package com.evan.saas.master.config;

import com.evan.saas.master.config.properties.MasterDatabaseProperties;
import com.evan.saas.master.model.MasterTenant;
import com.evan.saas.master.repository.MasterTenantRepository;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.evan.saas.master.model","com.evan.saas.master.repository"},
                       entityManagerFactoryRef = "masterEntityManagerFactory",
                       transactionManagerRef = "masterTransactionManager")
@Slf4j
public class MasterDatabaseConfig {

    @Autowired
    private MasterDatabaseProperties masterDatabaseProperties;

    @Bean(name = "masterDatasource")
    public DataSource masterDatasource(){
        log.info("Setting up masterDatasource with :{}",masterDatabaseProperties.toString());
        HikariDataSource datasource = new HikariDataSource();
        datasource.setUsername(masterDatabaseProperties.getUsername());
        datasource.setPassword(masterDatabaseProperties.getPassword());
        datasource.setJdbcUrl(masterDatabaseProperties.getUrl());
        datasource.setDriverClassName(masterDatabaseProperties.getDriverClassName());
        datasource.setPoolName(masterDatabaseProperties.getPoolName());
        datasource.setMaximumPoolSize(masterDatabaseProperties.getMaxPoolSize());
        datasource.setMinimumIdle(masterDatabaseProperties.getMinIdle());
        datasource.setConnectionTimeout(masterDatabaseProperties.getConnectionTimeout());
        datasource.setIdleTimeout(masterDatabaseProperties.getIdleTimeout());
        log.info("Setup of masterDatasource successfully.");
        return datasource;
    }

    @Primary
    @Bean(name = "masterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean masterEntityManagerFactory(){
        LocalContainerEntityManagerFactoryBean lb = new LocalContainerEntityManagerFactoryBean();
        lb.setDataSource(masterDatasource());
        lb.setPackagesToScan(
           new String[]{MasterTenant.class.getPackage().getName(), MasterTenantRepository.class.getPackage().getName()}
        );

        //Setting a name for the persistence unit as Spring sets it as 'default' if not defined.
        lb.setPersistenceUnitName("master-database-persistence-unit");

        //Setting Hibernate as the JPA provider.
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        lb.setJpaVendorAdapter(vendorAdapter);

        //Setting the hibernate properties
        lb.setJpaProperties(hibernateProperties());

        log.info("Setup of masterEntityManagerFactory successfully.");
        return lb;
    }

    @Bean(name = "masterTransactionManager")
    public JpaTransactionManager masterTransactionManager(@Qualifier("masterEntityManagerFactory")EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        log.info("Setup of masterTransactionManager successfully.");
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL,true);
        properties.put(Environment.FORMAT_SQL,true);
        properties.put(Environment.HBM2DDL_AUTO,"update");
        return properties;
    }
}
