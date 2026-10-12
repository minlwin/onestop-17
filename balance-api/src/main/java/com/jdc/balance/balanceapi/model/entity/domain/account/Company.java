package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Company extends AbstractEntity {

    @Id
    private UUID id;

    @MapsId
    @OneToOne(optional = false)
    private Partner partner;
}
