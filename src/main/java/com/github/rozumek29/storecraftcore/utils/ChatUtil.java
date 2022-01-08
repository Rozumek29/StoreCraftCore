package com.github.rozumek29.storecraftcore.utils;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String fixColor(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
