package com.jdc.balance.balanceapi.utils.security;

import com.jdc.balance.balanceapi.model.dto.OtpResult;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.entity.domain.account.OtpCheckHistory;
import com.jdc.balance.balanceapi.model.repo.account.OtpCheckHistoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OtpGenerationService {

    private final OtpCheckHistoryRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final ThreadLocalRandom random = ThreadLocalRandom.current();

    @Value("${app.otp.max-life}")
    private int otpMaxLife;

    @Transactional
    public OtpResult generate(Account account) {

        var otpCode = "%06d".formatted(random.nextInt(1000, 999999));

        var history = new OtpCheckHistory();
        history.setAccount(account);
        history.setSecret(passwordEncoder.encode(otpCode));
        history.setInitializedAt(LocalDateTime.now());
        history.setExpiredAt(history.getInitializedAt().plusMinutes(otpMaxLife));

        history = repo.save(history);

        return new OtpResult(history.getId(), otpCode);
    }
}
