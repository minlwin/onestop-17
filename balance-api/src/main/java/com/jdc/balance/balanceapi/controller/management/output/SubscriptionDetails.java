package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionDetails(
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
        LocalDate subscribedAt,
        String paymentProvider,
        String accountNo,
        String accountName,
        String paymentSlip,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
