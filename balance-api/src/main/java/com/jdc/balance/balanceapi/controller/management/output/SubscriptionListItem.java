package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import org.slf4j.helpers.SubstituteLogger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionListItem(
        UUID id,
        String partnerName,
        String phone,
        String email,
        String company,
        int planId,
        String planName,
        int months,
        int amount,
        LocalDateTime paidAt,
        SubscriptionStatus status,
        LocalDateTime appliedAt,
        LocalDateTime approvedAt,
        LocalDate subscribedAt
) {
}
