package com.github.rozumek29.storecraftcore.models;

import java.util.HashMap;
import java.util.UUID;

public class TopBlockPlaced {
    static private HashMap<Integer, UUID> topBlockPlaced = new HashMap<>();

    public static HashMap<Integer, UUID> getTopBlockPlaced() {
        return topBlockPlaced;
    }
}
