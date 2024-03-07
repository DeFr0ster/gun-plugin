package com.andrew.guns;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum LauncherEnum {

    ARROW("Arrow Launcher", "This launches an arrow!", ChatColor.GREEN, Material.WOODEN_HOE),
    EGG("Egg Launcher", "This launches an egg!", ChatColor.YELLOW, Material.STONE_HOE),
    SNOWBALL("Snowball Launcher", "This launches a snowball!", ChatColor.WHITE, Material.IRON_HOE),
    FIREBALL("Fireball Launcher", "This launches a fireball!", ChatColor.GOLD, Material.GOLDEN_HOE),
    TRIDENT("Trident Launcher", "This launches a trident!", ChatColor.AQUA, Material.DIAMOND_HOE);

    private String name;
    private String lore;
    private ChatColor color;
    private Material material;

    LauncherEnum(String name, String lore, ChatColor color, Material material) {
        this.name = name;
        this.lore = lore;
        this.color = color;
        this.material = material;
    }

    public static List<LauncherEnum> getAllLaunchers() {
        return Arrays.asList(LauncherEnum.values());
    }

    public String getName() { return name; }
    public String getLore() { return lore; }
    public ChatColor getColor() { return color; }
    public Material getMaterial() { return material; }
}
