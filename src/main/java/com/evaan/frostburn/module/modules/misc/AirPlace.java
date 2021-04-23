package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import net.minecraft.block.AirBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

/**
 * @Author Gopro336
 * https://github.com/Gopro336
 */
//TODO: Add an esp for the targeted placePos
public class AirPlace extends Module {

    private BlockPos placePos;

    public AirPlace() {super("AirPlace", Category.MISC);}

    @Override
    public void onUpdate() {
        if (mc.crosshairTarget instanceof BlockHitResult && mc.player.getMainHandStack().getItem() instanceof BlockItem) {

            try {
                placePos = ((BlockHitResult) mc.crosshairTarget).getBlockPos();
            } catch (Exception ignored) {} //apparently if you look at an entity the game crashes

            if (mc.world.getBlockState(placePos).getBlock() instanceof AirBlock) {

                if (mc.options.keyUse.wasPressed() || mc.options.keyUse.isPressed()) {
                    mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(placePos), Direction.DOWN, placePos, false));
                    mc.player.swingHand(Hand.MAIN_HAND);
                }
            }
        }
    }
}
