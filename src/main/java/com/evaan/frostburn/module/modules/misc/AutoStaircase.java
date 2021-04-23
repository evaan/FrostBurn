package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
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
 * @Author evaan
 * https://github.com/evaan
 */
public class AutoStaircase extends Module {
    public AutoStaircase() {super("AutoStaircase", Category.MISC);}

    Setting<Boolean> airPlace = register(new Setting("AirPlace", this, true));

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) {disable(); return;}
        if (!mc.player.isOnGround() || !(mc.player.inventory.getMainHandStack().getItem() instanceof BlockItem)) return;
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
            default:
            	break;
        }
        if (mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            mc.options.keyForward.setPressed(false);
            if (!airPlace.getValue()) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos.down()), Direction.DOWN, pos, false));
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos), Direction.DOWN, pos, false));
            mc.player.swingHand(Hand.MAIN_HAND);
        }
        if (!mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            mc.options.keyForward.setPressed(true);
            mc.player.jump();
        }
    }
}
