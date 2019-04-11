package com.evan.sso.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SSOConfig {

    private String username;
    private String password;
    private String ssoFilterUrlPattern;


}
