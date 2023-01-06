package com.distribuida.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;

@ApplicationScoped
public class DbConfig {

    @Inject
    @ConfigProperty(name="db.user")
    String dbUser;

    @Inject
    @ConfigProperty(name="db.password")
    String dbPassword;
    @Inject
    @ConfigProperty(name="db.url")
    String dbUrl;

    @Inject
    @ConfigProperty(name="db.driverClassName")
    String dbDriverClassName;

    @Inject
    @ConfigProperty(name="db.maximumPoolSize")
    int dbMaxPoolSize;

    @Inject
    @ConfigProperty(name="db.maxLifetime")
    int dbMaxLifeTime;

    @Produces
    @ApplicationScoped
    public Jdbi conectionDB(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dbDriverClassName);
        dataSource.setJdbcUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPassword);
        dataSource.setMaximumPoolSize(dbMaxPoolSize);
        dataSource.setMaxLifetime(dbMaxLifeTime);
        return Jdbi.create(dataSource);
    }
}
