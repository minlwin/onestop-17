package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDetails(
        UUID id,
        String name,
        String phone,
        String email,
        UserStatus status,
        LocalDate assignDate,
        LocalDateTime activatedAt,
        LocalDate retiredAt,
        long contractForThisMonth,
        long contractTotal,
        List<AccessLog> accessHistory
) {

    public static UserDetails from(Member entity) {
        return  new UserDetails(
                entity.getId(),
                entity.getAccount().getName(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getStatus(),
                entity.getAssignDate(),
                entity.getAccount().getActivatedAt(),
                entity.getResignDate(),
                0,
                0,
                List.of()
        );
    }
}
