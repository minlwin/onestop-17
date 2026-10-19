package com.jdc.balance.balanceapi.model.dto;

import java.util.UUID;

public record OtpResult(
        UUID id,
        String otpCode
) {
}
