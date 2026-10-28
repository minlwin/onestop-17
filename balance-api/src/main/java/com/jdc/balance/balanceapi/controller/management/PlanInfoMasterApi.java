package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.management.PlanInfoManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/master/plans")
public class PlanInfoMasterApi {

    private final PlanInfoManagementService service;

    @GetMapping
    List<PlanInfoDto> findAll() {
        return  service.findAll();
    }

    @GetMapping("{id}")
    PlanInfoDto findById(@PathVariable int id) {
        return  service.findById(id);
    }

    @PostMapping
    ModificationResult<Integer> create(@Validated @RequestBody PlanInfoForm form) {
        var dto = service.create(form);
        return  new ModificationResult<>(dto.id());
    }

    @PutMapping("{id}")
    ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody PlanInfoForm form) {
        var dto = service.update(id, form);
        return new ModificationResult<>(dto.id());
    }
}
