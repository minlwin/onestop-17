package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.RetirementForm;
import com.jdc.balance.balanceapi.controller.management.input.UserForm;
import com.jdc.balance.balanceapi.controller.management.input.UserSearch;
import com.jdc.balance.balanceapi.controller.management.output.UserDetails;
import com.jdc.balance.balanceapi.controller.management.output.UserListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.management.UserManagementService;
import com.jdc.balance.balanceapi.utils.listener.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/users")
public class UserManagementApi {

    private final UserManagementService service;
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping
    List<UserListItem> search(UserSearch form) {
        return service.search(form);
    }

    @GetMapping("{id}")
    UserDetails findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody UserForm form) {
        var result = service.create(form);
        eventPublisher.publishEvent(new AccountCreationEvent(result.result()));
        return  result;
    }

    @PutMapping("{id}")
    ModificationResult<UUID> updateRetirement(
            @PathVariable String id,
            @Validated @RequestBody RetirementForm form) {
        return service.update(id, form);
    }
}
