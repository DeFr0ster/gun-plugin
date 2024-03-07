package com.andrew.guns;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class LauncherListener implements Listener {

    private Main main;

    public LauncherListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {

        List<LauncherEnum> launchers = LauncherEnum.getAllLaunchers();

        if (e.hasItem() && e.getItem() != null) {
            Player player = e.getPlayer();
            ItemStack item = e.getItem();

            for (LauncherEnum launcher : launchers) {
                if (item.getType().equals(launcher.getMaterial()))
                    switch (launcher.getName()) {
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
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent e) {

        List<LauncherEnum> launchers = LauncherEnum.getAllLaunchers();

        if (e.getEntity().getShooter() instanceof Player) {
            Player player = (Player) e.getEntity().getShooter();
            ItemStack item = player.getInventory().getItemInMainHand();

            for (LauncherEnum launcher : launchers) {
                if (item.getType().equals(launcher.getMaterial())) {
                    switch (launcher.getName()) {
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
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        List<LauncherEnum> launchers = LauncherEnum.getAllLaunchers();

        Player target = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "Launcher Selector") && e.getCurrentItem() != null) {
            e.setCancelled(true);

            for (LauncherEnum launcher : launchers) {
                if (item.getType().equals(launcher.getMaterial())) {

                    ItemStack itemStack = new ItemStack(launcher.getMaterial());
                    ItemMeta meta = itemStack.getItemMeta();
                    meta.setDisplayName(launcher.getColor() + launcher.getName());
                    meta.setLore(Arrays.asList(ChatColor.GRAY + launcher.getLore()));
                    itemStack.setItemMeta(meta);

                    switch (e.getRawSlot()) {
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                            target.getInventory().addItem(itemStack);
                            break;
                        default:
                            return;
                    }
                }
            }
            target.closeInventory();
        }
    }
}
