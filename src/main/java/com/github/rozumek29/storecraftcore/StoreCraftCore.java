package com.github.rozumek29.storecraftcore;

import com.github.rozumek29.storecraftcore.database.DataSource;
import com.github.rozumek29.storecraftcore.listeners.*;
import com.github.rozumek29.storecraftcore.cache.TopUpdater;
import com.github.rozumek29.storecraftcore.placeholders.PAPIExpansion;
import com.github.rozumek29.storecraftcore.utils.ChatUtil;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public final class StoreCraftCore extends JavaPlugin implements Listener {

    private static Plugin instance;


    @SneakyThrows
    @Override
    public void onEnable() {
        instance = this;
        configSetup();
        checkPAPI();
        initializeDataBase();
        registerEvents();
        TopUpdater.runUpdater();
        new PAPIExpansion().register();
        getLogger().info(ChatUtil.fixColor("&7[&cCORE&7] &aPLUGIN ENABLED"));
    }

    @SneakyThrows
    @Override
    public void onDisable() {
        DataSource.closeConnections();
        getLogger().info(ChatUtil.fixColor("&7[&cCORE&7] &cPLUGIN DISABLED"));
    }

    private void configSetup(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Plugin getInstance() {
        return instance;
    }

    private void checkPAPI(){
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerDeath(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerLeave(), this);
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new BlockPlace(), this);
    }

    private void initializeDataBase() throws SQLException {
        DataSource dataSource = new DataSource();
    }
}
