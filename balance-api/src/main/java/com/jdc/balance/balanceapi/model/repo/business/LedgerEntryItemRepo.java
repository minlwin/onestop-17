package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntryItem;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.LedgerEntryItemPk;

public interface LedgerEntryItemRepo extends BaseRepository<LedgerEntryItem, LedgerEntryItemPk> {
}
