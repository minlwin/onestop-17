package com.jdc.balance.balanceapi.service.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.SignUpForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.Partner;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.model.repo.account.PartnerRepo;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpService {

    private final AccountRepo accountRepo;
    private final PartnerRepo partnerRepo;

    @Transactional
    public ModificationResult<UUID> signUp(SignUpForm form) {

        if(accountRepo.findOneByUsername(form.email()).isPresent()) {
            throw new BalanceAppException("Email %s has been used in other account.".formatted(form.email()));
        }

        var account = new Account();
        account.setName(form.name());
        account.setUsername(form.email());
        account.setRole(AccountRole.Partner);

        account = accountRepo.save(account);

        var partner = new Partner();
        partner.setAccount(account);
        partner.setEmail(form.email());

        partnerRepo.save(partner);

        return new ModificationResult<>(account.getId());
    }
}
