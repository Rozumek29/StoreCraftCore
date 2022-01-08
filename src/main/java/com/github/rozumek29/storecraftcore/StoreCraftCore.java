package com.github.rozumek29.storecraftcore;

import com.github.rozumek29.storecraftcore.commands.TopKillsCommand;
import com.github.rozumek29.storecraftcore.database.SQLiteManager;
import com.github.rozumek29.storecraftcore.listeners.*;
import com.github.rozumek29.storecraftcore.models.TopUpdater;
import com.github.rozumek29.storecraftcore.placeholders.PAPIExpansion;
import com.github.rozumek29.storecraftcore.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class StoreCraftCore extends JavaPlugin implements Listener {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        configSetup();
        checkPAPI();
        SQLiteManager.connect();
        registerEvents();
        TopUpdater.runUpdater();
        getCommand("top").setExecutor(new TopKillsCommand());
        new PAPIExpansion().register();
        getLogger().info(ChatUtil.fixColor("&7[&cCORE&7] &aPLUGIN ENABLED"));
    }

    @Override
    public void onDisable() {
        SQLiteManager.disconnect();
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
}
