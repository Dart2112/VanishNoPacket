package org.kitteh.vanish;

import org.bukkit.entity.Player;

final class VanishUser {
    private boolean seeAll;
    private boolean noPickup;
    private boolean noFollow;
    private boolean preventIncomingDamage;
    private boolean preventOutgoingDamage;
    private boolean noInteract;
    private boolean noHunger;
    private boolean noChat;
    private boolean silentChestReads;
    private boolean smoke;
    private boolean flames;
    private boolean explode;
    private boolean lightning;
    private boolean bats;

    VanishUser(Player player) {
        this.seeAll = player.hasPermission("vanish.see");
        this.noPickup = player.hasPermission("vanish.nopickup");
        this.noFollow = player.hasPermission("vanish.nofollow");
        this.preventIncomingDamage = player.hasPermission("vanish.preventincomingdamage");
        this.preventOutgoingDamage = player.hasPermission("vanish.preventoutgoingdamage");
        this.noInteract = player.hasPermission("vanish.nointeract");
        this.noHunger = player.hasPermission("vanish.nohunger");
        this.noChat = player.hasPermission("vanish.nochat");
        this.silentChestReads = player.hasPermission("vanish.silentchests");
        this.smoke = player.hasPermission("vanish.effects.smoke");
        this.flames = player.hasPermission("vanish.effects.flames");
        this.explode = player.hasPermission("vanish.effects.explode");
        this.lightning = player.hasPermission("vanish.effects.lightning");
        this.bats = player.hasPermission("vanish.effects.bats");
    }

    boolean getEffectBats() {
        return this.bats;
    }

    boolean getEffectExplode() {
        return this.explode;
    }

    boolean getEffectFlames() {
        return this.flames;
    }

    boolean getEffectLightning() {
        return this.lightning;
    }

    boolean getEffectSmoke() {
        return this.smoke;
    }

    boolean getNoChat() {
        return this.noChat;
    }

    boolean getNoFollow() {
        return this.noFollow;
    }

    boolean getNoHunger() {
        return this.noHunger;
    }

    boolean getNoInteract() {
        return this.noInteract;
    }

    boolean getNoPickup() {
        return this.noPickup;
    }

    boolean getPreventIncomingDamage() {
        return this.preventIncomingDamage;
    }

    boolean getPreventOutgoingDamage() {
        return this.preventOutgoingDamage;
    }

    boolean getReadChestsSilently() {
        return this.silentChestReads;
    }

    boolean getSeeAll() {
        return this.seeAll;
    }

    boolean toggleEffectBats() {
        this.bats = !this.bats;
        return this.bats;
    }

    boolean toggleEffectExplode() {
        this.explode = !this.explode;
        return this.explode;
    }

    boolean toggleEffectFlames() {
        this.flames = !this.flames;
        return this.flames;
    }

    boolean toggleEffectLightning() {
        this.lightning = !this.lightning;
        return this.lightning;
    }

    boolean toggleEffectSmoke() {
        this.smoke = !this.smoke;
        return this.smoke;
    }

    boolean toggleIncomingDamage() {
        this.preventIncomingDamage = !this.preventIncomingDamage;
        return this.preventIncomingDamage;
    }

    boolean toggleNoChat() {
        this.noChat = !this.noChat;
        return this.noChat;
    }

    boolean toggleNoFollow() {
        this.noFollow = !this.noFollow;
        return this.noFollow;
    }

    boolean toggleNoHunger() {
        this.noHunger = !this.noHunger;
        return this.noHunger;
    }

    boolean toggleNoInteract() {
        this.noInteract = !this.noInteract;
        return this.noInteract;
    }

    boolean toggleNoPickup() {
        this.noPickup = !this.noPickup;
        return this.noPickup;
    }

    boolean toggleOutgoingDamage() {
        this.preventOutgoingDamage = !this.preventOutgoingDamage;
        return this.preventOutgoingDamage;
    }

    boolean toggleSeeAll() {
        this.seeAll = !this.seeAll;
        return this.seeAll;
    }

    boolean toggleSilentChestReads() {
        this.silentChestReads = !this.silentChestReads;
        return this.silentChestReads;
    }
}