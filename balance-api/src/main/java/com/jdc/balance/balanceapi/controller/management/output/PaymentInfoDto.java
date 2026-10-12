package com.jdc.balance.balanceapi.controller.management.output;

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
}
