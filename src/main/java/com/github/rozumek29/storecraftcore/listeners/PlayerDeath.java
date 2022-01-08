package com.github.rozumek29.storecraftcore.listeners;

import com.github.rozumek29.storecraftcore.StoreCraftCore;
import com.github.rozumek29.storecraftcore.cache.StoreMap;
import com.github.rozumek29.storecraftcore.models.StorePlayer;
import com.github.rozumek29.storecraftcore.utils.ChatUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDeath implements Listener {

    private final Plugin plugin = StoreCraftCore.getInstance();
    String rawMessage = plugin.getConfig().getString("DeathMessage");

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (event.getEntity() instanceof Player) {

            Player player = event.getEntity();
            HashMap<UUID, StorePlayer> map = StoreMap.getMap();
            StorePlayer storePlayer = map.get(player.getUniqueId());
            storePlayer.increaseDeaths();

            if (player.hasPermission("storecraft.deathinfo")) {
                String message = ChatUtil.fixColor(rawMessage).replaceAll("%X%", String.valueOf(event.getEntity().getLocation().getBlockX())).replaceAll("%Y%", String.valueOf(event.getEntity().getLocation().getBlockY())).replaceAll("%Z%", String.valueOf(event.getEntity().getLocation().getBlockZ()));
                player.sendMessage(message);
            }

            Player killer = event.getEntity().getKiller();
            if (killer != null) {
                map.get(killer.getUniqueId()).increaseKills();
            }


        }
    }
}
