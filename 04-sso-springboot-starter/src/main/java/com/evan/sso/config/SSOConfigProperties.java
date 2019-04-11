package com.evan.sso.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="sso")
@Data
public class SSOConfigProperties {
    private boolean enable;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private String realName;
    private String retryCount;
    private String retryTimerInterval;
    private String ssoFilterUrlPattern;
}
