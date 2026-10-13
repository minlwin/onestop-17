package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;

import java.util.UUID;

public interface SubscriptionRepo extends BaseRepository<Subscription, UUID> {
}
