package com.jdc.balance.balanceapi.model.repo.account;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.account.Member;

import java.util.UUID;

public interface MemberRepo extends BaseRepository<Member, UUID> {
}
