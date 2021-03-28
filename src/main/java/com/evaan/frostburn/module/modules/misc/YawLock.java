package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

public class YawLock extends Module {
    public YawLock() {super("YawLock", Category.MISC);}

    Setting<Float> yaw;

    @Override
    public void onEnable() {
        yaw = register(new Setting("yaw", this, 0, 0, 7));
    }

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) {
            disable();
            return;
        }

        if (yaw.getValue() == 0f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX(),
                            mc.player.getEyeY(),
                            mc.player.getZ() - 1));
        } else if (yaw.getValue() == 1f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() + 1,
                            mc.player.getEyeY(),
                            mc.player.getZ() - 1));
        } else if (yaw.getValue() == 2f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() + 1,
                            mc.player.getEyeY(),
                            mc.player.getZ()));
        } else if (yaw.getValue() == 3f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() + 1,
                            mc.player.getEyeY(),
                            mc.player.getZ() + 1));
        } else if (yaw.getValue() == 4f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX(),
                            mc.player.getEyeY(),
                            mc.player.getZ() + 1));
        } else if (yaw.getValue() == 5f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() - 1,
                            mc.player.getEyeY(),
                            mc.player.getZ() + 1));
        } else if (yaw.getValue() == 6f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() - 1,
                            mc.player.getEyeY(),
                            mc.player.getZ()));
        } else if (yaw.getValue() == 7f) {
            mc.player.lookAt(
                    EntityAnchorArgumentType.EntityAnchor.EYES,
                    new Vec3d(
                            mc.player.getX() - 1,
                            mc.player.getEyeY(),
                            mc.player.getZ() - 1));
        }
    }
}
