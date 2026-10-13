package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.business.Ledger;

import java.util.UUID;

public interface LedgerRepo extends BaseRepository<Ledger, UUID> {
}
