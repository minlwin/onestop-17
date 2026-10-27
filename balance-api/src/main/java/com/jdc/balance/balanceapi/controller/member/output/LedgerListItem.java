package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.business.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.UUID;

public record LedgerListItem(
        UUID id,
        EntryType type,
        String name,
        String description,
        boolean disabled,
        BigDecimal total) {

    public static void select(CriteriaBuilder cb, CriteriaQuery<LedgerListItem> cq, Root<Ledger> root, ListJoin<LedgerEntry, LedgerEntryItem> items) {
        cq.select(cb.construct(LedgerListItem.class,
                root.get(Ledger_.id),
                root.get(Ledger_.type),
                root.get(Ledger_.name),
                root.get(Ledger_.description),
                root.get(Ledger_.disabled),
                cb.sum(cb.prod(
                        items.get(LedgerEntryItem_.unitPrice),
                        items.get(LedgerEntryItem_.quantity)))
                ));
    }
}
