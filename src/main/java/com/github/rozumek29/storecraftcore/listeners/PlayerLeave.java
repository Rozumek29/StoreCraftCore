package com.github.rozumek29.storecraftcore.listeners;

import com.github.rozumek29.storecraftcore.cache.StoreMap;
import com.github.rozumek29.storecraftcore.models.StorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event){
        HashMap<UUID, StorePlayer> map = StoreMap.getMap();
        StorePlayer player = map.get(event.getPlayer().getUniqueId());
        player.savePlayer();
    }
}
