package com.jdc.balance.balanceapi.controller.partner.input;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

public record SubscriptionForm(
        @NotNull(message = "Please select plan")
        Integer planId,
        @NotNull(message = "Please select payment information")
        Integer paymentInfoId,
        @NotNull(message = "Please enter subscription date")
        LocalDate subscriptionDate,
        @NotNull(message = "Please select payment slip file")
        MultipartFile paymentSlip
) {
}
