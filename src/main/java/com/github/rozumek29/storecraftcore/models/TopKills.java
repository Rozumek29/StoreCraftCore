package com.github.rozumek29.storecraftcore.models;

import java.util.HashMap;
import java.util.UUID;

public class TopKills{
    static private HashMap<Integer, UUID> topKills = new HashMap<>();

    public static HashMap<Integer, UUID> getTopKills() {
        return topKills;
    }
}
