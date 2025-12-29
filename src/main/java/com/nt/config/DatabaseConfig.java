package com.nt.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.net.URI;

@Configuration
public class DatabaseConfig {

    @Bean
    @Primary
    public DataSource dataSource() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        if (databaseUrl != null && databaseUrl.startsWith("postgresql://")) {
            URI dbUri = URI.create(databaseUrl);
            int port = dbUri.getPort() == -1 ? 5432 : dbUri.getPort();
            String jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + port + dbUri.getPath();
            String[] userInfo = dbUri.getUserInfo().split(":");
            
            return DataSourceBuilder.create()
                    .url(jdbcUrl)
                    .username(userInfo[0])
                    .password(userInfo[1])
                    .driverClassName("org.postgresql.Driver")
                    .build();
        }
        
        // Fallback to default configuration
        return DataSourceBuilder.create()
                .url(System.getenv("DATABASE_URL"))
                .username(System.getenv("DB_USERNAME"))
                .password(System.getenv("DB_PASSWORD"))
                .driverClassName("org.postgresql.Driver")
                .build();
    }
}