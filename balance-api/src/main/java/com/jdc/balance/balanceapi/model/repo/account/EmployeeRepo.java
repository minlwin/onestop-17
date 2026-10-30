package com.jdc.balance.balanceapi.model.repo.account;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepo extends BaseRepository<Employee, UUID> {
    List<Employee> findByCompanyId(UUID companyId);
}
