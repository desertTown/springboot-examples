package com.evan.sso.config;

import com.evan.sso.filter.LoginFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnProperty(name = "sso.enabled", matchIfMissing = true)
public class SSOAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SSOConfigProperties ssoConfigProperties() {
        return new SSOConfigProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public SSOConfig ssoConfig(SSOConfigProperties ssoConfigProperties) {
        SSOConfig ssoConfig = SSOConfig.builder()
                .password(ssoConfigProperties.getPassword())
                .username(ssoConfigProperties.getUsername())
                .ssoFilterUrlPattern(ssoConfigProperties.getSsoFilterUrlPattern())
                .build();
        return ssoConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFilter loginFilter(SSOConfig ssoConfig) {

        return new LoginFilter(ssoConfig);
    }

    @Bean
    public FilterRegistrationBean<LoginFilter> setLoginFilter(LoginFilter loginFilter) {
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(loginFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add(loginFilter.getSsoConfig().getSsoFilterUrlPattern());
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
