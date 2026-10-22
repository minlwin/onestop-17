package com.jdc.balance.balanceapi.controller.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record SignUpForm(
        @NotBlank(message = "Please enter your name")
        String name,
        @NotBlank(message = "Please enter email address")
        String email
) {
}
