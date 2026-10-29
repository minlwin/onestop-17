package com.jdc.balance.balanceapi.utils.listener;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionAction;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.CompanyPlan;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.model.repo.account.CompanyPlanRepo;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
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
public class RegistrationApprovalEventListener {

    private final CompanyRepo companyRepo;
    private final PlanInfoRepo planInfoRepo;
    private final SubscriptionRepo subscriptionRepo;
    private final CompanyPlanRepo companyPlanRepo;

    @Async
    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handle(RegistrationApprovalEvent event) {
        try {
            var company = companyRepo.getReferenceById(event.id());

            var defaultPlan = planInfoRepo.findOneByDefaultPlanIsTrue().orElse(null);

            if(null != defaultPlan) {
                var subscription = new Subscription();
                subscription.setId(SubscriptionPk.from(company.getId(), 1));
                subscription.setCompany(company);
                subscription.setPlanInfo(defaultPlan);
                subscription.setStatus(SubscriptionStatus.Approved);
                subscription.setAction(SubscriptionAction.Registration);
                subscription = subscriptionRepo.save(subscription);

                var companyPlan = new CompanyPlan();
                companyPlan.setRevision(subscription.getId().revision());
                companyPlan.setCompany(company);
                companyPlan.setPlanInfo(subscription.getPlanInfo());
                companyPlan.setStartDate(LocalDate.now());

                companyPlanRepo.save(companyPlan);
            }

        } catch (Exception e) {
            log.error("Error on Registration Event Listener", e);
        }
    }
}
