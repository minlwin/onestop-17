package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public record CompanyProfileDto(
        UUID id,
        String name,
        String category,
        String address,
        String partnerName,
        String phone,
        String position,
        String remark,
        RegistrationStatus status,
        LocalDateTime registeredAt,
        LocalDateTime approvedAt,
        String planName,
        LocalDate expiredAt
) {
    public static CompanyProfileDto from(Company entity) {
        return new CompanyProfileDto(
                entity.getId(),
                entity.getName(),
                entity.getCategory(),
                entity.getAddress(),
                entity.getPartner().getAccount().getName(),
                entity.getPartner().getPhone(),
                entity.getPartner().getPosition(),
                entity.getRemark(),
                entity.getStatus(),
                entity.getRegisterAt(),
                entity.getApprovedAt(),
                Optional.ofNullable(entity.getPlan()).map(a -> a.getName()).orElse(""),
                entity.getPlanExpiration()
        );
    }
}
