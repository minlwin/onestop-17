package com.jdc.balance.balanceapi.model.entity.domain.account;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class OtpCheckHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false)
    private Account account;

    @Column(nullable = false)
    private String secret;

    @Column(nullable = false)
    private LocalDateTime initializedAt;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    private LocalDateTime checkedAt;
    private boolean success;
}
