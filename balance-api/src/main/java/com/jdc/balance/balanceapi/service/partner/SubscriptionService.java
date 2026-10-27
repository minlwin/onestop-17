package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.controller.partner.input.SubscriptionForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;
    private final LoginCompanyService loginCompanyService;

    @Transactional(readOnly = true)
    public List<SubscriptionListItem> getAll() {
        var company = loginCompanyService.getInfo();
        return subscriptionRepo.search(cb -> {
            var cq = cb.createQuery(SubscriptionListItem.class);
            var root = cq.from(Subscription.class);
            cq.where(cb.equal(root.get(Subscription_.company).get(Company_.id), company.getId()));
            SubscriptionListItem.select(cq, cb, root);
            cq.orderBy(cb.desc(root.get(Subscription_.appliedAt)));
            return cq;
        });
    }

    @Transactional
    public ModificationResult<UUID> subscribe(SubscriptionForm form) {
        var company = loginCompanyService.getInfo();
        return null;
    }
}
