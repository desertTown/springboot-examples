package com.evan.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class StaffAuditor implements Serializable{

    private String createdByDomainId;

    private String createdByGsid;

    private String updatedByDomainId;

    private String updatedByGsid;
}
