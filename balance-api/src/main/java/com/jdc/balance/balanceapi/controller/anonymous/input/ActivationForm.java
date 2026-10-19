package com.jdc.balance.balanceapi.controller.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record ActivationForm(
        @NotBlank(message = "Please enter OTP Code")
        String optCode,
        @NotBlank(message = "Please enter your password")
        String password
) {
}
