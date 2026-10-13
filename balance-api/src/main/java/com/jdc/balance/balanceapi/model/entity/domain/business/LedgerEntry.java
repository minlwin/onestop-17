package com.jdc.balance.balanceapi.model.entity.domain.business;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.EnterBy;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode
public class LedgerEntry extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    private Ledger ledger;

    @Column(nullable = false)
    private EntryType type;

    @Column(nullable = false)
    private LocalDate issueDate;

    @OneToMany(mappedBy = "entry")
    private List<LedgerEntryItem> items;

    private EnterBy enterBy;
}
