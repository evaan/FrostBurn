package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


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
        switch(mc.player.getMovementDirection()) {
            case NORTH:
                //set north
                break;
            case EAST:
                //set east
                break;
            case SOUTH:
                //set south
                break;
            case WEST:
                //set west
                break;
        }
        if (!mc.player.isOnGround()) return;
        BlockPos pos = mc.player.getBlockPos().offset(mc.player.getMovementDirection());
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
