package com.jdc.balance.balanceapi.model.dto;

import java.util.List;

public record PageResult<T>(
        List<T> contents,
        long totalCount,
        int page,
        int size
) {
    public long getTotalPage() {
        return 0;
    }

    public List<Integer> getLinks() {
        return  List.of();
    }
}
