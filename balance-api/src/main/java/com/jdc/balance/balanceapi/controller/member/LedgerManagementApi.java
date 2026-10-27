package com.jdc.balance.balanceapi.controller.member;

import com.jdc.balance.balanceapi.controller.member.input.LedgerForm;
import com.jdc.balance.balanceapi.controller.member.input.LedgerSearch;
import com.jdc.balance.balanceapi.controller.member.output.LedgerListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.entity.consts.EntryType;
import com.jdc.balance.balanceapi.service.member.LedgerManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("member/ledger")
public class LedgerManagementApi {

    private final LedgerManagementService service;

    @GetMapping
    List<LedgerListItem> search(LedgerSearch search) {
        return service.search(search);
    }

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody LedgerForm form) {
        return service.create(form);
    }

    @PostMapping("{id}")
    ModificationResult<UUID> update(@PathVariable String id, @Validated @RequestBody LedgerForm form) {
        return service.update(id, form);
    }

}
