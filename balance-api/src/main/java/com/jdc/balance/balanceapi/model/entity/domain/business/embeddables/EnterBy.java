package com.jdc.balance.balanceapi.model.entity.domain.business.embeddables;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record EnterBy(
        UUID accountId,
        String name
) {
}
