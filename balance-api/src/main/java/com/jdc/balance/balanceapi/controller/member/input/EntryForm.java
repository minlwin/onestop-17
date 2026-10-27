package com.jdc.balance.balanceapi.controller.member.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record EntryForm(
        @NotBlank(message = "Please select ledger")
        String ledgerId,
        @NotNull(message = "Please enter issue date")
        LocalDate issueDate,
        @NotBlank(message = "Please enter particular information")
        String particular,
        @NotEmpty(message = "Please enter entry item")
        List<@Valid EntryFormItem> items
) {
}
