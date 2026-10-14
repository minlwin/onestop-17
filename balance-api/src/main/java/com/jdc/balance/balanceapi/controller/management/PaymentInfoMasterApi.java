package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.PaymentInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.management.PaymentInfoMasterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/master/payments")
public class PaymentInfoMasterApi {

    private final PaymentInfoMasterService service;

    @GetMapping
    List<PaymentInfoDto> findAll() {
        return  service.findAll();
    }

    @GetMapping("{id}")
    PaymentInfoDto findById(@PathVariable int id) {
        return  service.findById(id);
    }

    @PostMapping
    ModificationResult<Integer> create(@Validated @RequestBody PaymentInfoForm form) {
        return  service.create(form);
    }

    @PutMapping("{id}")
    ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody PaymentInfoForm form) {
        return  service.update(id, form);
    }
}
