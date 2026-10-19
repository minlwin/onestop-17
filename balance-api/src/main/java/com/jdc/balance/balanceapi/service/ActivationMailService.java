package com.jdc.balance.balanceapi.service;

import com.jdc.balance.balanceapi.model.dto.OtpResult;
import com.jdc.balance.balanceapi.model.entity.domain.account.Account;
import com.jdc.balance.balanceapi.utils.exceptions.BalanceAppException;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivationMailService {

    private final JavaMailSender mailSender;

    @Value("${app.activation.link}")
    private String activationLink;

    public void send(Account account, OtpResult otpResult) {

        try {
            var message = mailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message);

            var href = "%s/%s".formatted(activationLink, otpResult.id());

            helper.setTo(account.getUsername());
            helper.setSubject("Account Activation");
            helper.setText("""
                    <h1>Account Activation</h1>
                    
                    <p>Hi %s,<br />
                       Welcome to Balance Management Application! We are excited to have you on board. <br />
                       To finish setting up your profile and unlock full access, please activate your account by clicking the link below:
                    </p>
                    
                    <p>Your activation code : %s</p>
                       
                    <a href="%s">Activate Your account</a>   
                    """.formatted(account.getName(), otpResult.otpCode(), href), true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new BalanceAppException(e);
        }
    }
}
