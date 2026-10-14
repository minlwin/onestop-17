package com.jdc.balance.balanceapi.utils.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
    List<String > handle(MethodArgumentNotValidException e) {
        return e.getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .toList();
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    List<String> handle(Throwable e) {
        log.error("Server Error", e);
        return List.of("Internal Server Error");
    }
}
