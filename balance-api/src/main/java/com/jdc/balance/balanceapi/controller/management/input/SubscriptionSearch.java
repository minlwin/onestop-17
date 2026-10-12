package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;

import java.time.LocalDate;

public record SubscriptionSearch(
        SubscriptionStatus status,
        LocalDate appliedFrom,
        LocalDate appliedTo,
        String keyword
) {
}
