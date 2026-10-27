package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.partner.input.EmployeeForm;
import com.jdc.balance.balanceapi.controller.partner.input.EmployeeSearch;
import com.jdc.balance.balanceapi.controller.partner.output.EmployeeDetails;
import com.jdc.balance.balanceapi.controller.partner.output.EmployeeListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee;
import com.jdc.balance.balanceapi.model.entity.domain.account.Employee_;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.model.repo.account.EmployeeRepo;
import com.jdc.balance.balanceapi.model.repo.business.LedgerEntryRepo;
import com.jdc.balance.balanceapi.service.LoginUserService;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeManagementService {

    private final EmployeeRepo employeeRepo;
    private final AccountRepo accountRepo;
    private final CompanyRepo companyRepo;
    private final LedgerEntryRepo ledgerEntryRepo;

    private final LoginUserService loginUserService;

    @PreAuthorize("hasAuthority('Partner')")
    public List<EmployeeListItem> search(EmployeeSearch form) {

        var company = getCompany();

        return employeeRepo.search(cb -> {
            var cq = cb.createQuery(EmployeeListItem.class);
            var root = cq.from(Employee.class);

            EmployeeListItem.select(cb, cq, root);
            cq.where(form.where(cb, root, company));
            cq.orderBy(cb.asc(root.get(Employee_.assignDate)));

            return cq;
        });
    }

    @PreAuthorize("hasAuthority('Partner')")
    public EmployeeDetails findById(String id) {
        var details = safeCall(employeeRepo.findById(UUID.fromString(id)).map(EmployeeDetails::from))
                .apply("Employee").apply("id").apply(id);
        var entryCount = ledgerEntryRepo.findCountForEmployee(details.id());
        return details.withEntryCount(entryCount);
    }

    @Transactional
    public ModificationResult<UUID> create(EmployeeForm form) {

        if(accountRepo.findOneByUsername(form.email()).isPresent()) {
            throw new BalanceAppException("Employee email has been used by other account.");
        }

        var account = new Account();
        account.setName(form.name());
        account.setUsername(form.email());
        account.setRole(AccountRole.Employee);
        account = accountRepo.save(account);

        var company = getCompany();

        var employee = new Employee();
        employee.setAccount(account);
        employee.setCompany(company);
        employee.setEmail(form.email());
        employee.setPhone(form.phone());
        employee.setPermission(form.permission());
        employee.setAssignDate(form.assignDate());

        employee = employeeRepo.save(employee);

        return new ModificationResult<>(employee.getId());
    }

    private Company getCompany() {
        var account = loginUserService.getLoginUser();
        return safeCall(companyRepo.findById(account.getId()))
                .apply("Company").apply("id").apply(account.getId().toString());
    }
}
