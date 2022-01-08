package com.github.rozumek29.storecraftcore.listeners;

import com.github.rozumek29.storecraftcore.cache.StoreMap;
import com.github.rozumek29.storecraftcore.models.StorePlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerJoin implements Listener {

    HashMap<UUID, StorePlayer> map = StoreMap.getMap();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        UUID uuid = event.getPlayer().getUniqueId();
        StorePlayer storePlayer = new StorePlayer(uuid);
        map.put(uuid, storePlayer);
    }
}
