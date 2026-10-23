package com.jdc.balance.balanceapi.controller.partner.input;

import com.jdc.balance.balanceapi.model.entity.consts.EmployeePermission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmployeeForm(
        @NotBlank(message = "Please enter employee name")
        String name,
        @NotBlank(message = "Please enter employee phone")
        String phone,
        @NotBlank(message = "Please enter employee email")
        String email,
        @NotNull(message = "Please select permission")
        EmployeePermission permission,
        @NotNull(message = "Please enter assign date")
        LocalDate assignDate
) {
}
