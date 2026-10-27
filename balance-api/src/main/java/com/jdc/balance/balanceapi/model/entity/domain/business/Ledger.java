package com.jdc.balance.balanceapi.model.entity.domain.business;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Ledger extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    private Company company;

    @Column(nullable = false)
    private EntryType type;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    private boolean disabled;

    @OneToMany(mappedBy = "ledger")
    private List<LedgerEntry> entry;
}
