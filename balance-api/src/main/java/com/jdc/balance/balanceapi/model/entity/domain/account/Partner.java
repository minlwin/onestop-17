package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Partner extends AbstractEntity {

    @Id
    private UUID id;

    @OneToOne(optional = false)
    private Account account;

    @OneToOne(mappedBy = "partner")
    private Company company;
}
