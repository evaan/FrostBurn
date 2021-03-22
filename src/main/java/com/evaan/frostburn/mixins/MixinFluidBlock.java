package com.evaan.frostburn.mixins;

import org.spongepowered.asm.mixin.Mixin;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.ModuleManager;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@Mixin(FluidBlock.class)
public abstract class MixinFluidBlock extends Block implements FluidDrainable {

	public MixinFluidBlock(Settings settings) {
		super(settings);
	}

	@Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		boolean sneaking = false;
		boolean underwater = false;
		boolean riding = false;
		try {
			ClientPlayerEntity player = FrostBurn.mc.player;
			sneaking = player.isSneaking();
			underwater = player.isSubmergedInWater();
			riding = player.isRiding();
		} catch (Exception e) {
			return super.getCollisionShape(state, world, pos, context);
		}
		
		if(ModuleManager.getModule("Jesus").isEnabled() && !sneaking && !underwater && !riding) {
			return VoxelShapes.fullCube();
		}
		
		return super.getCollisionShape(state, world, pos, context);
	}
}
