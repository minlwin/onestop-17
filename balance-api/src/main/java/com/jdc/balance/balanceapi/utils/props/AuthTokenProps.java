package com.jdc.balance.balanceapi.utils.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.token")
public class AuthTokenProps {

    private String issuer;
    private int accessLife;
    private int refreshLife;
}
