package com.jdc.balance.balanceapi.service;

import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final AccountRepo accountRepo;

    @Transactional(readOnly = true)
    public Account getLoginUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return safeCall(accountRepo.findOneByUsername(username))
                .apply("Account").apply("email").apply(username);
    }
}
