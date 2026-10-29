package com.jdc.balance.balanceapi.utils.listener;

import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionApprovalEventListener {

    private final SubscriptionRepo subscriptionRepo;

    @Async
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(SubscriptionApprovalEvent event) {
        try {

            var subscription = subscriptionRepo.getReferenceById(event.id());

            if(!subscription.getSubscriptionDate().isAfter(LocalDate.now())) {
                var companyPlan = subscription.getCompany().getPlan();
                companyPlan.setPlanInfo(subscription.getPlanInfo());
                companyPlan.setRevision(subscription.getId().revision());
                companyPlan.setStartDate(LocalDate.now());
                companyPlan.setExpiration(LocalDate.now().plusMonths(1));
            }

        } catch (Exception e) {
            log.error("Error on Subscription Approval Event Listener", e);
        }
    }
}
