package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.RetirementForm;
import com.jdc.balance.balanceapi.controller.management.input.UserForm;
import com.jdc.balance.balanceapi.controller.management.input.UserSearch;
import com.jdc.balance.balanceapi.controller.management.output.UserDetails;
import com.jdc.balance.balanceapi.controller.management.output.UserListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/users")
public class UserManagementApi {

    @GetMapping
    List<UserListItem> search(UserSearch form) {
        return null;
    }

    @GetMapping("id")
    UserDetails findById(@PathVariable String id) {
        return null;
    }

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody UserForm form) {
        return null;
    }

    @PutMapping("{id}")
    ModificationResult<UUID> updateRetirement(
            @PathVariable String id,
            @Validated @RequestBody RetirementForm form) {
        return null;
    }
}
