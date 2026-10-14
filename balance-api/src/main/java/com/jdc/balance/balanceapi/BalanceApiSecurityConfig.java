package com.jdc.balance.balanceapi;

import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class BalanceApiSecurityConfig {

    @Bean
    SecurityFilterChain httpSecurity(HttpSecurity http) {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(req -> {
            req.requestMatchers("/anonymous/**").permitAll();
            req.requestMatchers("/management/**").hasAnyAuthority(AccountRole.Administrator.name(), AccountRole.Management.name());
            req.requestMatchers("/partner/**").hasAnyAuthority(AccountRole.Partner.name(), AccountRole.Employee.name());
            req.anyRequest().denyAll();
        });

        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
