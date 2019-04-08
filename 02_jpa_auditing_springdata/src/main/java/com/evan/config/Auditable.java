package com.evan.config;

import java.util.Date;

public interface Auditable {
    String getCreatedByDomainId();

    void setCreatedByDomainId(String createdByDomainId);

    String getCreatedByGsid();

    void setCreatedByGsid(String createdByGsid);

    String getUpdatedByDomainId();

    void setUpdatedByDomainId(String updatedByDomainId);

    String getUpdatedByGsid();

    void setUpdatedByGsid(String updatedByGsid);

    Date getCreatedTime();

    void setCreatedTime(Date createdTime);

    Date getUpdatedTime();

    void setUpdatedTime(Date updatedTime);
}
