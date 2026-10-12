package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Member extends AbstractEntity {

    @Id
    private UUID id;

    @MapsId
    @OneToOne(optional = false)
    private Account account;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate assignDate;
    private LocalDate resignDate;
}
