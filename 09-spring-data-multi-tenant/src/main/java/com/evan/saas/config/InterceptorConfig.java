package com.evan.saas.config;

import com.evan.saas.interceptor.TenantInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TenantInterceptor()).addPathPatterns("/**").excludePathPatterns("/login.html");
        super.addInterceptors(registry);
    }
}
