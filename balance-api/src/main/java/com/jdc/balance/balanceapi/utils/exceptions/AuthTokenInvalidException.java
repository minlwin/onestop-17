package com.jdc.balance.balanceapi.utils.exceptions;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

@Getter
public class AuthTokenInvalidException extends AuthenticationException {

    public AuthTokenInvalidException(@Nullable String msg) {
        super(msg);
    }

    public AuthTokenInvalidException(@Nullable String msg, Throwable cause) {
        super(msg, cause);
    }
}
