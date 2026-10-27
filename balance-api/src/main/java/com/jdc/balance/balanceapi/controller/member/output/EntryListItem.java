package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.business.*;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.EnterBy_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record EntryListItem(
        UUID id,
        LocalDate entryDate,
        String enterBy,
        EntryType type,
        String ledgerName,
        String particular,
        BigDecimal amount
) {
    public static void select(CriteriaBuilder cb, CriteriaQuery<EntryListItem> cq, Root<LedgerEntry> root, ListJoin<LedgerEntry, LedgerEntryItem> items) {
        cq.select(cb.construct(EntryListItem.class,
                root.get(LedgerEntry_.id),
                root.get(LedgerEntry_.issueDate),
                root.get(LedgerEntry_.enterBy).get(EnterBy_.name),
                root.get(LedgerEntry_.ledger).get(Ledger_.type),
                root.get(LedgerEntry_.ledger).get(Ledger_.name),
                root.get(LedgerEntry_.particular),
                cb.sum(cb.prod(items.get(LedgerEntryItem_.quantity), items.get(LedgerEntryItem_.unitPrice)))
        ));
    }
}
