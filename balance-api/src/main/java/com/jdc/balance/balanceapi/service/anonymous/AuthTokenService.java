package com.jdc.balance.balanceapi.service.anonymous;

import static com.jdc.balance.balanceapi.utils.OptionalUtils.safeCall;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.jdc.balance.balanceapi.controller.anonymous.input.TokenGenerateForm;
import com.jdc.balance.balanceapi.controller.anonymous.input.TokenRefreshForm;
import com.jdc.balance.balanceapi.controller.anonymous.ouput.TokenResponse;
import com.jdc.balance.balanceapi.model.repo.account.AccountRepo;
import com.jdc.balance.balanceapi.utils.security.AuthTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    private final AuthenticationManager authenticationManager;
    private final AuthTokenProvider tokenProvider;
    private final AccountRepo accountRepo;

    public TokenResponse generate(TokenGenerateForm form) {
        var usernamePasswordToken = UsernamePasswordAuthenticationToken.unauthenticated(form.email(), form.password());
        var authentication = authenticationManager.authenticate(usernamePasswordToken);
        return getResponse(authentication);
    }

    public TokenResponse refresh(TokenRefreshForm form) {
        var authentication = tokenProvider.parseToken(AuthTokenProvider.Type.Refresh, form.token());
        return getResponse(authentication);
    }

    private TokenResponse getResponse(Authentication authentication) {

        var account = safeCall(accountRepo.findOneByUsername(authentication.getName()))
                .apply("Account").apply("Email").apply(authentication.getName());

        var accessToken = tokenProvider.generateToken(AuthTokenProvider.Type.Access, authentication);
        var refreshToken = tokenProvider.generateToken(AuthTokenProvider.Type.Refresh, authentication);

        return new TokenResponse(
                account.getName(),
                account.getUsername(),
                account.getRole(),
                accessToken,
                refreshToken);
    }

}
