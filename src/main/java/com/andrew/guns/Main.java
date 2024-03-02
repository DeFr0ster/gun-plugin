package com.andrew.guns;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("launcher").setExecutor(new com.andrew.guns.Launcher());

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        if (e.hasItem() && e.getItem() != null) {
            Player player = e.getPlayer();
            String displayName = e.getItem().getItemMeta().getDisplayName();
            switch (displayName) {
                case "Arrow Launcher":
                    player.launchProjectile(Arrow.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1.0F, 1.0F);
                    player.sendMessage("You have shot an arrow!");
                    break;
                case "Egg Launcher":
                    player.launchProjectile(Egg.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_EGG_THROW, 1.0F, 1.0F);
                    player.sendMessage("You have thrown an egg!");
                    break;
                case "Snowball Launcher":
                    player.launchProjectile(Snowball.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_SNOWBALL_THROW, 1.0F, 1.0F);
                    player.sendMessage("You have thrown a snowball!");
                    break;
                case "Fireball Launcher":
                    player.launchProjectile(Fireball.class);
                    player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1.0F, 1.0F);
                    player.sendMessage("You have shot a fireball!");
                    break;
                case "Trident Launcher":
                    player.launchProjectile(Trident.class);
                    player.playSound(player.getLocation(), Sound.ITEM_TRIDENT_THROW, 1.0F, 1.0F);
                    player.sendMessage("You have thrown a trident!");
                    break;
            }
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent e) {


        if (e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            String displayName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
            switch (displayName) {
                case "Arrow Launcher":
                    if (e.getHitBlock() != null) {
                        player.teleport(e.getHitBlock().getLocation().add(0, 1, 0));
                        player.spawnParticle(Particle.PORTAL, player.getLocation(), 5);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    } else if (e.getHitEntity() != null) {
                        player.teleport(e.getHitEntity().getLocation().add(0, 1, 0));
                        player.spawnParticle(Particle.PORTAL, player.getLocation(), 5);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                    }
                    break;
                case "Egg Launcher":
                    if (e.getHitBlock() != null) {
                        player.playSound(e.getHitBlock().getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 1.0F, 1.0F);
                        e.getHitBlock().breakNaturally();
                    } else if (e.getHitEntity() != null) {
                        player.playSound(e.getHitEntity().getLocation(), Sound.ENTITY_PLAYER_HURT, 1.0F, 1.0F);
                    }
                    break;
                case "Snowball Launcher":
                    if (e.getHitBlock() != null) {
                        Snowman snowman = (Snowman) Bukkit.getWorld("world").spawnEntity(e.getHitBlock().getLocation().add(0, 1, 0), EntityType.SNOWMAN);
                        player.sendMessage("You have spawned a snowman!");
                    }
                    break;
                case "Trident Launcher":
                    if (e.getHitBlock() != null) {
                        Bukkit.getWorld("world").strikeLightning(e.getHitBlock().getLocation());
                    }
                    break;
            }
        }
    }
}