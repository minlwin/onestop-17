package com.jdc.balance.balanceapi.model.repo.business;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.consts.SubscriptionStatus;
import com.jdc.balance.balanceapi.model.entity.domain.business.Subscription;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SubscriptionRepo extends BaseRepository<Subscription, SubscriptionPk> {
    @Query("select s from Subscription s where s.id.companyId = :companyId order by s.id.revision desc ")
    List<Subscription> findByCompanyId(UUID companyId);

    @Query("select s from Subscription  s where s.status = :status and s.subscriptionDate = :date order by s.appliedAt")
    List<Subscription> searchForActivate(SubscriptionStatus status, LocalDate date);
}
