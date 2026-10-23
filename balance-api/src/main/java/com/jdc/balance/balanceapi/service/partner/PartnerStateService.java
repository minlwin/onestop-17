package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.partner.output.PartnerStateDto;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class PartnerStateService {

    private final LoginUserService loginUserService;
    private final CompanyRepo companyRepo;

    @Transactional(readOnly = true)
    public PartnerStateDto findState() {
        var account = loginUserService.getLoginUser();
        return companyRepo.findById(account.getId())
                .map(PartnerStateDto::from).orElse(PartnerStateDto.empty());
    }
}
