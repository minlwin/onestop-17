package com.jdc.balance.balanceapi.controller.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.ActivationForm;
import com.jdc.balance.balanceapi.model.dto.ModificationResult;
import com.jdc.balance.balanceapi.service.anonymous.AccountActivationService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("anonymous/activate")
public class AccountActivationApi {

    private final AccountActivationService service;

    @PostMapping("{id}")
    ModificationResult<String> activate(@PathVariable String id,
                                        @Validated @RequestBody ActivationForm form) {
        return service.activate(id, form);
    }
}
