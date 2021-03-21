package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Burrow extends Module {
    public Burrow() {super("Burrow", Category.COMBAT);}

    BlockPos playerPos;
    int oldSlot;

    @Override
    public void onEnable() {
        oldSlot = mc.player.inventory.selectedSlot;
        this.playerPos = mc.player.getBlockPos();
        boolean found = false;
        for (int i = 0; i < 9; i++) {
            if (mc.player.inventory.getStack(i).getItem().equals(Items.OBSIDIAN)) {
                mc.player.inventory.selectedSlot = i;
                found = true;
                break;
            }
        }
        if (mc.world.getBlockState(this.playerPos).getBlock().equals(Blocks.OBSIDIAN) || !found) {
            this.disable();
            return;
        }
        else mc.player.jump();
    }

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        if (mc.player.getY() > this.playerPos.getY() + 1.04) {
            mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(playerPos), Direction.DOWN, playerPos, false));
            mc.player.swingHand(Hand.MAIN_HAND);
            mc.player.jump();
            this.disable();
            mc.player.inventory.selectedSlot = oldSlot;
        }
    }
}
