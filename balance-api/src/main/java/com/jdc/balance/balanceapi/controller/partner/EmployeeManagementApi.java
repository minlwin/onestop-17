package com.jdc.balance.balanceapi.controller.partner;

import com.jdc.balance.balanceapi.controller.partner.input.EmployeeForm;
import com.jdc.balance.balanceapi.controller.partner.input.EmployeeSearch;
import com.jdc.balance.balanceapi.controller.partner.output.EmployeeDetails;
import com.jdc.balance.balanceapi.controller.partner.output.EmployeeListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.partner.EmployeeManagementService;
import com.jdc.balance.balanceapi.utils.listener.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("partner/employee")
public class EmployeeManagementApi {

    private final EmployeeManagementService service;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping
    List<EmployeeListItem> search(EmployeeSearch form) {
        return service.search(form);
    }

    @GetMapping("{id}")
    EmployeeDetails findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody EmployeeForm form) {
        var result = service.create(form);
        eventPublisher.publishEvent(new AccountCreationEvent(result.result()));
        return result;
    }
}
