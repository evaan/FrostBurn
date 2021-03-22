package com.evaan.frostburn.mixins;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.ModuleManager;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;


@Mixin(BoatEntity.class)
public abstract class MixinBoatEntity extends Entity {

	public MixinBoatEntity(EntityType<BoatEntity> type, World world) {
		super(type, world);
	}

	@Inject(method = "updateVelocity()V", at = @At("TAIL"), cancellable = true)
	private void velocityOverride(CallbackInfo cir) {
		
		if(ModuleManager.getModule("BoatFly").isEnabled()) {
			ClientPlayerEntity player = FrostBurn.mc.player;
			if(player == null) return;
			
			if(player.hasVehicle() && this.hasPlayerRider()) { //make sure they are riding boat
			
				Vec3d origV = this.getVelocity();
				double y = FrostBurn.mc.options.keyJump.isPressed() ? 0.5 : 0;
				this.setVelocity(new Vec3d(origV.x, y, origV.z)); //create and return adjusted velocity
			}
		}
	}

}
