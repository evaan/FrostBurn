package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class YawLock extends Module {
    public YawLock() {super("YawLock", Category.MISC);}

    @Override
    public void onEnable() {
        if (mc.player == null) {disable(); return;}
    }

    @Override
    public void onRender(MatrixStack matrices) {
        switch (mc.player.getHorizontalFacing()) {
            case SOUTH: mc.player.yaw = 0; break;
            case EAST: mc.player.yaw = 270; break;
            case NORTH: mc.player.yaw = 180; break;
            case WEST: mc.player.yaw = 90; break;
        }
    }
}
