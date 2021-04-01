package com.evaan.frostburn.module.modules.movement;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;

/**
 * @Author majorsopa
 * https://github.com/majorsopa
 */
public class Step extends Module {
    public Step() {super("Step", Category.MOVEMENT);}

    Setting<Float> newStepHeight = register(
            new Setting(
                    "StepHeight",
                    this,
                    1f,
                    0f,
                    100f
            )
    );

    @Override
    public void onUpdate() {
        if (mc.player == null) {return;}
        mc.player.stepHeight = newStepHeight.getValue();
    }

    @Override
    public void onDisable() {
        mc.player.stepHeight = 0.6f;
    }
}
