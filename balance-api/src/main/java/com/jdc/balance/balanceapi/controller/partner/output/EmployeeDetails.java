package com.jdc.balance.balanceapi.controller.partner.output;

import com.jdc.balance.balanceapi.model.entity.consts.EmployeePermission;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;

import java.time.LocalDate;
import java.util.UUID;

public record EmployeeDetails(
        UUID id,
        String name,
        String phone,
        EmployeePermission permission,
        LocalDate assignDate,
        LocalDate resignDate,
        boolean disabled,
        Long entryCount
) {

    public static EmployeeDetails from(Employee entity) {
        return new EmployeeDetails(
                entity.getId(),
                entity.getAccount().getName(),
                entity.getPhone(),
                entity.getPermission(),
                entity.getAssignDate(),
                entity.getResignDate(),
                entity.isDisabled(),
                0L
        );
    }

    public EmployeeDetails withEntryCount(Long count) {
        return new EmployeeDetails(
                this.id,
                this.name,
                this.phone,
                this.permission,
                this.assignDate,
                this.resignDate,
                this.disabled,
                count
        );
    }
}
