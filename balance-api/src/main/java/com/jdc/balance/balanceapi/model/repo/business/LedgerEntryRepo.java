package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.business.LedgerEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface LedgerEntryRepo extends BaseRepository<LedgerEntry, UUID> {
    @Query("select count(e.id) from LedgerEntry e where e.enterBy.accountId = :accountId")
    Long findCountForEmployee(@Param("accountId") UUID accountId);
}
