package com.jdc.balance.balanceapi.utils.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.activation")
public class ActivationProps {
    private String link;
}
