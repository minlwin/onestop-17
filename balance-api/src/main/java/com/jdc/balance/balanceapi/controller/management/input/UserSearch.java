package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;

import java.time.LocalDate;

public record UserSearch(
        UserStatus status,
        LocalDate assignFrom,
        LocalDate assignTo,
        String keyword
) {
}
