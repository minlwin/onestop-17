package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserListItem(
        UUID id,
        String name,
        String phone,
        String email,
        UserStatus status,
        LocalDate assignDate,
        LocalDateTime activatedAt,
        LocalDate retiredAt
) {
}
