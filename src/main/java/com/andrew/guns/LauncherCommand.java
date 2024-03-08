package com.andrew.guns;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LauncherCommand implements CommandExecutor {

    private Cache<UUID, Long> cooldown = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.SECONDS).build();

    // /launcher <player> <launcher type>

    /*
       Arrow Launcher
       Egg Launcher
       Snowball Launcher
       Fireball Launcher
       Trident Launcher
     */

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only a player can execute this command.");
            return true;
        }

        if (!cooldown.asMap().containsKey(player.getUniqueId())) {
            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + 5000);

            if (args.length != 2) {
                player.sendMessage(ChatColor.RED + "Correct usage: /launcher <Player> <Type>");
                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + "Player is not found");
                return true;
            }

            String launcherType = args[1];
            LauncherEnum launcher = LauncherEnum.valueOf(launcherType.toUpperCase());

            ItemStack itemStack = new ItemStack(launcher.getMaterial());
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(launcher.getColor() + launcher.getName());
            meta.setLore(Arrays.asList(ChatColor.GRAY + launcher.getLore()));
            itemStack.setItemMeta(meta);

            target.getInventory().addItem(itemStack);
            target.sendMessage(ChatColor.GRAY + "You have selected the " + itemStack.getItemMeta().getDisplayName() + ChatColor.GRAY + ".");
            return true;

        } else {
            long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
            player.sendMessage(ChatColor.RED + "You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " seconds to use this command.");
        }
        return false;
    }
}