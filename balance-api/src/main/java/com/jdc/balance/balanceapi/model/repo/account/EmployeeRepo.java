package com.jdc.balance.balanceapi.model.repo.account;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;

import java.util.UUID;

public interface EmployeeRepo extends BaseRepository<Employee, UUID> {
}
