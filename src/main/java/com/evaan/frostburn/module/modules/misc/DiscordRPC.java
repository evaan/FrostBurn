package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.DiscordUtil;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.OtherClientPlayerEntity;

import java.util.UUID;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class DiscordRPC extends Module {
    public DiscordRPC() {super("DiscordRPC", Category.MISC);}

    @Override
    public void onEnable() {
        DiscordUtil.start();
    }

    @Override
    public void onDisable() {
        DiscordUtil.end();
    }
}
