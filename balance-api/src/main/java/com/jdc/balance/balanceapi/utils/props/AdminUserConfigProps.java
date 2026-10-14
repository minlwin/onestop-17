package com.jdc.balance.balanceapi.utils.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.admin")
public class AdminUserConfigProps {

    private String email;
    private String password;
}
