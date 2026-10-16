package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Partner_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record SubscriptionSearch(
        SubscriptionStatus status,
        LocalDate appliedFrom,
        LocalDate appliedTo,
        String keyword
) {
    public List<Predicate> where(CriteriaBuilder cb, Root<Subscription> root) {
        var params = new ArrayList<Predicate>();

        if(null != status) {
            params.add(cb.equal(root.get(Subscription_.status), status));
        }

        if(null != appliedFrom) {
            params.add(cb.greaterThanOrEqualTo(root.get(Subscription_.appliedAt), appliedFrom.atStartOfDay()));
        }

        if(null != appliedTo) {
            params.add(cb.lessThan(root.get(Subscription_.appliedAt), appliedTo.plusDays(1).atStartOfDay()));
        }

        if(StringUtils.hasLength(keyword)) {
            var param = keyword.toLowerCase().concat("%");
            params.add(cb.or(
                    cb.like(cb.lower(root.get(Subscription_.company).get(Company_.name)), param),
                    cb.like(cb.lower(root.get(Subscription_.company).get(Company_.partner).get(Partner_.account).get(Account_.name)), param)
            ));
        }

        return params;
    }
}
