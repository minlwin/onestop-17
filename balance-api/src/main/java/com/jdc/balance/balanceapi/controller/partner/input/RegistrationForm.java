package com.jdc.balance.balanceapi.controller.partner.input;

import jakarta.validation.constraints.NotBlank;

public record RegistrationForm(
        @NotBlank(message = "Enter company name")
        String companyName,
        @NotBlank(message = "Enter company category")
        String category,
        @NotBlank(message = "Enter company address")
        String address,
        @NotBlank(message = "Enter partner phone")
        String phone,
        @NotBlank(message = "Enter partner position in company")
        String position,
        String remark
) {
}
