package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class AntiAim extends Module {
    public AntiAim() {super("AntiAim", Category.MISC);}

    @Override
    public void onUpdate() {
        //if (mc.player == null) {disable(); return;}
        //if (!mc.player.isUsingItem()) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookOnly(-(mc.player.yaw*2), 90, true));
    }
}
