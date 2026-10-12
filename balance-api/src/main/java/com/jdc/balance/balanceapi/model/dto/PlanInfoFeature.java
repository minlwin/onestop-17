package com.jdc.balance.balanceapi.model.dto;

import jakarta.validation.constraints.NotBlank;

public record PlanInfoFeature(
        @NotBlank(message = "Enter feature name")
        String name
) {
}
