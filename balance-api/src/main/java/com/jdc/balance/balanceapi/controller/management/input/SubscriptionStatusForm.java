package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import jakarta.validation.constraints.NotNull;

public record SubscriptionStatusForm(
        @NotNull(message = "Please select status")
        SubscriptionStatus status,
        String remark
) {
}
