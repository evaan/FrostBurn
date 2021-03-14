package com.evaan.frostburn.util;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class DiscordUtil {
    public static final String APP_ID = "820481496962826291";

    public static DiscordRichPresence presence;

    public static boolean connected;

    public static void start() {
        if (connected)
            return;
        connected = true;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize(APP_ID, handlers, true, "");
        presence.startTimestamp = System.currentTimeMillis() / 1000L;
        setRpcFromSettings();
        (new Thread(DiscordUtil::setRpcFromSettingsNonInt, "Discord-RPC-Callback-Handler")).start();
    }

    public static void end() {
        connected = false;
        rpc.Discord_Shutdown();
    }

    public static String getIP() {
        if (MinecraftClient.getInstance().isInSingleplayer()) return "Singleplayer";
        try {
            return MinecraftClient.getInstance().getCurrentServerEntry().address;
        } catch (Exception e) {
            return "Main Menu";
        }
    }

    private static void setRpcFromSettingsNonInt() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                rpc.Discord_RunCallbacks();
                details = getIP();
                presence.details = details;
                presence.state = state;
                rpc.Discord_UpdatePresence(presence);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        }
    }

    private static void setRpcFromSettings() {
        details = getIP();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "logo";
        presence.largeImageText = "FrostBurn 1.0";
        presence.smallImageKey = "";
        presence.smallImageText = "";
    }

    private static final DiscordRPC rpc = DiscordRPC.INSTANCE;

    private static String details;

    private static String state;

    static {
        presence = new DiscordRichPresence();
        connected = false;
    }
}
