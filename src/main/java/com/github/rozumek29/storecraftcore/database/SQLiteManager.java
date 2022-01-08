package com.github.rozumek29.storecraftcore.database;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

public class SQLiteManager {
    private static Plugin  plugin = StoreCraftCore.getInstance();
    private static Connection connection;

    public static void connect(){
        File file = new File(plugin.getDataFolder(), "database.db");
        if (!file.exists()){
            new File(plugin.getDataFolder().getPath()).mkdir();
        }
        String url = "jdbc:sqlite:"+file;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            createTable();
            plugin.getLogger().info("Database connect success.");
        }catch (Exception e){
            plugin.getLogger().warning("Could not connect to the database [SQLite]");
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void createTable(){
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement("create table IF NOT EXISTS players(" +
                    "UUID TEXT not null constraint players_pk primary key," +
                    "Name TEXT not null," +
                    "Kills integer not null," +
                    "Deaths integer not null," +
                    "BlockPlaced integer not null," +
                    "BlockBreak integer not null);");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return connection;
    }

}
