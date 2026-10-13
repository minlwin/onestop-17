package com.jdc.balance.balanceapi.controller.management.input;

import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Partner_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public record RegistrationSearch(
    RegistrationStatus status,
    LocalDate registeredFrom,
    LocalDate registeredTo,
    String keyword
) {
    public List<Predicate> where(CriteriaBuilder cb, Root<Company> root) {
        var params = new ArrayList<Predicate>();

        if(null != status) {
            params.add(cb.equal(root.get(Company_.status), status));
        }

        if(null != registeredFrom) {
            params.add(cb.greaterThanOrEqualTo(root.get(Company_.registerAt), registeredFrom.atStartOfDay()));
        }

        if(null != registeredTo) {
            params.add(cb.lessThan(root.get(Company_.registerAt), registeredTo.plusDays(1).atStartOfDay()));
        }

        if(StringUtils.hasLength(keyword)) {
            var param = keyword.toLowerCase().concat("%");
            params.add(cb.or(
                    cb.like(cb.lower(root.get(Company_.name)), param),
                    cb.like(cb.lower(root.get(Company_.partner).get(Partner_.name)), param)
            ));
        }

        return params;
    }
}
