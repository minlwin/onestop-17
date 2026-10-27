package com.jdc.balance.balanceapi.service;

import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.model.repo.account.EmployeeRepo;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginCompanyService {

    private final LoginUserService loginUserService;
    private final CompanyRepo companyRepo;
    private final EmployeeRepo employeeRepo;

    public Account getAccount() {
        return loginUserService.getLoginUser();
    }

    public Company getInfo() {
        var account = loginUserService.getLoginUser();

        if(!AccountRole.isMember(account.getRole())) {
            throw new BalanceAppException("Invalid role for using company");
        }

        if(account.getRole() == AccountRole.Partner) {
            return findForPartner(account.getId());
        }

        return safeCall(employeeRepo.findById(account.getId()).map(Employee::getCompany))
                .apply("Employee").apply("id").apply(account.getId().toString());
    }

    private Company findForPartner(UUID id) {
        return safeCall(companyRepo.findById(id))
                .apply("Company").apply("id").apply(id.toString());
    }
}
