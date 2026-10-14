package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.management.PlanInfoMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/master/plans")
public class PlanInfoMasterApi {

    private final PlanInfoMasterService service;

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
        return service.create(form);
    }

    @PutMapping("{id}")
    ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody PlanInfoForm form) {
        return service.update(id, form);
    }
}
