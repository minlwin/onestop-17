package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.RegistrationSearch;
import com.jdc.balance.balanceapi.controller.management.input.RegistrationStatusForm;
import com.jdc.balance.balanceapi.controller.management.output.RegistrationDetails;
import com.jdc.balance.balanceapi.controller.management.output.RegistrationListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.utils.listener.RegistrationApprovalEvent;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.function.Function;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegistrationManagementService {

    private final CompanyRepo companyRepo;
    private final ApplicationEventPublisher eventPublisher;

    public PageResult<RegistrationListItem> search(RegistrationSearch form, int page, int size) {

        Function<CriteriaBuilder, CriteriaQuery<RegistrationListItem>> queryFunc = cb -> {
            var cq = cb.createQuery(RegistrationListItem.class);
            var root = cq.from(Company.class);

            RegistrationListItem.select(cb, cq, root);
            cq.where(form.where(cb, root));

            cq.orderBy(cb.desc(root.get(Company_.registerAt)));

            return  cq;
        };

        Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
            var cq = cb.createQuery(Long.class);

            var root = cq.from(Company.class);
            cq.select(cb.count(root.get(Company_.id)));
            cq.where(form.where(cb, root));

            return  cq;
        };

        return companyRepo.search(queryFunc, countFunc, page, size);
    }

    public RegistrationDetails findById(String id) {
        return  safeCall(companyRepo.findById(UUID.fromString(id)).map(RegistrationDetails::from))
                .apply("Registration").apply("id").apply(id);
    }

    @Transactional
    public ModificationResult<UUID> update(String id, RegistrationStatusForm form) {
        var entity = safeCall(companyRepo.findById(UUID.fromString(id)))
                .apply("Registration").apply("id").apply(id);

        entity.setStatus(form.status());
        entity.setRemark(form.remark());

        if(entity.getStatus() == RegistrationStatus.Approved) {
            eventPublisher.publishEvent(new RegistrationApprovalEvent(entity.getId()));
        }

        return  new ModificationResult<>(entity.getId());
    }
}
