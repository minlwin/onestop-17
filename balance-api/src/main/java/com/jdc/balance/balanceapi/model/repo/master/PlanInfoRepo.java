package com.jdc.balance.balanceapi.model.repo.master;

import com.jdc.balance.balanceapi.model.BaseRepository;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlanInfoRepo extends BaseRepository<PlanInfo, Integer> {
    long countByDefaultPlanIsTrue();

    @Query("select p from PlanInfo p order by p.price")
    List<PlanInfo> getAvailablePlans();

    Optional<PlanInfo> findOneByDefaultPlanIsTrue();
}
