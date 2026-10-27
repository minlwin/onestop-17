package com.jdc.balance.balanceapi.controller.member.input;

import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LedgerForm(
        @NotNull(message = "Please select entry type")
        EntryType type,
        @NotBlank(message = "Please enter ledger name")
        String name,
        @NotBlank(message = "Please enter description of ledger")
        String description
) {
}
