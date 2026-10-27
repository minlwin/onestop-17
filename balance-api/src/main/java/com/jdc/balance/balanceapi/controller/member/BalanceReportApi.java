package com.jdc.balance.balanceapi.controller.member;

import com.jdc.balance.balanceapi.controller.member.input.BalanceSearch;
import com.jdc.balance.balanceapi.controller.member.output.BalanceListItem;
import com.jdc.balance.balanceapi.controller.member.output.BalanceSearchResult;
import com.jdc.balance.balanceapi.service.member.BalanceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("member/balance")
public class BalanceReportApi {

    private final BalanceReportService service;

    @GetMapping
    BalanceSearchResult search(BalanceSearch form,
                               @RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size) {
        return  service.search(form, page, size);
    }
}
