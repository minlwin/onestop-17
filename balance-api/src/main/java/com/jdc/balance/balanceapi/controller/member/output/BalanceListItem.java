package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.business.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.UUID;

public record BalanceListItem(
        UUID id,
        String ledgerName,
        String particular,
        BigDecimal debit,
        BigDecimal credit
) {
    public static void select(CriteriaBuilder cb, CriteriaQuery<BalanceListItem> cq, Root<LedgerEntry> root, ListJoin<LedgerEntry, LedgerEntryItem> items) {
        var ledger = root.get(LedgerEntry_.ledger);
        var total = cb.sum(cb.prod(items.get(LedgerEntryItem_.quantity), items.get(LedgerEntryItem_.unitPrice)));

        cq.select(cb.construct(BalanceListItem.class,
                root.get(LedgerEntry_.id),
                ledger.get(Ledger_.name),
                root.get(LedgerEntry_.particular),
                cb.selectCase()
                        .when(cb.equal(ledger.get(Ledger_.type), EntryType.Debit), total)
                        .otherwise(BigDecimal.ZERO),
                cb.selectCase()
                        .when(cb.equal(ledger.get(Ledger_.type), EntryType.Credit), total)
                        .otherwise(BigDecimal.ZERO)
        ));
    }
}
