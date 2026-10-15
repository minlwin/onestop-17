package com.jdc.balance.balanceapi.utils.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    List<String> handle(BalanceAppException e) {
        return List.of(e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    List<String> handle(MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.GONE)
    List<String> handle(AuthTokenAccessExpiredException e) {
        return List.of(e.getMessage());
    }


    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    List<String> handle(AuthenticationException e) {
        var message = switch (e) {
            case BadCredentialsException _ -> "Please check your password";
            case UsernameNotFoundException _ -> "Please check your login id";
            case DisabledException _ -> "You need to activate your account";
            case AccountExpiredException _ -> "Your account had been expired";
            case AuthTokenInvalidException te -> te.getMessage();
            default -> "Authentication Error";
        };
        return List.of(message);
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    List<String> handle(AccessDeniedException e) {
        log.error("Access Denied Error", e);
        return List.of("You have no authority for this action");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    List<String> handle(Throwable e) {
        log.error("Server Error", e);
        return List.of("Internal Server Error");
    }
}
