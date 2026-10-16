package com.jdc.balance.balanceapi.controller.anonymous.ouput;

import com.jdc.balance.balanceapi.model.entity.consts.AccountRole;

public record TokenResponse(
        String name,
        String username,
        AccountRole role,
        String accessToken,
        String refreshToken
) {
}
