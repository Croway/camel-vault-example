package com.redhat;

import org.apache.camel.CamelContext;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    public DataSource dataSource(CamelContext camelContext) {
        String databasePassword = camelContext.resolvePropertyPlaceholders("{{hashicorp:database/password}}");

        return DataSourceBuilder
                .create()
                .username("postgres")
                .password(databasePassword)
                .url("jdbc:postgresql://localhost:5432/postgres")
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}
