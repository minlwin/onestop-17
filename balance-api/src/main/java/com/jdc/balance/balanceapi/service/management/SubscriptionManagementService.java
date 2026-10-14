package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.SubscriptionSearch;
import com.jdc.balance.balanceapi.controller.management.input.SubscriptionStatusForm;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionDetails;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.utils.listener.SubscriptionApprovalEvent;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Function;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionManagementService {

    private final SubscriptionRepo subscriptionRepo;
    private final ApplicationEventPublisher eventPublisher;

    public PageResult<SubscriptionListItem> search(SubscriptionSearch form, int page, int size) {

        Function<CriteriaBuilder, CriteriaQuery<SubscriptionListItem>> queryFunc = cb -> {
            var cq = cb.createQuery(SubscriptionListItem.class);
            var root = cq.from(Subscription.class);

            SubscriptionListItem.select(cq, cb, root);
            cq.where(form.where(cb, root));
            cq.orderBy(cb.desc(root.get(Subscription_.appliedAt)));

            return cq;
        };

        Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc = cb -> {
            var cq = cb.createQuery(Long.class);
            var root = cq.from(Subscription.class);

            cq.select(cb.count(root.get(Subscription_.id)));
            cq.where(form.where(cb, root));

            return cq;
        };

        return null;
    }

    public SubscriptionDetails findById(String id) {
        return safeCall(subscriptionRepo.findById(UUID.fromString(id)).map(SubscriptionDetails::from))
                .apply("Subscription").apply("id").apply(id);
    }

    @Transactional
    public ModificationResult<UUID> update(String id, SubscriptionStatusForm form) {

        var entity = safeCall(subscriptionRepo.findById(UUID.fromString(id)))
                .apply("Subscription").apply("id").apply(id);

        entity.setStatus(form.status());
        entity.setRemark(form.remark());

        if(form.status() == SubscriptionStatus.Approved) {
            entity.setAppliedAt(LocalDateTime.now());
            eventPublisher.publishEvent(new SubscriptionApprovalEvent(entity.getId()));
        }

        return new ModificationResult<>(entity.getId());
    }
}
