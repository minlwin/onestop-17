package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Employee extends AbstractEntity {
    @Id
    private UUID id;

    @MapsId
    @OneToOne(optional = false)
    private Account account;

    @ManyToOne(optional = false)
    private Company company;
}
