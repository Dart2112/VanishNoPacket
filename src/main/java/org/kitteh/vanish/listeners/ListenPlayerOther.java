package org.kitteh.vanish.listeners;

import me.kangarko.compatbridge.model.CompMaterial;
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
            if (i == CompMaterial.TRAPPED_CHEST.toMaterial() || i == CompMaterial.CHEST.toMaterial()) {
                final Chest chest = (Chest) blockState;
                inventory = this.plugin.getServer().createInventory(player, chest.getInventory().getSize());
                inventory.setContents(chest.getInventory().getContents());
                fake = true;

            } else if (i == CompMaterial.ENDER_CHEST.toMaterial()) {
                if (this.plugin.getServer().getPluginManager().isPluginEnabled("EnderChestPlus") && VanishPerms.canNotInteract(player)) {
                    event.setCancelled(true);
                    return;
                }
                inventory = player.getEnderChest();

            } else if (i == CompMaterial.DISPENSER.toMaterial()) {
                inventory = ((Dispenser) blockState).getInventory();

            } else if (i == CompMaterial.HOPPER.toMaterial()) {
                inventory = ((Hopper) blockState).getInventory();

            } else if (i == CompMaterial.DROPPER.toMaterial()) {
                inventory = ((Dropper) blockState).getInventory();

            } else if (i == CompMaterial.FURNACE.toMaterial()) {
                inventory = ((Furnace) blockState).getInventory();

            } else if (i == CompMaterial.BREWING_STAND.toMaterial()) {
                inventory = ((BrewingStand) blockState).getInventory();

            } else if (i == CompMaterial.BEACON.toMaterial()) {
                inventory = ((Beacon) blockState).getInventory();

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
        if ((event.getAction() == Action.PHYSICAL) && (event.getClickedBlock().getType() == CompMaterial.FARMLAND.toMaterial())) {
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