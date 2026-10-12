package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoFeature;

import java.time.LocalDateTime;
import java.util.List;

public record PlanInfoDto(
        int id,
        String name,
        Integer price,
        Integer maxEmployee,
        Integer maxLedger,
        Integer maxEntry,
        String description,
        List<PlanInfoFeature> features,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
