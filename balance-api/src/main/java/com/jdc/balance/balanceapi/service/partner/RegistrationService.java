package com.jdc.balance.balanceapi.service.partner;

import com.jdc.balance.balanceapi.controller.partner.input.RegistrationForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.RegistrationStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.model.repo.account.CompanyRepo;
import com.jdc.balance.balanceapi.model.repo.account.PartnerRepo;
import com.jdc.balance.balanceapi.service.LoginUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final LoginUserService loginUserService;
    private final PartnerRepo partnerRepo;
    private final CompanyRepo companyRepo;

    @Transactional
    public ModificationResult<UUID> register(RegistrationForm form) {
        var account = loginUserService.getLoginUser();

        var partner = safeCall(partnerRepo.findById(account.getId()))
                .apply("Partner").apply("id").apply(account.getId().toString());
        partner.setPhone(form.phone());
        partner.setPosition(form.position());

        var company = new Company();
        company.setPartner(partner);
        company.setName(form.companyName());
        company.setCategory(form.category());
        company.setAddress(form.address());
        company.setRegisterAt(LocalDateTime.now());
        company.setStatus(RegistrationStatus.Pending);
        company.setRemark(form.remark());

        company = companyRepo.save(company);

        return new ModificationResult<>(company.getId());
    }
}
