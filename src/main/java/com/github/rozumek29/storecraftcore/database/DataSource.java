package com.github.rozumek29.storecraftcore.database;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.sqlite.JDBC;

import java.io.File;
import java.io.IOException;
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
        config.setJdbcUrl("jdbc:sqlite:" + file.getPath());
        config.setPoolName("StoreCraftDataBasePool");
        config.setMaximumPoolSize(10);
    }

    public DataSource(){

        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
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
