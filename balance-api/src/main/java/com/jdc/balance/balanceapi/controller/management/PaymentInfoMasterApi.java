package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.PaymentInfoForm;
import com.jdc.balance.balanceapi.controller.management.output.PaymentInfoDto;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/master/payments")
public class PaymentInfoMasterApi {

    @GetMapping
    List<PaymentInfoDto> findAll() {
        return  List.of();
    }

    @GetMapping("{id}")
    PaymentInfoDto findById(@PathVariable int id) {
        return  null;
    }

    @PostMapping
    ModificationResult<Integer> create(@Validated @RequestBody PaymentInfoForm form) {
        return  null;
    }

    @PutMapping("{id}")
    ModificationResult<Integer> update(@PathVariable int id, @Validated @RequestBody PaymentInfoForm form) {
        return  null;
    }
}
