package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;

import java.time.LocalDate;

public record RegistrationSearch(
    RegistrationStatus status,
    LocalDate registeredFrom,
    LocalDate registeredTo,
    String keyword
) {
}
