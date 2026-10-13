package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.RegistrationSearch;
import com.jdc.balance.balanceapi.controller.management.input.RegistrationStatusForm;
import com.jdc.balance.balanceapi.controller.management.output.RegistrationDetails;
import com.jdc.balance.balanceapi.controller.management.output.RegistrationListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.service.management.RegistrationManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/registrations")
public class RegistrationManagementApi {

    private final RegistrationManagementService service;

    @GetMapping
    PageResult<RegistrationListItem> search(
            RegistrationSearch form,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return service.search(form, page, size);
    }

    @GetMapping("{id}")
    RegistrationDetails findById(@PathVariable String id) {
        return  null;
    }

    @PutMapping("{id}")
    ModificationResult<UUID> update(@PathVariable String id, @Validated @RequestBody RegistrationStatusForm form) {
        return  null;
    }
}
