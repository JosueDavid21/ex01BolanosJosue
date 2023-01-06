package com.distribuida.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Handle;
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

    @Produces
    @ApplicationScoped
    public Handle conectionDB(){
        Jdbi jdbi = Jdbi.create(poolConection());
        return  jdbi.open();
    }

    private DataSource poolConection(){
        HikariConfig config = new HikariConfig("./src/main/resources/META-INF/hikari-config.properties");
        return new HikariDataSource(config);
    }
}
