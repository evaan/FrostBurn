package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.item.BlockItem;
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

    int toWalk = 0;

    Boolean crouching = true;

    @Override
    public void onEnable() {
        if (mc.player == null) return;

        // set vectors for directions
        // east
        Vec3d eastWalk = new Vec3d(mc.player.getX() + 1, mc.player.getY() - 2.05913, mc.player.getZ());
        // north
        Vec3d northWalk = new Vec3d(mc.player.getX(), mc.player.getY() - 2.05913, mc.player.getZ() - 1);
        // west
        Vec3d westWalk = new Vec3d(mc.player.getX() - 1, mc.player.getY() - 2.05913, mc.player.getZ());
        // south
        Vec3d southWalk = new Vec3d(mc.player.getX(), mc.player.getY() - 2.05913, mc.player.getZ() + 1);

        if (mc.player.getHorizontalFacing() == Direction.NORTH) {
            mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, northWalk);
        } else if (mc.player.getHorizontalFacing() == Direction.WEST) {
            mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, westWalk);
        } else if (mc.player.getHorizontalFacing() == Direction.SOUTH) {
            mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, southWalk);
        } else {
            mc.player.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, eastWalk);
        }

        try {
            for (int i = 0; i < 9; i++) {
                if (mc.player.inventory.getStack(i).getItem() instanceof BlockItem) {
                    mc.player.inventory.selectedSlot = i;
                }
            }
        } catch (Exception e) {
            System.out.println("no blocks");
        }
    }

    @Override
    public void onUpdate() {

        if (mc.player == null) return;


        if (!mc.player.isOnGround()) {
            return;
        }
        BlockPos pos = mc.player.getBlockPos().offset(mc.player.getHorizontalFacing());
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
