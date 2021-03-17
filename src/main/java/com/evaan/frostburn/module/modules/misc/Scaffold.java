package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Scaffold extends Module {
    public Scaffold() {super("Scaffold", Category.MISC);}

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) {disable(); return;}
        BlockPos pos = mc.player.getBlockPos().down();
        if (mc.world.getBlockState(pos).getMaterial().isReplaceable()) {
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos), Direction.DOWN, pos, false));
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
