package com.jdc.balance.balanceapi.controller.member.output;

import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.EnterBy;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EntryDetails(
        UUID id,
        EntryType type,
        String ledgerName,
        String particular,
        LocalDate issueAt,
        EnterBy enterBy,
        List<EntryDetailsItem> items
) {

    public static EntryDetails from(LedgerEntry entity) {
        return new EntryDetails(
                entity.getId(),
                entity.getLedger().getType(),
                entity.getLedger().getName(),
                entity.getParticular(),
                entity.getIssueDate(),
                entity.getEnterBy(),
                entity.getItems().stream().map(EntryDetailsItem::from).toList()
        );
    }
}
