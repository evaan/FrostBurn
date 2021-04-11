package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.network.OtherClientPlayerEntity;

import java.util.UUID;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FakePlayer extends Module {
    public FakePlayer() {super("FakePlayer", Category.MISC);}

    @Override
    public void onEnable() {
        if (mc.world == null || mc.player == null) {disable(); return;}
        OtherClientPlayerEntity player = new OtherClientPlayerEntity(mc.world, new GameProfile(UUID.fromString("0f75a81d-70e5-43c5-b892-f33c524284f2"), "popbob"));
        player.copyPositionAndRotation(mc.player);
        player.setHeadYaw(mc.player.headYaw);
        mc.world.addEntity(-100, player);
    }

    @Override
    public void onDisable() {
        if (mc.world != null) mc.world.removeEntity(-100);
    }
}
