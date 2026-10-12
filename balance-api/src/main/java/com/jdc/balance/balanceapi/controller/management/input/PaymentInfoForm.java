package com.jdc.balance.balanceapi.controller.management.input;

import jakarta.validation.constraints.NotBlank;

public record PaymentInfoForm(
        @NotBlank(message = "Enter payment provider")
        String provider,
        @NotBlank(message = "Enter account number")
        String accountNo,
        @NotBlank(message = "Enter account holder name")
        String accountName
) {
}
