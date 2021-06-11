package com.evaan.frostburn.module.modules.movement;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.entity.player.PlayerAbilities;

public class Fly extends Module {
    public Fly() {super("Fly", Module.Category.MOVEMENT);}

    Setting<Float> speed = register(
            new Setting(
                    "Speed",
                    this,
                    100f,
                    1f,
                    500f
            )
    );

    @Override
    public void onUpdate() {
        assert mc.player != null;

        PlayerAbilities abilities = mc.player.getAbilities();
        abilities.flying = true;

        abilities.setFlySpeed(speed.getValue() / 5000);

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
