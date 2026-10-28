package com.jdc.balance.balanceapi.service.commons;

import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanInfoMasterService {

    private final PlanInfoRepo repo;

    @Cacheable("planinfo.list")
    public List<PlanInfoDto> findAll() {
        return repo.findAll().stream().map(PlanInfoDto::from).toList();
    }

    @Cacheable(value = "planinfo.master", key = "#id")
    public PlanInfoDto findById(int id) {
        return safeCall(repo.findById(id).map(PlanInfoDto::from))
                .apply("Subscription Plan Master").apply("id").apply(String.valueOf(id));
    }

    public PlanInfo getReference(Integer id) {
        return repo.getReferenceById(id);
    }
}
