package com.evaan.frostburn.module.modules.movement;

import com.evaan.frostburn.module.Module;
import net.minecraft.client.MinecraftClient;

/**
 * @Author majorsopa
 * https://github.com/majorsopa
 */
public class Step extends Module {
    public Step() {super("Step", Category.MOVEMENT);}

    float prevStepHeight;

    @Override
    public void onEnable() {
        if (mc.player == null) {return;}
        prevStepHeight = mc.player.stepHeight;
        mc.player.stepHeight = 1.5f;
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = prevStepHeight;
    }
}
