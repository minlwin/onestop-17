package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import com.jdc.balance.balanceapi.service.commons.PlanInfoMasterService;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanInfoManagementService {

    private final PlanInfoRepo repo;
    private final PlanInfoMasterService masterService;

    public List<PlanInfoDto> findAll() {
        return masterService.findAll();
    }

    public PlanInfoDto findById(int id) {
        return masterService.findById(id);
    }

    @Transactional
    @CachePut(value = "planinfo.master", key = "#result.id()")
    @CacheEvict(value = "planinfo.list", allEntries = true)
    public PlanInfoDto create(PlanInfoForm form) {
        var entity = new PlanInfo();

        entity.setName(form.name());
        entity.setPrice(form.price());
        entity.setMaxEmployee(form.maxEmployee());
        entity.setMaxLedger(form.maxLedger());
        entity.setMaxEntry(form.maxEntry());
        entity.setDescription(form.description());
        entity.setFeatures(form.features());

        if(form.price() == 0) {

            if(repo.countByDefaultPlanIsTrue() > 0L) {
                throw new BalanceAppException("Only one default plan is available.");
            }

            entity.setDefaultPlan(true);
        }

        entity = repo.save(entity);
        return PlanInfoDto.from(entity);
    }

    @Transactional
    @CachePut(value = "planinfo.master", key = "#result.id()")
    @CacheEvict(value = "planinfo.list", allEntries = true)
    public PlanInfoDto update(int id, PlanInfoForm form) {

        var entity = safeCall(repo.findById(id))
                .apply("Subscription Plan Master").apply("id").apply(String.valueOf(id));

        entity.setName(form.name());
        entity.setPrice(form.price());
        entity.setMaxEmployee(form.maxEmployee());
        entity.setMaxLedger(form.maxLedger());
        entity.setMaxEntry(form.maxEntry());
        entity.setDescription(form.description());
        entity.setFeatures(form.features());

        if(form.price() == 0) {
            if(!entity.isDefaultPlan() && repo.countByDefaultPlanIsTrue() > 0L) {
                throw new BalanceAppException("Only one default plan is available.");
            }
            entity.setDefaultPlan(true);
        } else {
            entity.setDefaultPlan(false);
        }

        return PlanInfoDto.from(entity);
    }
}
