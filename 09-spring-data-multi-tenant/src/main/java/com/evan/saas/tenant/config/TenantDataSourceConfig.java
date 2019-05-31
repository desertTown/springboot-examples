package com.evan.saas.tenant.config;

import com.evan.saas.tenant.model.User;
import com.evan.saas.tenant.repository.UserRepository;
import com.evan.saas.tenant.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {
        "com.evan.saas.tenant.model",
        "com.evan.saas.tenant.repository"
})
@EnableJpaRepositories(basePackages = {
        "com.evan.saas.tenant.repository",
        "com.evan.saas.tenant.service"
},entityManagerFactoryRef = "tenantEntityManagerFactory"
,transactionManagerRef = "tenantTransactionManager")
public class TenantDataSourceConfig {

    @Bean("jpaVendorAdapter")
    public JpaVendorAdapter jpaVendorAdapter(){
        return new HibernateJpaVendorAdapter();
    }

    @Bean(name = "tenantTransactionManager")
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean(name = "datasourceBasedMultiTenantConnectionProvider")
    @ConditionalOnBean(name = "masterEntityManagerFactory")
    public MultiTenantConnectionProvider multiTenantConnectionProvider(){
        return new DataSourceBasedMultiTenantConnectionProviderImpl();
    }

    @Bean(name = "currentTenantIdentifierResolver")
    public CurrentTenantIdentifierResolver currentTenantIdentifierResolver(){
        return new CurrentTenantIdentifierResolverImpl();
    }

    @Bean(name = "tenantEntityManagerFactory")
    @ConditionalOnBean(name = "datasourceBasedMultiTenantConnectionProvider")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasourceBasedMultiTenantConnectionProvider")MultiTenantConnectionProvider connectionProvider,
            @Qualifier("currentTenantIdentifierResolver")CurrentTenantIdentifierResolver tenantIdentifierResolver
    ){
        LocalContainerEntityManagerFactoryBean localBean = new LocalContainerEntityManagerFactoryBean();
        localBean.setPackagesToScan(
                new String[]{
                        User.class.getPackage().getName(),
                        UserRepository.class.getPackage().getName(),
                        UserService.class.getPackage().getName()

                }
        );
        localBean.setJpaVendorAdapter(jpaVendorAdapter());
        localBean.setPersistenceUnitName("tenant-database-persistence-unit");
        Map<String,Object> properties = new HashMap<>();
        properties.put(Environment.MULTI_TENANT, MultiTenancyStrategy.SCHEMA);
        properties.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER,connectionProvider);
        properties.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER,tenantIdentifierResolver);
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL,true);
        properties.put(Environment.FORMAT_SQL,true);
        properties.put(Environment.HBM2DDL_AUTO,"update");
        localBean.setJpaPropertyMap(properties);
        return localBean;
    }
}
