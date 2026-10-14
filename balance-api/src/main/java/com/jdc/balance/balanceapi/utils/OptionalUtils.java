package com.jdc.balance.balanceapi.utils;

import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;

import java.util.Optional;
import java.util.function.Function;

public interface OptionalUtils {

    static <T> Function<String, Function<String, Function<String, T>>> safeCall(Optional<T> optional) {
        return (resource) -> (key) -> (value) ->
                optional.orElseThrow(() -> new BalanceAppException("There is no %s with %s %s.".formatted(resource, key, value)));
    }
}
