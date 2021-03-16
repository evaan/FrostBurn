package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.module.Module;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class Fullbright extends Module {
    public Fullbright() {super("Fullbright", Category.RENDER);}

    @Override
    public void onUpdate() {

        PlayerEntity player = mc.player;
        World world = mc.world;

        mc.options.gamma = 100;
    }
}
