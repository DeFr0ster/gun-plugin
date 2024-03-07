package com.andrew.guns;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        // LauncherListener CLASS
        Bukkit.getPluginManager().registerEvents(new LauncherListener(this), this);

        // LAUNCHER COMMAND
        getCommand("launcher").setExecutor(new LauncherCommand());
        getCommand("launchermenu").setExecutor(new LauncherMenu());

        // TAB COMPLETER
        getCommand("launcher").setTabCompleter(new LauncherTab());

    }
}