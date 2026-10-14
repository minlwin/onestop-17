package com.jdc.balance.balanceapi.model.repo.account;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepo extends BaseRepository<Account, UUID> {
    Optional<Account> findOneByUsername(String username);
}
