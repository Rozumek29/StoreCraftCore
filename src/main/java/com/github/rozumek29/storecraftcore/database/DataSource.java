package com.github.rozumek29.storecraftcore.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl("jdbc:mysql://localhost:3306");
        config.setDataSourceClassName("com.mysql.cj.jdbc.MysqlDataSource");
        config.addDataSourceProperty("databaseName", "test");
        config.setUsername("u1_gqLefvi9Bc");
        config.setPassword("MCQUF+ICmi@azWN8W=!wNjYC");
        config.setMaximumPoolSize(10);
        config.setMaxLifetime(1800000);
        ds = new HikariDataSource(config);
    }


    private DataSource() {}

    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    public static void closeConnections(){
        if (!ds.isClosed()){
            ds.close();
        }
    }
}
