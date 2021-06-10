package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import com.google.common.collect.Streams;
import net.minecraft.block.BedBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BedItem;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class BedAura extends Module {
    //todo rewrite
    public BedAura() {super("BedAura", Category.COMBAT);}
    Setting<Float> range = register(new Setting("Range", this, 4.0f, 0.1f, 5.0f));
    Setting<Integer> delay = register(new Setting("Delay", this, 10, 0, 40));

    int bedSlot = -1;
    int oldSlot = -1;

    int ticks = 0;

    @Override
    public void onUpdate() {
        if (mc.world == null || mc.player == null) {disable(); return;}
        for (int i = 0; i < 9; i++) {if (mc.player.getInventory().getStack(i).getItem() instanceof BedItem) {bedSlot = i;}}
        if (bedSlot == -1) {disable(); return;}
        if (ticks != delay.getValue()) {ticks++; return;}
        else ticks = 0;
        if (mc.player == null || mc.world == null) {disable(); return;}
        List<Entity> players = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.getValue() && e != mc.player).collect(Collectors.toList());
        if (players.isEmpty()) {return;}
        PlayerEntity player = (PlayerEntity)players.get(0);
        ArrayList<Pair<BlockPos, Direction>> positions = new ArrayList<>();
        positions.add(new Pair<>(player.getBlockPos().north().up(), Direction.SOUTH));
        positions.add(new Pair<>(player.getBlockPos().east().up(), Direction.WEST));
        positions.add(new Pair<>(player.getBlockPos().south().up(), Direction.NORTH));
        positions.add(new Pair<>(player.getBlockPos().west().up(), Direction.EAST));
        positions.sort(Comparator.comparing(object -> object.getLeft().getSquaredDistance(mc.player.getX(), mc.player.getY(), mc.player.getZ(), true)));
        for (Pair<BlockPos, Direction> pair : positions) {
            BlockPos blockPos = pair.getLeft();
            Direction direction = pair.getRight();
            oldSlot = mc.player.getInventory().selectedSlot;
            mc.player.getInventory().selectedSlot = bedSlot;
            if (!(mc.world.getBlockState(blockPos)).getMaterial().isReplaceable()) continue;
            if (mc.world.getBlockState(blockPos.offset(direction)).getBlock() instanceof BedBlock) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos.offset(direction)), Direction.DOWN, blockPos.offset(direction), true));
            if (!(mc.world.getBlockState(blockPos).getBlock() instanceof BedBlock))  {
                if (direction == Direction.NORTH) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(-180f, mc.player.getPitch(), true));
                if (direction == Direction.EAST) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(-90f, mc.player.getPitch(), true));
                if (direction == Direction.SOUTH) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(0f, mc.player.getPitch(), true));
                if (direction == Direction.WEST) mc.player.networkHandler.sendPacket(new PlayerMoveC2SPacket.LookAndOnGround(90f, mc.player.getPitch(), true));
                mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, false));
            }
            //todo maybe do it on another tick
            if (mc.world.getBlockState(blockPos).getBlock() instanceof BedBlock) mc.interactionManager.interactBlock(mc.player, mc.world, Hand.MAIN_HAND, new BlockHitResult(Vec3d.of(blockPos), Direction.DOWN, blockPos, true));
            mc.player.getInventory().selectedSlot = oldSlot;
            break;
        }
    }
}
