package com.evan.config;

import com.evan.utils.SessionContextHolder;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorBean implements AuditorAware<StaffAuditor> {
    @Override
    public Optional<StaffAuditor> getCurrentAuditor() {
        return Optional.of(SessionContextHolder.get());
    }
}
