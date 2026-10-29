package com.jdc.balance.balanceapi.controller.management;

import com.jdc.balance.balanceapi.controller.management.input.SubscriptionSearch;
import com.jdc.balance.balanceapi.controller.management.input.SubscriptionStatusForm;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionDetails;
import com.jdc.balance.balanceapi.controller.management.output.SubscriptionListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.model.entity.domain.business.embeddables.SubscriptionPk;
import com.jdc.balance.balanceapi.service.management.SubscriptionManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/subscriptions")
public class SubscriptionManagementApi {

    private final SubscriptionManagementService service;

    @GetMapping
    PageResult<SubscriptionListItem> search(
            SubscriptionSearch form,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return service.search(form, page, size);
    }

    @GetMapping("{companyId}/{revision}")
    SubscriptionDetails findById(@PathVariable String companyId, @PathVariable int revision) {
        return service.findById(companyId, revision);
    }

    @PutMapping("{companyId}/{revision}")
    ModificationResult<SubscriptionPk> updateStatus(
            @PathVariable String companyId, @PathVariable int revision,
            @Validated @RequestBody SubscriptionStatusForm form) {
        return  service.update(companyId, revision, form);
    }
}
