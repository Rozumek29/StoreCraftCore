package com.github.rozumek29.storecraftcore.database;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.plugin.Plugin;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final Plugin plugin = StoreCraftCore.getInstance();
    private static File file = new File(plugin.getDataFolder(), "database.db");
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setPoolName("StoreCraftPool");
        config.setDriverClassName("org.sqlite.JDBC");
        config.setJdbcUrl("jdbc:sqlite:"+file);
        config.setMaximumPoolSize(10);
    }

    public DataSource(){
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnections() throws SQLException{
        if(!ds.isClosed()){
            ds.close();
        }
    }
}
