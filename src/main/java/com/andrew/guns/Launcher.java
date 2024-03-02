package com.andrew.guns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Launcher implements CommandExecutor {

    // /launcher <player> <type>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Incorrect Usage: /launcher <player> <type>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(ChatColor.RED +"ERROR! Player not found.");
            return true;
        }

        String type = args[1].toLowerCase();
        ItemStack launcher;

        switch (type) {
            case "arrow":
                launcher = createLauncher("Arrow Launcher", Material.WOODEN_HOE);
                break;
            case "egg":
                launcher = createLauncher("Egg Launcher", Material.STONE_HOE);
                break;
            case "snowball":
                launcher = createLauncher("Snowball Launcher", Material.IRON_HOE);
                break;
            case "fireball":
                launcher = createLauncher("Fireball Launcher", Material.GOLDEN_HOE);
                break;
            case "trident":
                launcher = createLauncher("Trident Launcher", Material.DIAMOND_HOE);
                break;
            default:
                player.sendMessage("Invalid launcher type.");
                return true;
        }

        if (launcher != null) {
            target.getInventory().addItem(launcher);
            player.sendMessage(ChatColor.GRAY + "You have given the " + type + ChatColor.GRAY + " launcher to " + target.getName() + ChatColor.GRAY + "!");
        }

        return true;

    }
    private ItemStack createLauncher(String name,  Material material) {
        if (name.equalsIgnoreCase("Arrow Launcher")) {
            ItemStack arrow = new ItemStack(Material.WOODEN_HOE);
            ItemMeta meta = arrow.getItemMeta();
            meta.setDisplayName("Arrow Launcher");
            meta.isUnbreakable();
            arrow.setItemMeta(meta);
            return arrow;
        }
        if (name.equalsIgnoreCase("Egg Launcher")) {
            ItemStack egg = new ItemStack(Material.STONE_HOE);
            ItemMeta meta = egg.getItemMeta();
            meta.setDisplayName("Egg Launcher");
            meta.isUnbreakable();
            egg.setItemMeta(meta);
            return egg;
        }
        if (name.equalsIgnoreCase("Snowball Launcher")) {
            ItemStack snowball = new ItemStack(Material.IRON_HOE);
            ItemMeta meta = snowball.getItemMeta();
            meta.setDisplayName("Snowball Launcher");
            meta.isUnbreakable();
            snowball.setItemMeta(meta);
            return snowball;
        }
        if (name.equalsIgnoreCase("Fireball Launcher")) {
            ItemStack fireball = new ItemStack(Material.GOLDEN_HOE);
            ItemMeta meta = fireball.getItemMeta();
            meta.setDisplayName("Fireball Launcher");
            meta.isUnbreakable();
            fireball.setItemMeta(meta);
            return fireball;
        }
        if (name.equalsIgnoreCase("Trident Launcher")) {
            ItemStack trident = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta meta = trident.getItemMeta();
            meta.setDisplayName("Trident Launcher");
            meta.isUnbreakable();
            trident.setItemMeta(meta);
            return trident;
        }
        return null;
    }
}
