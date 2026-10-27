package com.jdc.balance.balanceapi.controller.partner;

import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.service.partner.SubscriptionPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("partner/plan")
public class SubscriptionPlanApi {

    private final SubscriptionPlanService service;

    @GetMapping
    List<PlanInfoDto> findAll() {
        return service.findAll();
    }
}
