package com.jdc.balance.balanceapi.controller.member.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EntryFormItem(
        @NotBlank(message = "Please enter article")
        String article,
        @NotNull(message = "Please enter quantity")
        Integer quantity,
        @NotNull(message = "Please enter unit price")
        BigDecimal unitPrice
) {
}
