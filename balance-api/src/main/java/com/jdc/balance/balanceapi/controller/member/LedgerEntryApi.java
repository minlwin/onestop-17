package com.jdc.balance.balanceapi.controller.member;

import com.jdc.balance.balanceapi.controller.member.input.EntryForm;
import com.jdc.balance.balanceapi.controller.member.input.EntrySearch;
import com.jdc.balance.balanceapi.controller.member.output.EntryDetails;
import com.jdc.balance.balanceapi.controller.member.output.EntryListItem;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.model.dto.PageResult;
import com.jdc.balance.balanceapi.service.member.LedgerEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("member/entry")
public class LedgerEntryApi {

    private final LedgerEntryService service;

    @GetMapping
    PageResult<EntryListItem> search(EntrySearch form,
                                     @RequestParam(required = false, defaultValue = "0") int page,
                                     @RequestParam(required = false, defaultValue = "10") int size) {
        return service.search(form, page, size);
    }

    @GetMapping("{id}")
    EntryDetails findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody EntryForm form) {
        return service.create(form);
    }
}
