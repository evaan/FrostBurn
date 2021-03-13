package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import com.google.common.collect.Streams;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class AutoAnchor extends Module {
    public AutoAnchor() {super("AutoAnchor", Category.COMBAT);}
    Setting<Float> range = register(new Setting("Range", this, 4.0f, 0.1f, 5.0f));
    Setting<Integer> delay = register(new Setting("Delay", this, 2, 0, 40));

    int anchorSlot = -1;
    int glowStoneSlot = -1;
    int oldSlot = -1;

    int ticks = 0;

    @Override
    public void onUpdate() {
        if (mc.world == null || mc.player == null) {disable(); return;}
        for (int i = 0; i < 9; i++) {if (mc.player.inventory.getStack(i).getItem().equals(Items.RESPAWN_ANCHOR)) {anchorSlot = i;} else if (mc.player.inventory.getStack(i).getItem().equals(Items.GLOWSTONE)) {glowStoneSlot = i;}}
        if (anchorSlot == -1 || glowStoneSlot == -1) {disable(); return;}
        if (ticks != delay.getValue()) {ticks++; return;}
        else ticks = 0;
        try {
            PlayerEntity player = (PlayerEntity) Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList()).get(0);
            for (Direction direction : Direction.values()) {
                BlockPos blockPos = null;
                if (player.getBlockPos().getSquaredDistance(mc.player.getX(), mc.player.getY(), mc.player.getZ(), true) < 6.0f && !(mc.world.getBlockState(player.getBlockPos()).getBlock() != Blocks.RESPAWN_ANCHOR && mc.world.getBlockState(player.getBlockPos()).getBlock() != Blocks.AIR && mc.world.getBlockState(player.getBlockPos()).getMaterial().isReplaceable())) blockPos = player.getBlockPos();
                else if (player.getBlockPos().offset(direction).getSquaredDistance(mc.player.getX(), mc.player.getY(), mc.player.getZ(), true) < 6.0f && (mc.world.getBlockState(player.getBlockPos().offset(direction)).getBlock() == Blocks.RESPAWN_ANCHOR || mc.world.getBlockState(player.getBlockPos().offset(direction)).getMaterial().isReplaceable())) blockPos = player.getBlockPos().offset(direction);
                if (blockPos == null) continue;
                mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, false));
                if (mc.world.getBlockState(blockPos).getBlock().equals(Blocks.RESPAWN_ANCHOR)) {
                    mc.player.inventory.selectedSlot = glowStoneSlot;
                    mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
                    mc.interactionManager.interactBlock(mc.player, mc.world, Hand.OFF_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
                }
                mc.player.inventory.selectedSlot = oldSlot;
                break;
            }
        } catch (Exception ignored) {}
    }
}
