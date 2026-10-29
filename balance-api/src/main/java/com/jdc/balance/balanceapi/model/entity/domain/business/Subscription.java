package com.jdc.balance.balanceapi.model.entity.domain.business;

import com.jdc.balance.balanceapi.model.entity.AbstractEntity;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionAction;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.model.entity.domain.master.PaymentInfo;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Subscription extends AbstractEntity {

    @EmbeddedId
    private SubscriptionPk id;

    @ManyToOne
    @JoinColumn(insertable = false, updatable = false)
    private Company company;

    @ManyToOne
    private PlanInfo planInfo;

    @Column(nullable = false)
    private SubscriptionStatus status;
    @Column(nullable = false)
    private SubscriptionAction action;

    @ManyToOne
    private PaymentInfo paymentInfo;
    private BigDecimal monthlyFee;

    private String paymentSlip;
    private LocalDate subscriptionDate;

    private LocalDateTime appliedAt;
    private LocalDateTime approvedAt;
    private String remark;

}
