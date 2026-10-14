package com.jdc.balance.balanceapi.controller.anonymous;

import com.jdc.balance.balanceapi.controller.anonymous.input.TokenGenerateForm;
import com.jdc.balance.balanceapi.controller.anonymous.input.TokenRefreshForm;
import com.jdc.balance.balanceapi.controller.anonymous.ouput.TokenResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("anonymous/auth")
public class AuthApi {

    @PostMapping("generate")
    TokenResponse generateToken(@Validated @RequestBody TokenGenerateForm form) {
        return null;
    }

    @PostMapping("refresh")
    TokenResponse refreshToken(@Validated @RequestBody TokenRefreshForm form) {
        return null;
    }
}
