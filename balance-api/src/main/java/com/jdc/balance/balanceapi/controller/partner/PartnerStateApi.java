package com.jdc.balance.balanceapi.controller.partner;

import com.jdc.balance.balanceapi.controller.partner.output.PartnerStateDto;
import com.jdc.balance.balanceapi.service.partner.PartnerStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("partner/state")
public class PartnerStateApi {

    private final PartnerStateService service;

    @GetMapping
    PartnerStateDto getState() {
        return service.findState();
    }
}
