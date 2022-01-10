package com.github.rozumek29.storecraftcore.placeholders;

import com.github.rozumek29.storecraftcore.cache.TopMaps;
import com.github.rozumek29.storecraftcore.enums.Operation;
import com.github.rozumek29.storecraftcore.enums.Type;
import com.github.rozumek29.storecraftcore.models.*;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
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

    HashMap<Integer, UUID> kills = TopMaps.getTopKills();
    HashMap<Integer, UUID> deaths = TopMaps.getTopDeaths();
    HashMap<Integer, UUID> bp = TopMaps.getTopBlockPlaced();
    HashMap<Integer, UUID> bb = TopMaps.getTopBlockBreak();

    String value;

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        value = null;
        if (params.contains("top")){
            int index = Integer.parseInt(params.substring(params.length()-1));
            if (index == 0){
                index = 10;
            }
            if (params.contains("kills")){
                if (params.contains("name")){
                    value = getTop(index, Type.KILLS, Operation.NAME);
                }else {
                    value = getTop(index, Type.KILLS, Operation.VALUE);
                }
            } else if (params.contains("deaths")){
                if (params.contains("name")){
                    value = getTop(index, Type.DEATHS, Operation.NAME);
                }else {
                    value = getTop(index, Type.DEATHS, Operation.VALUE);
                }
            } else if (params.contains("bp")){
                if (params.contains("name")){
                    value = getTop(index, Type.BLOCK_PLACED, Operation.NAME);
                }else {
                    value = getTop(index, Type.BLOCK_PLACED, Operation.VALUE);
                }
            } else if (params.contains("bb")){
                if (params.contains("name")){
                    value = getTop(index, Type.BLOCK_BREAK, Operation.NAME);
                }else {
                    value = getTop(index, Type.BLOCK_BREAK, Operation.VALUE);
                }
            }
        }
        if (value != null){
            return value;
        }else {
            return "---";
        }
    }

    StorePlayer storePlayer;

    private String getTop(int index, Type type, Operation operation){
        switch (type){
            case KILLS -> {
                if (kills.size() >= index){
                    storePlayer = new StorePlayer(kills.get(index));
                    if (operation == Operation.NAME){
                        return storePlayer.getName();
                    }else if (operation == Operation.VALUE){
                        return String.valueOf(storePlayer.getKills());
                    }
                }
            }
            case DEATHS -> {
                if (deaths.size() >= index){
                    storePlayer = new StorePlayer(deaths.get(index));
                    if (operation == Operation.NAME){
                        return storePlayer.getName();
                    }else if (operation == Operation.VALUE){
                        return String.valueOf(storePlayer.getDeaths());
                    }
                }
            }
            case BLOCK_PLACED -> {
                if (bp.size() >= index){
                    storePlayer = new StorePlayer(bp.get(index));
                    if (operation == Operation.NAME){
                        return storePlayer.getName();
                    }else if (operation == Operation.VALUE){
                        return String.valueOf(storePlayer.getBlockPlaced());
                    }
                }
            }
            case BLOCK_BREAK -> {
                if (bb.size() >= index){
                    storePlayer = new StorePlayer(bb.get(index));
                    if (operation == Operation.NAME){
                        return storePlayer.getName();
                    }else if (operation == Operation.VALUE){
                        return String.valueOf(storePlayer.getBlockBreak());
                    }
                }
            }
        }
        return null;
    }
}
