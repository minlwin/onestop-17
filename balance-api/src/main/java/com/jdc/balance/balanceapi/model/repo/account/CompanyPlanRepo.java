package com.jdc.balance.balanceapi.model.repo.account;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.account.CompanyPlan;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CompanyPlanRepo extends BaseRepository<CompanyPlan, UUID> {

    List<CompanyPlan> findByExpiration(LocalDate date);
}
