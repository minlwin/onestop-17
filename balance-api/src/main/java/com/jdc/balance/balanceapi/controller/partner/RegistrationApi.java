package com.jdc.balance.balanceapi.controller.partner;

import com.jdc.balance.balanceapi.controller.partner.input.RegistrationForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.partner.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("partner/registration")
public class RegistrationApi {

    private final RegistrationService service;

    @PostMapping
    ModificationResult<UUID> create(@Validated @RequestBody RegistrationForm form) {
        return service.register(form);
    }
}
