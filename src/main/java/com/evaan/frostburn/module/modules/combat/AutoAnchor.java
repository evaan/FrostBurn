package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.setting.Setting;
import com.google.common.collect.Streams;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class AutoAnchor extends Module {
    //hey i mean it works.. kind of
    public AutoAnchor() {super("AutoAnchor", Category.COMBAT);}

    Setting<Float> range = register(new Setting("Range", this, 4.0f, 0.1f, 5.0f));
    Setting<Integer> delay = register(new Setting("Delay", this, 2, 0, 40));
    Setting<Integer> charges = register(new Setting("Charges", this, 2, 1, 4));

    int anchorSlot = -1;
    int glowStoneSlot = -1;
    int oldSlot = -1;

    int ticks = 0;

    @Override
    public void onUpdate() {
        if (ticks != delay.value) {ticks++; return;}
        else ticks = 0;
        if (mc.player == null || mc.world == null) {disable(); return;}
        List<Entity> players = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.value && e != mc.player).collect(Collectors.toList());
        if (players.isEmpty()) return;
        PlayerEntity player = (PlayerEntity)players.get(0);
        ArrayList<BlockPos> positions = new ArrayList<>();
        positions.add(new BlockPos(player.getBlockPos().north()));
        positions.add(new BlockPos(player.getBlockPos().east()));
        positions.add(new BlockPos(player.getBlockPos().south()));
        positions.add(new BlockPos(player.getBlockPos().west()));
        positions.add(new BlockPos(player.getBlockPos().north(2)));
        positions.add(new BlockPos(player.getBlockPos().east(2)));
        positions.add(new BlockPos(player.getBlockPos().south(2)));
        positions.add(new BlockPos(player.getBlockPos().west(2)));
        for (int i = 0; i < 9; i++) {if (mc.player.inventory.getStack(i).getItem().equals(Items.RESPAWN_ANCHOR)) {anchorSlot = i;} else if (mc.player.inventory.getStack(i).getItem().equals(Items.GLOWSTONE) && mc.player.inventory.getStack(i).getCount() >= 4) {glowStoneSlot = i;}}
        if (anchorSlot == -1 || glowStoneSlot == -1) {disable(); return;}
        for (BlockPos blockPos : positions) {
            oldSlot = mc.player.inventory.selectedSlot;
            mc.player.inventory.selectedSlot = anchorSlot;
            if (blockPos.getSquaredDistance(mc.player.getX(), mc.player.getY(), mc.player.getZ(), true) > 6.0f || (mc.world.getBlockState(blockPos).getBlock() != Blocks.RESPAWN_ANCHOR && mc.world.getBlockState(blockPos).getBlock() != Blocks.AIR && mc.world.getBlockState(blockPos).getMaterial().isReplaceable())) continue;
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, false));
            mc.player.inventory.selectedSlot = glowStoneSlot;
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.RESPAWN_ANCHOR) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            //todo make this on seperate ticks because servers will have a stroke if you dont, actually maybe they wont idk i didnt check
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.RESPAWN_ANCHOR && charges.value == 2) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.RESPAWN_ANCHOR && charges.value == 3) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.RESPAWN_ANCHOR && charges.value == 4) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            if (mc.world.getBlockState(blockPos).getBlock() == Blocks.RESPAWN_ANCHOR) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.OFF_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            mc.player.inventory.selectedSlot = oldSlot;
            break;
        }
    }
}
