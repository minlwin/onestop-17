package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;

import java.time.LocalDateTime;

public record PaymentInfoDto(
        int id,
        String provider,
        String accountNo,
        String accountName,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static PaymentInfoDto from(PaymentInfo entity) {
        return new PaymentInfoDto(
                entity.getId(),
                entity.getProvider(),
                entity.getAccountNo(),
                entity.getAccountName(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
