package com.jdc.balance.balanceapi.service.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.ActivationForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.repo.account.OtpCheckHistoryRepo;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
public class AccountActivationService {

    private final OtpCheckHistoryRepo historyRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional(noRollbackFor = BalanceAppException.class)
    public ModificationResult<String> activate(String id, ActivationForm form) {

        var history = safeCall(historyRepo.findById(UUID.fromString(id)))
                .apply("OTP History").apply("id").apply(id);

        history.setCheckedAt(LocalDateTime.now());

        if(LocalDateTime.now().isAfter(history.getExpiredAt())) {
            history.setSuccess(false);
            throw new BalanceAppException("Your activation link has been expired.");
        }

        if(!passwordEncoder.matches(form.optCode(), history.getSecret())) {
            history.setSuccess(false);
            throw new BalanceAppException("Your OTP Code is invalid. Please check your email.");
        }

        var account = history.getAccount();
        account.setPassword(passwordEncoder.encode(form.password()));
        account.setActivatedAt(LocalDateTime.now());

        return new ModificationResult<>("Your account has been activated. Please login again.");
    }
}
