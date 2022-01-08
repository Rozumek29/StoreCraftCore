package com.github.rozumek29.storecraftcore.cache;

import com.github.rozumek29.storecraftcore.models.StorePlayer;

import java.util.HashMap;
import java.util.UUID;

public class StoreMap {
    private static HashMap<UUID, StorePlayer> map = new HashMap<>();

    public static HashMap<UUID, StorePlayer> getMap(){
        return map;
    }
}
