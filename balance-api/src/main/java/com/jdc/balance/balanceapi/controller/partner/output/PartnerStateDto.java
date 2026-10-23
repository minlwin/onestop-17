package com.jdc.balance.balanceapi.controller.partner.output;

import com.jdc.balance.balanceapi.model.entity.domain.account.Company;

import java.time.LocalDateTime;
import java.util.UUID;

public record PartnerStateDto(
        UUID companyId,
        LocalDateTime registeredAt,
        LocalDateTime approvedAt
) {
    public static PartnerStateDto from(Company company) {
        return  new PartnerStateDto(company.getId(), company.getRegisterAt(), company.getApprovedAt());
    }

    public static PartnerStateDto empty() {
        return new PartnerStateDto(null, null, null);
    }
}
