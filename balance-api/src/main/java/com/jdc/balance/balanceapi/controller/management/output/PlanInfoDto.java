package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.dto.PlanInfoFeature;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;

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
    public static PlanInfoDto from(PlanInfo entity) {
        return new PlanInfoDto(
            entity.getId(),
            entity.getName(),
            entity.getPrice(),
            entity.getMaxEmployee(),
            entity.getMaxLedger(),
            entity.getMaxEntry(),
            entity.getDescription(),
            entity.getFeatures(),
            entity.getCreatedAt(),
            entity.getCreatedBy(),
            entity.getModifiedAt(),
            entity.getModifiedBy()
        );
    }
}
