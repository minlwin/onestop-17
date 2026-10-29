package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class CompanyPlan extends AbstractEntity {

    @Id
    private UUID id;
    private int revision;

    @MapsId
    @OneToOne(optional = false)
    private Company company;

    @ManyToOne
    private PlanInfo planInfo;

    private LocalDate startDate;
    private LocalDate expiration;
}
