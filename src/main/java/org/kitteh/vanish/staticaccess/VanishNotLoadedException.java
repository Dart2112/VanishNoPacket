package org.kitteh.vanish.staticaccess;

/**
 * Our princess is in another castle
 */
final class VanishNotLoadedException extends Exception {
    private static final long serialVersionUID = 1L;

    VanishNotLoadedException() {
        super("VanishNoPacket isn't loaded!");
    }
}