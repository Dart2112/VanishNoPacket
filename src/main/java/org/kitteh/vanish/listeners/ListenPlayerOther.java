package org.kitteh.vanish.listeners;

import net.lapismc.lapiscore.utils.CompatibleMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.kitteh.vanish.Settings;
import org.kitteh.vanish.VanishPerms;
import org.kitteh.vanish.VanishPlugin;
import org.kitteh.vanish.metrics.MetricsOverlord;

public final class ListenPlayerOther implements Listener {
    private final VanishPlugin plugin;

    public ListenPlayerOther(VanishPlugin instance) {
        this.plugin = instance;
    }

    @EventHandler(ignoreCancelled = true)
    public void onBucketFill(PlayerBucketFillEvent event) {
        if (this.plugin.getManager().isVanished(event.getPlayer()) && VanishPerms.canNotInteract(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDrop(PlayerDropItemEvent event) {
        if (this.plugin.getManager().isVanished(event.getPlayer()) && VanishPerms.canNotInteract(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onFoodChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player player = (Player) event.getEntity();
            if (this.plugin.getManager().isVanished(player) && VanishPerms.canNotHunger(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        if (!this.plugin.chestFakeInUse(player.getName()) && !player.isSneaking() && (event.getAction() == Action.RIGHT_CLICK_BLOCK) && this.plugin.getManager().isVanished(event.getPlayer()) && VanishPerms.canReadChestsSilently(event.getPlayer())) {
            final Block block = event.getClickedBlock();
            Inventory inventory = null;
            final BlockState blockState = block.getState();
            boolean fake = false;
            Material i = block.getType();
            if (i == CompatibleMaterial.TRAPPED_CHEST.parseMaterial() || i == CompatibleMaterial.CHEST.parseMaterial()) {
                final Chest chest = (Chest) blockState;
                inventory = this.plugin.getServer().createInventory(player, chest.getInventory().getSize());
                inventory.setContents(chest.getInventory().getContents());
                fake = true;

            } else if (i == CompatibleMaterial.ENDER_CHEST.parseMaterial()) {
                if (this.plugin.getServer().getPluginManager().isPluginEnabled("EnderChestPlus") && VanishPerms.canNotInteract(player)) {
                    event.setCancelled(true);
                    return;
                }
                inventory = player.getEnderChest();

            } else if (i == CompatibleMaterial.DISPENSER.parseMaterial()) {
                inventory = ((Dispenser) blockState).getInventory();

            } else if (i == CompatibleMaterial.HOPPER.parseMaterial()) {
                inventory = ((Hopper) blockState).getInventory();

            } else if (i == CompatibleMaterial.DROPPER.parseMaterial()) {
                inventory = ((Dropper) blockState).getInventory();

            } else if (i == CompatibleMaterial.FURNACE.parseMaterial()) {
                inventory = ((Furnace) blockState).getInventory();

            } else if (i == CompatibleMaterial.BREWING_STAND.parseMaterial()) {
                inventory = ((BrewingStand) blockState).getInventory();

            }
            if (inventory != null) {
                event.setCancelled(true);
                if (fake) {
                    this.plugin.chestFakeOpen(player.getName());
                    player.sendMessage(ChatColor.AQUA + "[VNP] Opening chest silently. Can not edit.");
                }
                player.openInventory(inventory);
                return;
            }
        }
        if (this.plugin.getManager().isVanished(player) && VanishPerms.canNotInteract(player)) {
            event.setCancelled(true);
            return;
        }
        if ((event.getAction() == Action.PHYSICAL) && (event.getClickedBlock().getType() == CompatibleMaterial.FARMLAND.parseMaterial())) {
            if (this.plugin.getManager().isVanished(player) && VanishPerms.canNotTrample(player)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (this.plugin.getManager().isVanished(player) && VanishPerms.canNotPickUp(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        if (this.plugin.getManager().isVanished(player)) {
            this.plugin.messageStatusUpdate(ChatColor.DARK_AQUA + event.getPlayer().getName() + " has quit vanished");
        }
        this.plugin.getManager().playerQuit(player);
        this.plugin.hooksQuit(player);
        this.plugin.getManager().getAnnounceManipulator().dropDelayedAnnounce(player.getName());
        if (!this.plugin.getManager().getAnnounceManipulator().playerHasQuit(player.getName()) || VanishPerms.silentQuit(player)) {
            MetricsOverlord.getQuitInvisTracker().increment();
            event.setQuitMessage(null);
        }
        this.plugin.chestFakeClose(event.getPlayer().getName());
    }

    @EventHandler(ignoreCancelled = true)
    public void onShear(PlayerShearEntityEvent event) {
        if (this.plugin.getManager().isVanished(event.getPlayer()) && VanishPerms.canNotInteract(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onWorldChange(PlayerChangedWorldEvent event) {
        if (Settings.getWorldChangeCheck()) {
            this.plugin.getManager().playerRefresh(event.getPlayer());
        }
    }
}