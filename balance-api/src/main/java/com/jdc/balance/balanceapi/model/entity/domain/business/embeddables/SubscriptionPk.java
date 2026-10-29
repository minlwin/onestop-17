package com.jdc.balance.balanceapi.model.entity.domain.business.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record SubscriptionPk(
        @Column(name = "company_id")
        UUID companyId,
        int revision
) {
        public static SubscriptionPk from(UUID companyId, int revision) {
                return new SubscriptionPk(companyId, revision);
        }
}
