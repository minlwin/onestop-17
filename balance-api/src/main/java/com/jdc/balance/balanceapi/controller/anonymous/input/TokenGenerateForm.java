package com.jdc.balance.balanceapi.controller.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record TokenGenerateForm(
        @NotBlank(message = "Please enter email address")
        String email,
        @NotBlank(message = "Please enter password")
        String password
) {
}
