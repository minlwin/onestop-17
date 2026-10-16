package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Partner extends AbstractEntity {

    @Id
    private UUID id;

    @OneToOne(optional = false)
    private Account account;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @OneToOne(mappedBy = "partner")
    private Company company;

    @Column(nullable = false)
    private String position;
}
