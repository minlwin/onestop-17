package com.jdc.balance.balanceapi.utils.listener;

import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.service.ActivationMailService;
import com.jdc.balance.balanceapi.utils.security.OtpGenerationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Component
@RequiredArgsConstructor
public class AccountCreationListener {

    private final AccountRepo accountRepo;
    private final OtpGenerationService otpGenerationService;
    private final ActivationMailService activationMailService;

    @Async
    @EventListener
    @Transactional
    void handle(AccountCreationEvent event) {
        var account = safeCall(accountRepo.findById(event.id()))
                .apply("Account").apply("id").apply(event.id().toString());

        var otpResult = otpGenerationService.generate(account);

        activationMailService.send(account, otpResult);
    }
}
