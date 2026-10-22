package com.jdc.balance.balanceapi.service.management;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.domain.master.PlanInfo;
import com.jdc.balance.balanceapi.model.repo.master.PlanInfoRepo;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanInfoMasterService {

    private final PlanInfoRepo repo;

    public List<PlanInfoDto> findAll() {
        return repo.findAll().stream().map(PlanInfoDto::from).toList();
    }

    public PlanInfoDto findById(int id) {
        return safeCall(repo.findById(id).map(PlanInfoDto::from))
                .apply("Subscription Plan Master").apply("id").apply(String.valueOf(id));
    }

    @Transactional
    public ModificationResult<Integer> create(PlanInfoForm form) {
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
        return new ModificationResult<>(entity.getId());
    }

    @Transactional
    public ModificationResult<Integer> update(int id, PlanInfoForm form) {

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

        return new ModificationResult<>(entity.getId());
    }
}
