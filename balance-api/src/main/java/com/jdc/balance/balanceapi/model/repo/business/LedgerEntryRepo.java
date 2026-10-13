package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;

import java.util.UUID;

public interface LedgerEntryRepo extends BaseRepository<LedgerEntry, UUID> {
}
