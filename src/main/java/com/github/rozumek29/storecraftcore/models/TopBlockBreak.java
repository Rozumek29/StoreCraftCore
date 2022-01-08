package com.github.rozumek29.storecraftcore.models;

import java.util.HashMap;
import java.util.UUID;

public class TopBlockBreak {
    static private HashMap<Integer, UUID> topBlockBreak = new HashMap<>();

    public static HashMap<Integer, UUID> getTopBlockBreak() {
        return topBlockBreak;
    }
}
