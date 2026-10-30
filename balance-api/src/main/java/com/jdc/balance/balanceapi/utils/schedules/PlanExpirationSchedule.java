package com.jdc.balance.balanceapi.utils.schedules;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionAction;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.model.repo.account.CompanyPlanRepo;
import com.jdc.balance.balanceapi.model.repo.account.EmployeeRepo;
import com.jdc.balance.balanceapi.model.repo.business.LedgerRepo;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanExpirationSchedule {

    private final SubscriptionRepo subscriptionRepo;
    private final CompanyPlanRepo companyPlanRepo;
    private final PlanInfoRepo planInfoRepo;
    private final LedgerRepo ledgerRepo;
    private final EmployeeRepo employeeRepo;

    @Scheduled(cron = "00 50 23 * * *")
    public void resetExpiredSubscription() {

        log.info("Start Reset Process");

        try {
            var defaultPlan = planInfoRepo.findOneByDefaultPlanIsTrue().orElse(null);

            if(null == defaultPlan) {
                log.info("There is no default plan");
                return;
            }

            var companyPlans = companyPlanRepo.findByExpiration(LocalDate.now());

            for(var companyPlan : companyPlans) {
                var subscription = new Subscription();
                subscription.setId(SubscriptionPk.from(companyPlan.getCompany().getId(), companyPlan.getRevision() + 1));
                subscription.setCompany(companyPlan.getCompany());
                subscription.setPlanInfo(defaultPlan);
                subscription.setSubscriptionDate(LocalDate.now());
                subscription.setAppliedAt(LocalDateTime.now());
                subscription.setApprovedAt(LocalDateTime.now());
                subscription.setStatus(SubscriptionStatus.Approved);
                subscription.setAction(SubscriptionAction.Schedular);
                subscription = subscriptionRepo.save(subscription);

                companyPlan.setRevision(subscription.getId().revision());
                companyPlan.setCompany(subscription.getCompany());
                companyPlan.setPlanInfo(subscription.getPlanInfo());
                companyPlan.setStartDate(LocalDate.now());

                var ledgers = ledgerRepo.findByCompanyId(companyPlan.getCompany().getId());

                for(var i = 0; i < ledgers.size(); i ++) {

                    // max == 3, i == 3
                    if(defaultPlan.getMaxLedger() > i) {
                        continue;
                    }

                    ledgers.get(i).setDisabled(true);
                }

                var employees = employeeRepo.findByCompanyId(companyPlan.getCompany().getId());

                for(var i = 0; i < employees.size(); i ++) {
                    if(defaultPlan.getMaxEmployee() > i) {
                        continue;
                    }

                    employees.get(i).setDisabled(true);
                }

            }

        } finally {
            log.info("End Reset Process");
        }
    }
}
