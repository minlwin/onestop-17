package com.jdc.balance.balanceapi.service.member;

import com.jdc.balance.balanceapi.controller.member.output.CompanyProfileDto;
import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.repo.account.EmployeeRepo;
import com.jdc.balance.balanceapi.model.repo.account.PartnerRepo;
import com.jdc.balance.balanceapi.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class CompanyProfileService {

    private final LoginUserService loginUserService;
    private final PartnerRepo partnerRepo;
    private final EmployeeRepo employeeRepo;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyAuthority('Partner', 'Employee')")
    public CompanyProfileDto getProfile() {
        var account = loginUserService.getLoginUser();

        if(account.getRole() == AccountRole.Employee) {
            var employee = safeCall(employeeRepo.findById(account.getId()))
                    .apply("Employee").apply("id").apply(account.getId().toString());

            return CompanyProfileDto.from(employee.getCompany());
        }

        var partner = safeCall(partnerRepo.findById(account.getId()))
                .apply("Partner").apply("id").apply(account.getId().toString());

        return CompanyProfileDto.from(partner.getCompany());
    }

}
