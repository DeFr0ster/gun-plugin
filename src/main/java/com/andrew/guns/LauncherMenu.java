package com.andrew.guns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class LauncherMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        List<LauncherEnum> launchers = LauncherEnum.getAllLaunchers();

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only a player can execute this command.");
            return true;
        }

        Inventory inv = Bukkit.createInventory(player, 45, ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Launcher Selector");

        //FRAME
        ItemStack frame = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = frame.getItemMeta();
        meta.setDisplayName(" ");
        frame.setItemMeta(meta);

        for (int i : new int[]{0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}) {
            inv.setItem(i, frame);
        }

        //ARROW
        ItemStack arrow = new ItemStack(LauncherEnum.ARROW.getMaterial());
        ItemMeta arrowmeta = arrow.getItemMeta();
        arrowmeta.setDisplayName(LauncherEnum.ARROW.getColor().toString() + ChatColor.BOLD + LauncherEnum.ARROW.getName());
        arrowmeta.setLore(Arrays.asList(ChatColor.GRAY + LauncherEnum.ARROW.getLore()));
        arrow.setItemMeta(arrowmeta);

        inv.setItem(20, arrow);

        //EGG
        ItemStack egg = new ItemStack(LauncherEnum.EGG.getMaterial());
        ItemMeta eggmeta = egg.getItemMeta();
        eggmeta.setDisplayName(LauncherEnum.EGG.getColor().toString() + ChatColor.BOLD + LauncherEnum.EGG.getName());
        eggmeta.setLore(Arrays.asList(ChatColor.GRAY + LauncherEnum.EGG.getLore()));
        egg.setItemMeta(eggmeta);

        inv.setItem(21, egg);

        //SNOWBALL
        ItemStack snowball = new ItemStack(LauncherEnum.SNOWBALL.getMaterial());
        ItemMeta snowballmeta = snowball.getItemMeta();
        snowballmeta.setDisplayName(LauncherEnum.SNOWBALL.getColor().toString() + ChatColor.BOLD + LauncherEnum.SNOWBALL.getName());
        snowballmeta.setLore(Arrays.asList(ChatColor.GRAY + LauncherEnum.SNOWBALL.getLore()));
        snowball.setItemMeta(snowballmeta);

        inv.setItem(22, snowball);

        //FIREBALL
        ItemStack fireball = new ItemStack(LauncherEnum.FIREBALL.getMaterial());
        ItemMeta fireballmeta = fireball.getItemMeta();
        fireballmeta.setDisplayName(LauncherEnum.FIREBALL.getColor().toString() + ChatColor.BOLD + LauncherEnum.FIREBALL.getName());
        fireballmeta.setLore(Arrays.asList(ChatColor.GRAY + LauncherEnum.FIREBALL.getLore()));
        fireball.setItemMeta(fireballmeta);

        inv.setItem(23, fireball);

        //TRIDENT
        ItemStack trident = new ItemStack(LauncherEnum.TRIDENT.getMaterial());
        ItemMeta tridentmeta = trident.getItemMeta();
        tridentmeta.setDisplayName(LauncherEnum.TRIDENT.getColor().toString() + ChatColor.BOLD + LauncherEnum.TRIDENT.getName());
        tridentmeta.setLore(Arrays.asList(ChatColor.GRAY + LauncherEnum.TRIDENT.getLore()));
        trident.setItemMeta(tridentmeta);

        inv.setItem(24, trident);

        player.openInventory(inv);

        return false;
    }
}