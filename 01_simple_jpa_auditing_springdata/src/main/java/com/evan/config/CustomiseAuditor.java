package com.evan.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("customerAuditor")
public class CustomiseAuditor implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // User info should be handle here
        return Optional.of("Fake user!!!");
    }
}
