package com.jdc.balance.balanceapi.model.entity.domain.business;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Subscription extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    private Company company;

    @ManyToOne(optional = false)
    private PlanInfo planInfo;

    @Column(nullable = false)
    private int months;

    @Column(nullable = false)
    private LocalDate subscriptionDate;

    @Column(nullable = false)
    private BigDecimal monthlyFee;

    @ManyToOne(optional = false)
    private PaymentInfo payment;

    @Column(nullable = false)
    private String paymentSlip;

    @Column(nullable = false)
    private SubscriptionStatus status;
    private String remark;

    @Column(nullable = false)
    private LocalDateTime appliedAt;

    private LocalDateTime approvedAt;
}
