package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import jakarta.validation.constraints.NotNull;

public record RegistrationStatusForm(
        @NotNull(message = "Please select status")
        RegistrationStatus status,
        String remark
) {
}
