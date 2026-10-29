package com.jdc.balance.balanceapi.controller.partner;

import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.controller.partner.input.SubscriptionForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.service.partner.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("partner/subscription")
public class SubscriptionApi {

    private final SubscriptionService service;

    @GetMapping
    List<SubscriptionListItem> getAll() {
        return service.getAll();
    }

    @PostMapping
    ModificationResult<SubscriptionPk> subscribe(@Validated SubscriptionForm form) {
        return service.subscribe(form);
    }

    @GetMapping("plans")
    List<PlanInfoDto> getPlans() {
        return service.getPlans();
    }

    @GetMapping("payments")
    List<PaymentInfoDto> getPaymentInfos() {
        return service.getPaymentInfos();
    }
}
