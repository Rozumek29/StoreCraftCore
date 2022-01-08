package com.github.rozumek29.storecraftcore.listeners;

import com.github.rozumek29.storecraftcore.cache.StoreMap;
import com.github.rozumek29.storecraftcore.models.StorePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashMap;
import java.util.UUID;

public class BlockPlace implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if (event.getBlockPlaced() instanceof Block){
            HashMap<UUID, StorePlayer> map = StoreMap.getMap();
            StorePlayer player = map.get(event.getPlayer().getUniqueId());
            player.increaseBlocksPlaced();
        }
    }
}
