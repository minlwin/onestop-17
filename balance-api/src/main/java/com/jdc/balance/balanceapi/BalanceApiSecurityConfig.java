package com.jdc.balance.balanceapi;

import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;
import com.jdc.balance.balanceapi.utils.exceptions.SecurityExceptionsHandler;
import com.jdc.balance.balanceapi.utils.security.AuthTokenProvider;
import com.jdc.balance.balanceapi.utils.security.AuthTokenSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableMethodSecurity
public class BalanceApiSecurityConfig {

    @Bean
    SecurityFilterChain httpSecurity(HttpSecurity http,
                                     AuthTokenSecurityFilter authTokenSecurityFilter,
                                     SecurityExceptionsHandler securityExceptionsHandler) {

        http.csrf(csrf -> csrf.disable());

        http.authorizeHttpRequests(req -> {
            req.requestMatchers("/anonymous/**").permitAll();
            req.requestMatchers("/management/**").hasAnyAuthority(AccountRole.Administrator.name(), AccountRole.Management.name());
            req.requestMatchers("/partner/**").hasAuthority(AccountRole.Partner.name());
            req.requestMatchers("/member/**").hasAnyAuthority(AccountRole.Partner.name(), AccountRole.Employee.name());
            req.anyRequest().denyAll();
        });

        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        http.addFilterBefore(authTokenSecurityFilter, AuthorizationFilter.class);

        http.exceptionHandling(exceptions -> {
            exceptions.accessDeniedHandler(securityExceptionsHandler);
            exceptions.authenticationEntryPoint(securityExceptionsHandler);
        });
        return http.build();
    }

    @Bean
    AuthTokenSecurityFilter authTokenSecurityFilter(AuthTokenProvider authTokenProvider) {
        return new AuthTokenSecurityFilter(authTokenProvider);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
        return configuration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
