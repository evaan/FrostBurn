package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class Fullbright extends Module {
    public Fullbright() {super("Fullbright", Category.RENDER);}

    Setting<Boolean> gammaMode = register(
            new Setting(
                    "GammaMode",
                    this,
                    true
            )
    );

    @Override
    public void onEnable() {
        if (gammaMode.getValue()) {
            mc.options.gamma = 100;
        }
    }

    @Override
    public void onDisable() {
        if (gammaMode.getValue()){
            mc.options.gamma = 0;
        }
    }

    @Override
    public void onUpdate() {
        if (!gammaMode.getValue()) {
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 6, 1));
        }
    }
}
