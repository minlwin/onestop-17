package com.jdc.balance.balanceapi.utils.security;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AdministratorInitializer {

    private final AccountRepo accountRepo;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;
    @Value("${app.admin.password}")
    private String adminPassword;

    @Transactional
    @EventListener(classes = ContextRefreshedEvent.class)
    void handle() {
        if(accountRepo.count() == 0L) {
            var admin = new Account();
            admin.setName("Admin User");
            admin.setUsername(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(AccountRole.Administrator);
            admin.setActivatedAt(LocalDateTime.now());
            accountRepo.save(admin);
        }
    }
}
