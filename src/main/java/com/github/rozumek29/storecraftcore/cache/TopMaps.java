package com.github.rozumek29.storecraftcore.cache;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

@Getter
public class TopMaps {
    static private HashMap<Integer, UUID> topBlockBreak = new HashMap<>();
    static private HashMap<Integer, UUID> topBlockPlaced = new HashMap<>();
    static private HashMap<Integer, UUID> topDeaths = new HashMap<>();
    static private HashMap<Integer, UUID> topKills = new HashMap<>();

    public static HashMap<Integer, UUID> getTopBlockBreak() {
        return topBlockBreak;
    }

    public static HashMap<Integer, UUID> getTopBlockPlaced() {
        return topBlockPlaced;
    }

    public static HashMap<Integer, UUID> getTopDeaths() {
        return topDeaths;
    }

    public static HashMap<Integer, UUID> getTopKills() {
        return topKills;
    }
}
