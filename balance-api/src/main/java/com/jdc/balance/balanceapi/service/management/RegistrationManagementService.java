package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.RegistrationSearch;
import com.jdc.balance.balanceapi.controller.management.output.RegistrationListItem;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class RegistrationManagementService {

    private final CompanyRepo companyRepo;

    @Transactional(readOnly = true)
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
}
