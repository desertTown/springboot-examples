package com.evan.saas.master.service;


import com.evan.saas.master.model.MasterTenant;

public interface MasterTenantService {
    /**
     * Using custom tenant name query
     * @param tenant    tenant name
     * @return          masterTenant
     */
    MasterTenant findByTenant(String tenant);
}
