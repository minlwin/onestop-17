package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionDetails(
        UUID id,
        int revision,
        String partnerName,
        String phone,
        String email,
        String company,
        int planId,
        String planName,
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
        String remark,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static SubscriptionDetails from(Subscription entity) {
        return new SubscriptionDetails(
                entity.getId().companyId(),
                entity.getId().revision(),
                entity.getCompany().getPartner().getAccount().getName(),
                entity.getCompany().getPartner().getPhone(),
                entity.getCompany().getPartner().getEmail(),
                entity.getCompany().getName(),
                entity.getPlanInfo().getId(),
                entity.getPlanInfo().getName(),
                entity.getMonthlyFee(),
                entity.getCreatedAt(),
                entity.getStatus(),
                entity.getAppliedAt(),
                entity.getApprovedAt(),
                entity.getSubscriptionDate(),
                entity.getPaymentInfo().getProvider(),
                entity.getPaymentInfo().getAccountNo(),
                entity.getPaymentInfo().getAccountName(),
                entity.getPaymentSlip(),
                entity.getRemark(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
