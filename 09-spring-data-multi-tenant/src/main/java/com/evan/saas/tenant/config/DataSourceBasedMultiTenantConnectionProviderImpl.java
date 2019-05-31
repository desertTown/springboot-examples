package com.evan.saas.tenant.config;

import com.evan.saas.common.util.DataSourceUtils;
import com.evan.saas.master.model.MasterTenant;
import com.evan.saas.master.repository.MasterTenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


@Slf4j
@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl{

    private static final long serialVersionUID = -7522287771874314380L;

    @Autowired
    private MasterTenantRepository masterTenantRepository;

    private Map<String,DataSource> dataSources = new TreeMap<>();

    @Override
    protected DataSource selectAnyDataSource() {
        //  程序启动初始化的时候会调用这里初始化默认的数据源
        if(dataSources.isEmpty()){
            List<MasterTenant> tenants = masterTenantRepository.findAll();
            tenants.forEach(masterTenant->{
                dataSources.put(masterTenant.getTenant(), DataSourceUtils.wrapperDataSource(masterTenant));
            });
        }
        return dataSources.values().iterator().next();
    }

    @Override
    protected DataSource selectDataSource(String tenant) {
        //  调用具体sql语句 之前在这里切换数据源
        if(!dataSources.containsKey(tenant)){
            List<MasterTenant> tenants = masterTenantRepository.findAll();
            tenants.forEach(masterTenant->{
                dataSources.put(masterTenant.getTenant(),DataSourceUtils.wrapperDataSource(masterTenant));
            });
        }
        return dataSources.get(tenant);
    }
}
