package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.dto.PageResult;

import java.math.BigDecimal;

public record BalanceSearchResult(
        BigDecimal openingBalance,
        BigDecimal closingBalance,
        PageResult<BalanceListItem> result
) {

}
