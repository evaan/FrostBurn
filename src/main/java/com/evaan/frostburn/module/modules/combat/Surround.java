package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Surround extends Module {
    public Surround() {super("Surround", Category.COMBAT);}

    int obiSlot;
    int oldSlot;

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        oldSlot = mc.player.inventory.selectedSlot;
        obiSlot = -1;
        if (!mc.player.isOnGround()) {disable(); return;}
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(mc.player.getBlockPos().north());
        positions.add(mc.player.getBlockPos().east());
        positions.add(mc.player.getBlockPos().south());
        positions.add(mc.player.getBlockPos().west());
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStack(i).getItem().equals(Items.OBSIDIAN)) {
                obiSlot = i;
                break;
            }
        }
        if (obiSlot == -1) {disable(); return;}
        for (BlockPos pos : positions) {
            if (!mc.world.getBlockState(pos).getMaterial().isReplaceable()) continue;
            for (Direction direction : Direction.values()) {
                if (!mc.world.getBlockState(pos.offset(direction)).getMaterial().isReplaceable()) {
                    mc.player.inventory.selectedSlot = obiSlot;
                    mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(pos), direction, pos, false));
                    mc.player.inventory.selectedSlot = oldSlot;
                }
            }
        }
    }
}
