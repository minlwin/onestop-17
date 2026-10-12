package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.PlanInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PlanInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/master/plans")
public class PlanMasterApi {

    @GetMapping
    List<PlanInfoDto> findAll() {
        return  null;
    }

    @GetMapping("{id}")
    PlanInfoDto findById(@PathVariable int id) {
        return  null;
    }

    @PostMapping
    ModificationResult<Integer> create(@Validated @RequestBody PlanInfoForm form) {
        return null;
    }

    @PutMapping("{id}")
    ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody PlanInfoForm form) {
        return null;
    }
}
