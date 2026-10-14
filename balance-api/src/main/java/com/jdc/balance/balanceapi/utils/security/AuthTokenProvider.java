package com.jdc.balance.balanceapi.utils.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenProvider {

    public enum Type {
        Access, Refresh
    }

    public String generateToken(Type type, Authentication authentication) {
        return null;
    }

    public Authentication parseToken(Type type, String token) {
        return null;
    }
}
