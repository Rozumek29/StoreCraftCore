package com.github.rozumek29.storecraftcore.models;

import java.util.HashMap;
import java.util.UUID;

public class TopDeaths {
    static private HashMap<Integer, UUID> topDeaths = new HashMap<>();

    public static HashMap<Integer, UUID> getTopDeaths() {
        return topDeaths;
    }
}
