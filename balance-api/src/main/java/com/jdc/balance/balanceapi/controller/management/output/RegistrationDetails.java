package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;

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
        LocalDateTime registeredAt,
        LocalDateTime approvedAt,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static RegistrationDetails from(Company entity) {
        return  new RegistrationDetails(
                entity.getId(),
                entity.getPartner().getAccount().getName(),
                entity.getPartner().getPhone(),
                entity.getPartner().getEmail(),
                entity.getPartner().getPosition(),
                entity.getName(),
                entity.getCategory(),
                entity.getAddress(),
                entity.getStatus(),
                entity.getRegisterAt(),
                entity.getApprovedAt(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
