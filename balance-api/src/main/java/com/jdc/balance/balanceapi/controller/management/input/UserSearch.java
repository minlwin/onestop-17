package com.jdc.balance.balanceapi.controller.management.input;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.jdc.balance.balanceapi.model.entity.consts.UserStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record UserSearch(
        UserStatus status,
        LocalDate assignFrom,
        LocalDate assignTo,
        String keyword
) {
    public List<Predicate> where(CriteriaBuilder cb, Root<Member> root) {
        var params = new ArrayList<Predicate>();

        if(null != status) {
            params.add(cb.equal(root.get(Member_.status), status));
        }

        if(null != assignFrom) {
            params.add(cb.greaterThanOrEqualTo(root.get(Member_.assignDate), assignFrom));
        }

        if(null != assignTo) {
            params.add(cb.lessThan(root.get(Member_.assignDate), assignTo.plusDays(1)));
        }

        if(StringUtils.hasLength(keyword)) {
            var param = keyword.toLowerCase().concat("%");
            params.add(cb.or(
                    cb.like(cb.lower(root.get(Member_.account).get(Account_.name)), param),
                    cb.like(cb.lower(root.get(Member_.phone)), param),
                    cb.like(cb.lower(root.get(Member_.email)), param)
            ));
        }

        return params;
    }
}
