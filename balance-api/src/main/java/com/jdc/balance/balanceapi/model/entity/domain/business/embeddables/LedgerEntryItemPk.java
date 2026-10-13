package com.jdc.balance.balanceapi.model.entity.domain.business.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record LedgerEntryItemPk(
        @Column(name = "entry_id")
        UUID entryId,
        @Column(name = "seq_number")
        int seqNumber
) {
}
