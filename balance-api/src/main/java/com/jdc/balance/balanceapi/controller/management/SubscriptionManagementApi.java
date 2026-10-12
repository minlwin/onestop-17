package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.SubscriptionSearch;
import com.jdc.balance.balanceapi.controller.management.input.SubscriptionStatusForm;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionDetails;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/subscriptions")
public class SubscriptionManagementApi {

    @GetMapping
    PageResult<SubscriptionListItem> search(
            SubscriptionSearch form,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return null;
    }

    @GetMapping("{id}")
    SubscriptionDetails findById(@PathVariable String id) {
        return null;
    }

    @PutMapping("{id}")
    ModificationResult<UUID> updateStatus(
            @PathVariable String id,
            @Validated @RequestBody SubscriptionStatusForm form) {
        return  null;
    }
}
