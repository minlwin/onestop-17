package com.jdc.balance.balanceapi.model.entity.domain.account;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.consts.EmployeePermission;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Employee extends AbstractEntity {
    @Id
    private UUID id;

    @MapsId
    @OneToOne(optional = false)
    private Account account;

    @ManyToOne(optional = false)
    private Company company;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private EmployeePermission permission;

    private LocalDate assignDate;
    private LocalDate resignDate;

}
