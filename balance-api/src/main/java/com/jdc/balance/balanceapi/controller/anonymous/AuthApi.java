package com.jdc.balance.balanceapi.controller.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.TokenGenerateForm;
import com.jdc.balance.balanceapi.controller.anonymous.input.TokenRefreshForm;
import com.jdc.balance.balanceapi.controller.anonymous.ouput.TokenResponse;
import com.jdc.balance.balanceapi.service.anonymous.AuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("anonymous/auth")
public class AuthApi {

    private final AuthTokenService tokenService;

    @PostMapping("generate")
    TokenResponse generateToken(@Validated @RequestBody TokenGenerateForm form) {
        return tokenService.generate(form);
    }

    @PostMapping("refresh")
    TokenResponse refreshToken(@Validated @RequestBody TokenRefreshForm form) {
        return tokenService.refresh(form);
    }
}
