package com.jdc.balance.balanceapi.utils.schedules;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanActivationSchedule {

    private final SubscriptionRepo subscriptionRepo;

    @Transactional
    @Scheduled(cron = "0 10 0 * * *")
    public void activateActiveSubscription() {

        log.info("Start Activation");

        try {
            var subscriptions = subscriptionRepo.searchForActivate(SubscriptionStatus.Approved, LocalDate.now());

            for(var subscription : subscriptions) {
                var company = subscription.getCompany();
                var companyPlan = company.getPlan();

                if(subscription.getId().revision() > companyPlan.getRevision()) {
                    companyPlan.setPlanInfo(subscription.getPlanInfo());
                    companyPlan.setStartDate(subscription.getSubscriptionDate());
                    companyPlan.setExpiration(companyPlan.getStartDate().plusMonths(1));
                    companyPlan.setRevision(subscription.getId().revision());

                    log.info("{} is activate for {}.", companyPlan.getPlanInfo().getName(), company.getName());
                }
            }
        } finally {
            log.info("End Activation");
        }
    }
}
