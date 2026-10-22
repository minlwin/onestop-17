package com.jdc.balance.balanceapi.controller.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.SignUpForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.anonymous.SignUpService;
import com.jdc.balance.balanceapi.utils.listener.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("anonymous/signup")
public class SignUpApi {

    private final SignUpService service;
    private final ApplicationEventPublisher eventPublisher;

    @PostMapping
    ModificationResult<UUID> signUp(@Validated @RequestBody SignUpForm form) {
        var result = service.signUp(form);
        eventPublisher.publishEvent(new AccountCreationEvent(result.result()));
        return  result;
    }
}
