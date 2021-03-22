package com.evaan.frostburn.module.modules.movement;

import com.evaan.frostburn.module.Module;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Sprint extends Module {
    public Sprint() {super("Sprint", Category.MOVEMENT);}

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        mc.player.setSprinting(mc.player.input.movementForward != 0 || mc.player.input.movementSideways != 0);
    }
}
