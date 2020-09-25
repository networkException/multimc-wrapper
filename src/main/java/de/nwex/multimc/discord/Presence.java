package de.nwex.multimc.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class Presence {

    private final DiscordRPC presence;

    @SuppressWarnings("BusyWait")
    public Presence() {
        presence = DiscordRPC.INSTANCE;
        presence.Discord_Initialize("703315329476198440", new DiscordEventHandlers(), true, null);

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                presence.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler").start();

        System.out.println("Started Presence");
    }

    public void clear() {
        presence.Discord_ClearPresence();
    }

    public void update(String details) {
        System.out.println("Updated presence to '" + details + "'");

        DiscordRichPresence updated = new DiscordRichPresence();
        updated.startTimestamp = System.currentTimeMillis() / 1000;
        updated.details = details;
        updated.largeImageKey = "mmc";
        updated.largeImageText = "Using MultiMC";
        updated.smallImageKey = "bbn_logo";
        updated.smallImageText = "Coded by BBN";
        presence.Discord_UpdatePresence(updated);
    }
}
