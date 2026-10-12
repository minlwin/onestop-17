package com.jdc.balance.balanceapi.controller.management.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserForm(
        @NotBlank(message = "Please enter user name")
        String name,
        @NotBlank(message = "Please enter phone number")
        String phone,
        @NotBlank(message = "Please enter email address")
        String email,
        @NotNull(message = "Please enter assign date")
        LocalDate assignDate
) {
}
