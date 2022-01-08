package com.github.rozumek29.storecraftcore.commands;

import com.github.rozumek29.storecraftcore.cache.StoreMap;
import com.github.rozumek29.storecraftcore.models.StorePlayer;
import com.github.rozumek29.storecraftcore.models.TopKills;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class TopKillsCommand implements CommandExecutor {

    HashMap<Integer, UUID> map = TopKills.getTopKills();
    HashMap<UUID, StorePlayer> map2 = StoreMap.getMap();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(map.get(3).toString());
            return true;
        }
        return false;
    }
}
