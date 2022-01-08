package com.github.rozumek29.storecraftcore.placeholders;

import com.github.rozumek29.storecraftcore.models.StorePlayer;
import com.github.rozumek29.storecraftcore.models.TopKills;
import com.github.rozumek29.storecraftcore.utils.ChatUtil;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class PAPIExpansion extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "StoreCraft";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Rozumek29";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    HashMap<Integer, UUID> map = TopKills.getTopKills();
    StorePlayer storePlayer;
    String value;

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        value = null;
        switch (params) {
            case "top_killer_1" -> {
                if (map.get(1) != null){
                    storePlayer = new StorePlayer(map.get(1));
                    value = String.valueOf(storePlayer.getName());
                }
            }
            case "top_killer_2" -> {
                storePlayer = new StorePlayer(map.get(2));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_3" -> {
                if (map.get(3) != null) {
                    storePlayer = new StorePlayer(map.get(3));
                    value = String.valueOf(storePlayer.getName());
                }
            }
            case "top_killer_4" -> {
                storePlayer = new StorePlayer(map.get(4));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_5" -> {
                storePlayer = new StorePlayer(map.get(5));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_6" -> {
                storePlayer = new StorePlayer(map.get(6));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_7" -> {
                storePlayer = new StorePlayer(map.get(7));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_8" -> {
                storePlayer = new StorePlayer(map.get(8));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_9" -> {
                storePlayer = new StorePlayer(map.get(9));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_killer_10" -> {
                storePlayer = new StorePlayer(map.get(10));
                value = String.valueOf(storePlayer.getName());
            }
            case "top_kills_1" -> {
                storePlayer = new StorePlayer(map.get(1));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_2" -> {
                storePlayer = new StorePlayer(map.get(2));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_3" -> {
                storePlayer = new StorePlayer(map.get(3));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_4" -> {
                storePlayer = new StorePlayer(map.get(4));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_5" -> {
                storePlayer = new StorePlayer(map.get(5));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_6" -> {
                storePlayer = new StorePlayer(map.get(6));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_7" -> {
                storePlayer = new StorePlayer(map.get(7));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_8" -> {
                storePlayer = new StorePlayer(map.get(8));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_9" -> {
                storePlayer = new StorePlayer(map.get(9));
                value = String.valueOf(storePlayer.getKills());
            }
            case "top_kills_10" -> {
                storePlayer = new StorePlayer(map.get(10));
                value = String.valueOf(storePlayer.getKills());
            }
        }
        if (value != null) {
            return value;
        } else {
            return ChatUtil.fixColor("&8----");
        }
    }
}
