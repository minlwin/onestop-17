package com.jdc.balance.balanceapi.controller.partner.input;

import com.jdc.balance.balanceapi.model.entity.domain.account.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record EmployeeSearch(
        LocalDate fromDate,
        LocalDate toDate,
        String keyword
) {
    public List<Predicate> where(CriteriaBuilder cb, Root<Employee> root, Company company) {
        var params = new ArrayList<Predicate>();

        params.add(cb.equal(root.get(Employee_.company).get(Company_.id), company.getId()));

        if(fromDate != null) {
            params.add(cb.greaterThanOrEqualTo(root.get(Employee_.assignDate), fromDate));
        }

        if(toDate != null) {
            params.add(cb.lessThanOrEqualTo(root.get(Employee_.assignDate), toDate));
        }

        if(StringUtils.hasLength(keyword)) {
            var value = keyword.toLowerCase().concat("%");
            params.add(cb.or(
               cb.like(cb.lower(root.get(Employee_.account).get(Account_.name)), value),
               cb.like(cb.lower(root.get(Employee_.phone)), value)
            ));
        }

        return params;
    }
}
