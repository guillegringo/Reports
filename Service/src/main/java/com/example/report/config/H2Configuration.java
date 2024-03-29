package com.example.report.config;

import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;

@Configuration
@Profile("h2")
public class H2Configuration {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-ifNotExists", "-tcpAllowOthers", "-tcpPort", "9002");
    }

    @Bean
    @DependsOn("inMemoryH2DatabaseServer")
    public JdbcDataSource dataSource() {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost:9002/mem:jobrunr-db;DB_CLOSE_DELAY=-1");
        return dataSource;
    }

}
