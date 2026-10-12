package com.jdc.balance.balanceapi.controller.management.input;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RetirementForm(
        @NotNull(message = "Enter retire date")
        LocalDate retireDate
) {
}
