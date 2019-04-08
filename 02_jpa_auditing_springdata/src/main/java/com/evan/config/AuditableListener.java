package com.evan.config;

import com.evan.utils.SessionContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;


@Component
public class AuditableListener {
    @PrePersist
    void preCreate(Auditable auditable) {
        StaffAuditor staffAuditor = SessionContextHolder.get();
        auditable.setCreatedByDomainId(staffAuditor.getCreatedByDomainId());
        auditable.setCreatedByGsid(staffAuditor.getCreatedByGsid());
        auditable.setUpdatedByDomainId(staffAuditor.getUpdatedByDomainId());
        auditable.setUpdatedByGsid(staffAuditor.getUpdatedByGsid());

        Date date = new Date();
        auditable.setCreatedTime(date);
        auditable.setUpdatedTime(date);
    }

    @PreUpdate
    void update(Auditable auditable) {
//        StaffAuditor staffAuditor = SessionContextHolder.get();
        auditable.setUpdatedTime(new Date());
        auditable.setUpdatedByDomainId("Fake Evan updated domainId");
        auditable.setUpdatedByGsid("Fake Evan updated gsid");
    }
}
