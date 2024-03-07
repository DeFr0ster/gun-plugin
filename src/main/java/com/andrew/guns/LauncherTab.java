package com.andrew.guns;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LauncherTab implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        // /launcher <player> <launcher type>

        /*
       Arrow Launcher
       Egg Launcher
       Snowball Launcher
       Fireball Launcher
       Trident Launcher
     */

        if (args.length == 1) {
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length == 2) {
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("arrow", "egg", "snowball", "fireball", "trident"), new ArrayList<>());
        }
        return new ArrayList<>();
    }
}
