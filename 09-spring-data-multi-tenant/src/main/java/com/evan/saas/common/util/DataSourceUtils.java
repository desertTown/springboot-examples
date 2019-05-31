package com.evan.saas.common.util;

import com.evan.saas.master.model.MasterTenant;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;


@Slf4j
public class DataSourceUtils {

    public static DataSource wrapperDataSource(MasterTenant tenant){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(tenant.getUsername());
        dataSource.setPassword(tenant.getPassword());
        dataSource.setJdbcUrl(tenant.getUrl());
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setConnectionTimeout(20000);
        dataSource.setMinimumIdle(10);
        dataSource.setMaximumPoolSize(20);
        dataSource.setIdleTimeout(300000);
        dataSource.setConnectionTimeout(20000);
        String tenantId = tenant.getId();
        String tenantConnectionPoolName = tenantId+"-connection-pool";
        dataSource.setPoolName(tenantConnectionPoolName);
        log.info("Configuration datasource:{},Connection pool name:{}",tenant.getId(),tenantConnectionPoolName);
        return dataSource;
    }

}
