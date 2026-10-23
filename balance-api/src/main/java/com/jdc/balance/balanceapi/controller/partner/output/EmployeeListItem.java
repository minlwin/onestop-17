package com.jdc.balance.balanceapi.controller.partner.output;

import com.jdc.balance.balanceapi.model.entity.consts.EmployeePermission;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeListItem(
        UUID id,
        String name,
        String phone,
        EmployeePermission permission,
        LocalDate assignDate,
        LocalDate resignDate,
        boolean disabled
) {
    public static void select(CriteriaBuilder cb, CriteriaQuery<EmployeeListItem> cq, Root<Employee> root) {
        cq.select(cb.construct(EmployeeListItem.class,
                root.get(Employee_.id),
                root.get(Employee_.account).get(Account_.name),
                root.get(Employee_.phone),
                root.get(Employee_.permission),
                root.get(Employee_.assignDate),
                root.get(Employee_.resignDate),
                root.get(Employee_.disabled)
        ));
    }
}
