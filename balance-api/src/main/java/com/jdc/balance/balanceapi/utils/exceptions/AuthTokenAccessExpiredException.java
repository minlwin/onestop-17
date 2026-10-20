package com.jdc.balance.balanceapi.utils.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.AuthenticationException;

public class AuthTokenAccessExpiredException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

	public AuthTokenAccessExpiredException(@Nullable String msg, Throwable cause) {
        super(msg, cause);
    }
}
