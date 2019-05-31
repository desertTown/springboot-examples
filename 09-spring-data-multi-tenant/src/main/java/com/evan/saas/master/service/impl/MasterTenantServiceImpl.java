package com.evan.saas.master.service.impl;

import com.evan.saas.master.model.MasterTenant;
import com.evan.saas.master.repository.MasterTenantRepository;
import com.evan.saas.master.service.MasterTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("masterTenantService")
public class MasterTenantServiceImpl implements MasterTenantService {

    private MasterTenantRepository masterTenantRepository;

    @Autowired
    MasterTenantServiceImpl(MasterTenantRepository masterTenantRepository){
        this.masterTenantRepository = masterTenantRepository;
    }

    @Override
    public MasterTenant findByTenant(String tenant) {
        return masterTenantRepository.findByTenant(tenant);
    }
}
