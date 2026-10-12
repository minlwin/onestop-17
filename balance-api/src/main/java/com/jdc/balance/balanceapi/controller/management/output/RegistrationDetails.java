package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record RegistrationDetails(
        UUID id,
        String partnerName,
        String phone,
        String email,
        String position,
        String company,
        String companyType,
        String companyAddress,
        RegistrationStatus status,
        LocalDate registeredAt,
        LocalDate approvedAt,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
}
