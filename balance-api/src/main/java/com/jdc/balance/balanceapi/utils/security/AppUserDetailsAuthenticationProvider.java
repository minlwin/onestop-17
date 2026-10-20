package com.jdc.balance.balanceapi.utils.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppUserDetailsAuthenticationProvider extends DaoAuthenticationProvider {

    public AppUserDetailsAuthenticationProvider(AppUserDetailsService appUserDetailsService, PasswordEncoder passwordEncoder) {
        super(appUserDetailsService);
        setPasswordEncoder(passwordEncoder);
        setHideUserNotFoundExceptions(false);
    }
}
