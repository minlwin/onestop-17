package com.jdc.balance.balanceapi.controller.management.input;

import jakarta.validation.constraints.NotBlank;

public record PlanInfoFeature(
        @NotBlank(message = "Enter feature name")
        String name
) {
}
