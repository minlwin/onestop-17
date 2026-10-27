package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.controller.partner.input.SubscriptionForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import com.jdc.balance.balanceapi.model.repo.business.SubscriptionRepo;
import com.jdc.balance.balanceapi.model.repo.master.PaymentInfoRepo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import com.jdc.balance.balanceapi.service.LoginCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final LoginCompanyService loginCompanyService;
    private final SubscriptionSlipFileStore fileStore;

    private final PlanInfoRepo planInfoRepo;
    private final PaymentInfoRepo paymentInfoRepo;
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
    public ModificationResult<UUID> subscribe(SubscriptionForm form) {
        var company = loginCompanyService.getInfo();
        var plan = safeCall(planInfoRepo.findById(form.planId()))
                .apply("Plan Information").apply("id").apply(form.planId().toString());
        var paymentInfo = safeCall(paymentInfoRepo.findById(form.paymentInfoId()))
                .apply("Payment Information").apply("id").apply(form.paymentInfoId().toString());
        var slip = fileStore.save(company, form.paymentSlip());

        var subscription = new Subscription();
        subscription.setCompany(company);
        subscription.setPlanInfo(plan);
        subscription.setPayment(paymentInfo);
        subscription.setMonthlyFee(BigDecimal.valueOf(plan.getPrice()));
        subscription.setPaymentSlip(slip);
        subscription.setSubscriptionDate(form.subscriptionDate());

        subscription.setStatus(SubscriptionStatus.Pending);
        subscription.setAppliedAt(LocalDateTime.now());

        subscription = subscriptionRepo.save(subscription);
        return new ModificationResult<>(subscription.getId());
    }

    @Transactional(readOnly = true)
    public List<PlanInfoDto> getPlans() {
        return planInfoRepo.findAll().stream().map(PlanInfoDto::from).toList();
    }

    @Transactional(readOnly = true)
    public List<PaymentInfoDto> getPaymentInfos() {
        return paymentInfoRepo.findAll().stream().map(PaymentInfoDto::from).toList();
    }
}
