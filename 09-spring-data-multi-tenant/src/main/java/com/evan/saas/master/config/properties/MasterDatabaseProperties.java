package com.evan.saas.master.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties("una.master.datasource")
public class MasterDatabaseProperties {

    private String url;

    private String password;

    private String username;

    private String driverClassName;

    private long connectionTimeout;

    private int maxPoolSize;

    private long idleTimeout;

    private int minIdle;

    private String poolName;

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("MasterDatabaseProperties [ url=")
                .append(url)
                .append(", username=")
                .append(username)
                .append(", password=")
                .append(password)
                .append(", driverClassName=")
                .append(driverClassName)
                .append(", connectionTimeout=")
                .append(connectionTimeout)
                .append(", maxPoolSize=")
                .append(maxPoolSize)
                .append(", idleTimeout=")
                .append(idleTimeout)
                .append(", minIdle=")
                .append(minIdle)
                .append(", poolName=")
                .append(poolName)
                .append("]");
        return builder.toString();
    }
}
