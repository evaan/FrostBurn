package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;


/**
 * @Author majorsopa
 * https://github.com/majorsopa
 */
public class AutoStaircase extends Module {
    public AutoStaircase() {super("AutoStaircase", Category.MISC);}

    boolean jumping = false;

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) {disable(); return;}
        if (!mc.player.isOnGround()) return;
        BlockPos pos = mc.player.getBlockPos().offset(mc.player.getMovementDirection());
        switch (mc.player.getMovementDirection()) {
            case NORTH:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX(), mc.player.getY(), mc.player.getZ() - 1));
                break;
            case EAST:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX() + 1, mc.player.getY(), mc.player.getZ()));
                break;
            case SOUTH:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX(), mc.player.getY(), mc.player.getZ() + 1));
                break;
            case WEST:
                mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d(mc.player.getX() - 1, mc.player.getY(), mc.player.getZ()));
                break;
        }
        if (mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            mc.options.keyForward.setPressed(false);
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos), Direction.DOWN, pos, false));
            mc.player.swingHand(Hand.MAIN_HAND);
        }
        if (!mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            mc.options.keyForward.setPressed(true);
            mc.player.jump();
        }
    }
}
