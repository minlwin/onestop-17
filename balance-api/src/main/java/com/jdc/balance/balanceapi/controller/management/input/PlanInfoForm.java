package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.dto.PlanInfoFeature;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PlanInfoForm(
        @NotBlank(message = "Enter plan name")
        String name,
        @NotNull(message = "Enter monthly fee")
        Integer price,
        @NotNull(message = "Enter employee limit")
        Integer maxEmployee,
        @NotNull(message = "Enter ledger limit")
        Integer maxLedger,
        @NotNull(message = "Enter entry limit")
        Integer maxEntry,
        @NotNull(message = "Enter description")
        String description,
        @NotEmpty(message = "Enter features")
        List<@Valid PlanInfoFeature> features
) {
}
