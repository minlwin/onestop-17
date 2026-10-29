package com.jdc.balance.balanceapi.controller.management.output;

import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Company_;
import com.jdc.balance.balanceapi.model.entity.domain.account.Partner_;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription_;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk_;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record SubscriptionListItem(
        UUID id,
        int revision,
        String partnerName,
        String phone,
        String email,
        String company,
        int planId,
        String planName,
        int months,
        BigDecimal monthlyFee,
        LocalDateTime paidAt,
        SubscriptionStatus status,
        LocalDateTime appliedAt,
        LocalDateTime approvedAt,
        LocalDate subscribedAt
) {
    public static void select(CriteriaQuery<SubscriptionListItem> cq, CriteriaBuilder cb, Root<Subscription> root) {
        cq.select(cb.construct(SubscriptionListItem.class,
            root.get(Subscription_.id).get(SubscriptionPk_.companyId),
                root.get(Subscription_.id).get(SubscriptionPk_.revision),
            root.get(Subscription_.company).get(Company_.partner).get(Partner_.account).get(Account_.name),
            root.get(Subscription_.company).get(Company_.partner).get(Partner_.phone),
            root.get(Subscription_.company).get(Company_.partner).get(Partner_.email),
            root.get(Subscription_.company).get(Company_.name),
            root.get(Subscription_.planInfo).get(PlanInfo_.id),
            root.get(Subscription_.planInfo).get(PlanInfo_.name),
            root.get(Subscription_.monthlyFee),
            root.get(Subscription_.createdAt),
            root.get(Subscription_.status),
            root.get(Subscription_.appliedAt),
            root.get(Subscription_.approvedAt),
            root.get(Subscription_.subscriptionDate)
        ));
    }

    public BigDecimal getAmount() {
        return monthlyFee.multiply(BigDecimal.valueOf(months));
    }
}
