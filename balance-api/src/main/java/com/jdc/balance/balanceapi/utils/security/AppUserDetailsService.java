package com.jdc.balance.balanceapi.utils.security;

import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepo.findOneByUsername(username)
                .map(account -> User.withUsername(username)
                        .password(account.getPassword())
                        .authorities(account.getRole().toString())
                        .disabled(null == account.getActivatedAt())
                        .accountExpired(account.isExpired())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("There is no user with %s".formatted(username)));
    }
}
