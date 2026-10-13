package com.jdc.balance.balanceapi.model.entity.domain.master;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class PaymentInfo extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String accountNo;

    @Column(nullable = false)
    private String accountName;

    private boolean disabled;
}
