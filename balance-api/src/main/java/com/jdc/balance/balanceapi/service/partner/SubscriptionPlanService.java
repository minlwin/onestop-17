package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanService {

    private final PlanInfoRepo planInfoRepo;

    @Transactional(readOnly = true)
    public List<PlanInfoDto> findAll() {
        return planInfoRepo.findAll().stream().map(PlanInfoDto::from).toList();
    }
}
