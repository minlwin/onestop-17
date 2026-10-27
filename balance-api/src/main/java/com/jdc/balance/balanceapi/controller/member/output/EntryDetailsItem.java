package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntryItem;

import java.math.BigDecimal;

public record EntryDetailsItem(
        String article,
        int quantity,
        BigDecimal unitPrice
) {
    public BigDecimal getAmount() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public static EntryDetailsItem from(LedgerEntryItem entity) {
        return new EntryDetailsItem(entity.getArticle(), entity.getQuantity(), entity.getUnitPrice());
    }
}
