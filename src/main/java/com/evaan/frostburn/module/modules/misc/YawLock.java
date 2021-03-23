package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.math.Vec3d;

public class YawLock extends Module {
    public YawLock() {super("YawLock", Category.MISC);}

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) {disable(); return;}

        double whereLook = mc.player.getEyeY();

        switch (mc.player.getMovementDirection()) {
            case NORTH:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX(), whereLook, mc.player.getZ() - 1));
                break;
            case EAST:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX() + 1, whereLook, mc.player.getZ()));
                break;
            case SOUTH:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX(), whereLook, mc.player.getZ() + 1));
                break;
            case WEST:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX() - 1, whereLook, mc.player.getZ()));
                break;
            default:
                break;
        }
    }
}
