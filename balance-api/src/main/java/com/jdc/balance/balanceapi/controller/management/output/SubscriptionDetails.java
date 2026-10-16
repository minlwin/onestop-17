package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;

import java.math.BigDecimal;
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
        BigDecimal amount,
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

    public static SubscriptionDetails from(Subscription entity) {
        return new SubscriptionDetails(
                entity.getId(),
                entity.getCompany().getPartner().getAccount().getName(),
                entity.getCompany().getPartner().getPhone(),
                entity.getCompany().getPartner().getEmail(),
                entity.getCompany().getName(),
                entity.getPlanInfo().getId(),
                entity.getPlanInfo().getName(),
                entity.getMonths(),
                entity.getMonthlyFee().multiply(BigDecimal.valueOf(entity.getMonths())),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getAppliedAt(),
                entity.getApprovedAt(),
                entity.getSubscriptionDate(),
                entity.getPayment().getProvider(),
                entity.getPayment().getAccountNo(),
                entity.getPayment().getAccountName(),
                entity.getPaymentSlip(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
