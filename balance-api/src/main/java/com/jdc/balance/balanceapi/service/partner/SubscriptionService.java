package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.controller.partner.input.SubscriptionForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionAction;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import com.jdc.balance.balanceapi.service.commons.PaymentInfoMasterService;
import com.jdc.balance.balanceapi.service.commons.PlanInfoMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final LoginCompanyService loginCompanyService;
    private final SubscriptionSlipFileStore fileStore;

    private final PlanInfoMasterService planInfoMasterService;
    private final PaymentInfoMasterService paymentInfoMasterService;

    private final SubscriptionRepo subscriptionRepo;

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
    public ModificationResult<SubscriptionPk> subscribe(SubscriptionForm form) {
        var company = loginCompanyService.getInfo();
        var plan = planInfoMasterService.getReference(form.planId());
        var paymentInfo = paymentInfoMasterService.getReference(form.paymentInfoId());
        var slip = fileStore.save(company, form.paymentSlip());

        var subscriptions = subscriptionRepo.findByCompanyId(company.getId());
        var latestSubscription = subscriptions.getFirst();

        var subscription = latestSubscription;

        if(latestSubscription.getStatus() == SubscriptionStatus.Approved) {
            subscription = new Subscription();
            subscription.setId(SubscriptionPk.from(company.getId(), company.getPlan().getRevision() + 1));
        }

        subscription.setCompany(company);
        subscription.setPlanInfo(plan);

        subscription.setStatus(SubscriptionStatus.Pending);
        subscription.setAction(SubscriptionAction.Subscription);
        subscription.setSubscriptionDate(form.subscriptionDate());

        subscription.setPaymentInfo(paymentInfo);
        subscription.setMonthlyFee(BigDecimal.valueOf(plan.getPrice()));
        subscription.setPaymentSlip(slip);
        subscription.setAppliedAt(LocalDateTime.now());

        subscription = subscriptionRepo.save(subscription);
        return new ModificationResult<>(subscription.getId());
    }

    @Transactional(readOnly = true)
    public List<PlanInfoDto> getPlans() {
        return planInfoMasterService.findAll();
    }

    @Transactional(readOnly = true)
    public List<PaymentInfoDto> getPaymentInfos() {
        return paymentInfoMasterService.findAll();
    }
}
