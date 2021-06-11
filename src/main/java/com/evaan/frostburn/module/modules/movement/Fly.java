package com.evaan.frostburn.module.modules.movement;

import com.evaan.frostburn.module.Module;
import net.minecraft.entity.player.PlayerAbilities;

public class Fly extends Module {
    public Fly() {super("Fly", Module.Category.MOVEMENT);}

    @Override
    public void onUpdate() {
        assert mc.player != null;

        PlayerAbilities abilities = mc.player.getAbilities();
        abilities.flying = true;

        mc.player.sendAbilitiesUpdate();
    }

    @Override
    public void onDisable() {
        assert mc.player != null;

        PlayerAbilities abilities = mc.player.getAbilities();
        abilities.flying = false;

        mc.player.sendAbilitiesUpdate();

    }
}
