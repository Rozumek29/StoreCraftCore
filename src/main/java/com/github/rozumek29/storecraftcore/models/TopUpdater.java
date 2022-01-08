package com.github.rozumek29.storecraftcore.models;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import com.github.rozumek29.storecraftcore.database.SQLiteManager;
import com.github.rozumek29.storecraftcore.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class TopUpdater {

    private static Plugin plugin = StoreCraftCore.getInstance();
    private static final Long delay = 1200L;
    private static PreparedStatement ps;
    private static ResultSet rs;

    private static HashMap<Integer, UUID> topkills = TopKills.getTopKills();
    private static HashMap<Integer, UUID> topDeaths = TopDeaths.getTopDeaths();
    private static HashMap<Integer, UUID> topBlockPlaced = TopBlockPlaced.getTopBlockPlaced();
    private static HashMap<Integer, UUID> topBlockBreak = TopBlockBreak.getTopBlockBreak();

    private static final String killsSQL = "SELECT UUID, Kills FROM players ORDER BY Kills DESC LIMIT 10;";
    private static final String deathsSQL = "SELECT UUID, Deaths FROM players ORDER BY Deaths DESC LIMIT 10;";
    private static final String blockPlacedSQL = "SELECT UUID, BlockPlaced FROM players ORDER BY BlockPlaced DESC LIMIT 10;";
    private static final String blockBreakSQL = "SELECT UUID, BlockBreak FROM players ORDER BY BlockBreak DESC LIMIT 10;";

    public static void runUpdater() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                topkills = getTopMap(killsSQL, topkills);
                topDeaths = getTopMap(deathsSQL, topDeaths);
                topBlockPlaced = getTopMap(blockPlacedSQL, topBlockPlaced);
                topBlockBreak = getTopMap(blockBreakSQL, topBlockBreak);
            }
        }, 300L, delay);
    }

    private static HashMap<Integer, UUID> getTopMap(String sql, HashMap<Integer, UUID> map) {
        try {
            ps = SQLiteManager.getConnection().prepareStatement(sql);
            ps.execute();
            rs = ps.getResultSet();

            int i = 1;

            while (rs.next()){
                UUID uuid = UUID.fromString(rs.getString("UUID"));
                map.put(i, uuid);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

}
