package com.jdc.balance.balanceapi.model.entity.domain.business;

import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.LedgerEntryItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@Entity
public class LedgerEntryItem {

    @EmbeddedId
    private LedgerEntryItemPk id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "entry_id", referencedColumnName = "id", insertable = false, updatable = false)
    private LedgerEntry entry;

    private String article;
    private BigDecimal unitPrice;
    private int quantity;
}
