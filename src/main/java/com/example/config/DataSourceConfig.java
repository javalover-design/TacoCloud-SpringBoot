package com.example.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author lambda
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().build();
    }
    @Bean
    public JdbcTemplate getJdbcTemplate(@Qualifier("datasource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
