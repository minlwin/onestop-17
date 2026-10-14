package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
    public static void select(CriteriaQuery<UserListItem> cq, CriteriaBuilder cb, Root<Member> root) {
        cq.select(cb.construct(UserListItem.class,
                root.get(Member_.id),
                root.get(Member_.name),
                root.get(Member_.phone),
                root.get(Member_.email),
                root.get(Member_.status),
                root.get(Member_.assignDate),
                root.get(Member_.account).get(Account_.activatedAt),
                root.get(Member_.resignDate)
        ));
    }
}
