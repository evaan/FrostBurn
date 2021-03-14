package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Sprint extends Module {
    public Sprint() {super("Sprint", Category.MISC);}

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        if (mc.player.input.movementForward > 0 || mc.player.input.movementSideways > 0) mc.player.setSprinting(true);
        else mc.player.setSprinting(false);
    }
}
