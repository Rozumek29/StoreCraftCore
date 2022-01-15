package com.github.rozumek29.storecraftcore.database;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final Plugin plugin = StoreCraftCore.getInstance();
    private static File file = new File(plugin.getDataFolder(), "database.db");
    private static HikariDataSource ds;

    static {

    }

    public DataSource(){

        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e) {
                plugin.getLogger().log(java.util.logging.Level.SEVERE,  "Filed to create database file (SQLite) Error -> " + e.getMessage());
            }
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {

                HikariConfig config = new HikariConfig();
                config.setPoolName("StoreCraftPool");
                config.setDataSourceClassName("org.sqlite.SQLiteDataSource");
                config.setJdbcUrl("jdbc:sqlite:"+file.getAbsolutePath());
                config.setPoolName("StoreCraftDataBasePool");
                config.setMaximumPoolSize(20);
                config.setMaxLifetime(60000); // 60 Sec
                config.setIdleTimeout(30000); // 30 Sec

                ds = new HikariDataSource(config);
            }
        });

    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closeConnections() {
        if(!ds.isClosed()){
            ds.close();
        }
    }
}
