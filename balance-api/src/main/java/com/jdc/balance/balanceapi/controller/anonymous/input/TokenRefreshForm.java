package com.jdc.balance.balanceapi.controller.anonymous.input;

import jakarta.validation.constraints.NotBlank;

public record TokenRefreshForm(
        @NotBlank(message = "Please enter refresh token")
        String token
) {
}
