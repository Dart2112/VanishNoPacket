package org.kitteh.vanish;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class VanishPerms {
    private static Map<String, VanishUser> users = Collections.synchronizedMap(new HashMap<>());

    public static boolean blockIncomingDamage(Player player) {
        return VanishPerms.getUser(player).getPreventIncomingDamage();
    }

    public static boolean blockOutgoingDamage(Player player) {
        return VanishPerms.getUser(player).getPreventOutgoingDamage();
    }

    static boolean canEffectBats(Player player) {
        return VanishPerms.getUser(player).getEffectBats();
    }

    static boolean canEffectExplode(Player player) {
        return VanishPerms.getUser(player).getEffectExplode();
    }

    static boolean canEffectFlames(Player player) {
        return VanishPerms.getUser(player).getEffectFlames();
    }

    static boolean canEffectLightning(Player player) {
        return VanishPerms.getUser(player).getEffectLightning();
    }

    static boolean canEffectSmoke(Player player) {
        return VanishPerms.getUser(player).getEffectSmoke();
    }

    static boolean canFakeAnnounce(Player player) {
        return player.hasPermission("vanish.fakeannounce");
    }

    static boolean canList(CommandSender sender) {
        return sender.hasPermission("vanish.list");
    }

    public static boolean canNotChat(Player player) {
        return VanishPerms.getUser(player).getNoChat();
    }

    public static boolean canNotFollow(Player player) {
        return VanishPerms.getUser(player).getNoFollow();
    }

    public static boolean canNotHunger(Player player) {
        return VanishPerms.getUser(player).getNoHunger();
    }

    public static boolean canNotInteract(Player player) {
        return VanishPerms.getUser(player).getNoInteract();
    }

    public static boolean canNotPickUp(Player player) {
        return VanishPerms.getUser(player).getNoPickup();
    }

    public static boolean canNotTrample(Player player) {
        return player.hasPermission("vanish.notrample");
    }

    public static boolean canReadChestsSilently(Player player) {
        return VanishPerms.getUser(player).getReadChestsSilently();
    }

    public static boolean canReceiveAdminAlerts(Player player) {
        return player.hasPermission("vanish.adminalerts");
    }

    static boolean canReload(CommandSender sender) {
        return sender.hasPermission("vanish.reload");
    }

    public static boolean canSeeAll(Player player) {
        return VanishPerms.getUser(player).getSeeAll();
    }

    public static boolean canSeeSpoutStatus(Player player) {
        return player.hasPermission("vanish.spout.status");
    }

    static boolean canSeeStatusUpdates(Player player) {
        return player.hasPermission("vanish.statusupdates");
    }

    static boolean canToggleDamageIn(Player player) {
        return player.hasPermission("vanish.toggle.damagein");
    }

    static boolean canToggleDamageOut(Player player) {
        return player.hasPermission("vanish.toggle.damageout");
    }

    static boolean canToggleEffectBats(Player player) {
        return player.hasPermission("vanish.effects.toggle.bats");
    }

    static boolean canToggleEffectExplode(Player player) {
        return player.hasPermission("vanish.effects.toggle.explode");
    }

    static boolean canToggleEffectFlames(Player player) {
        return player.hasPermission("vanish.effects.toggle.flames");
    }

    static boolean canToggleEffectLightning(Player player) {
        return player.hasPermission("vanish.effects.toggle.lightning");
    }

    static boolean canToggleNoChat(Player player) {
        return player.hasPermission("vanish.toggle.nochat");
    }

    static boolean canToggleNoFollow(Player player) {
        return player.hasPermission("vanish.toggle.nofollow");
    }

    static boolean canToggleNoHunger(Player player) {
        return player.hasPermission("vanish.toggle.nohunger");
    }

    static boolean canToggleNoInteract(Player player) {
        return player.hasPermission("vanish.toggle.nointeract");
    }

    static boolean canToggleNoPickup(Player player) {
        return player.hasPermission("vanish.toggle.nopickup");
    }

    static boolean canToggleSee(Player player) {
        return player.hasPermission("vanish.toggle.see");
    }

    static boolean canToggleSilentChestReads(Player player) {
        return player.hasPermission("vanish.toggle.silentchests");
    }

    static boolean canToggleSmoke(Player player) {
        return player.hasPermission("vanish.effects.toggle.smoke");
    }

    public static boolean canVanish(Player player) {
        return player.hasPermission("vanish.vanish");
    }

    static boolean canVanishOff(Player player) {
        return player.hasPermission("vanish.vanish.off");
    }

    static boolean canVanishOn(Player player) {
        return player.hasPermission("vanish.vanish.on");
    }

    public static boolean joinVanished(Player player) {
        return player.hasPermission("vanish.joinvanished");
    }

    public static boolean joinWithoutAnnounce(Player player) {
        return player.hasPermission("vanish.joinwithoutannounce");
    }

    public static boolean permTestOther(Player player) {
        return player.hasPermission("vanish.permtest.other");
    }

    public static boolean permTestSelf(Player player) {
        return player.hasPermission("vanish.permtest.self");
    }

    public static boolean silentQuit(Player player) {
        return player.hasPermission("vanish.silentquit");
    }

    static boolean toggleDamageIn(Player player) {
        return VanishPerms.getUser(player).toggleIncomingDamage();
    }

    static boolean toggleDamageOut(Player player) {
        return VanishPerms.getUser(player).toggleOutgoingDamage();
    }

    static boolean toggleEffectBats(Player player) {
        return VanishPerms.getUser(player).toggleEffectBats();
    }

    static boolean toggleEffectExplode(Player player) {
        return VanishPerms.getUser(player).toggleEffectExplode();
    }

    static boolean toggleEffectFlames(Player player) {
        return VanishPerms.getUser(player).toggleEffectFlames();
    }

    static boolean toggleEffectLightning(Player player) {
        return VanishPerms.getUser(player).toggleEffectLightning();
    }

    static boolean toggleEffectSmoke(Player player) {
        return VanishPerms.getUser(player).toggleEffectSmoke();
    }

    static boolean toggleNoChat(Player player) {
        return VanishPerms.getUser(player).toggleNoChat();
    }

    static boolean toggleNoFollow(Player player) {
        return VanishPerms.getUser(player).toggleNoFollow();
    }

    static boolean toggleNoHunger(Player player) {
        return VanishPerms.getUser(player).toggleNoHunger();
    }

    static boolean toggleNoInteract(Player player) {
        return VanishPerms.getUser(player).toggleNoInteract();
    }

    static boolean toggleNoPickup(Player player) {
        return VanishPerms.getUser(player).toggleNoPickup();
    }

    static boolean toggleSeeAll(Player player) {
        return VanishPerms.getUser(player).toggleSeeAll();
    }

    static boolean toggleSilentChestReads(Player player) {
        return VanishPerms.getUser(player).toggleSilentChestReads();
    }

    static void userQuit(Player player) {
        VanishPerms.users.remove(player.getName());
    }

    private static VanishUser getUser(Player player) {
        VanishUser user = VanishPerms.users.get(player.getName());
        if (user == null) {
            user = new VanishUser(player);
            VanishPerms.users.put(player.getName(), user);
        }
        return user;
    }
}