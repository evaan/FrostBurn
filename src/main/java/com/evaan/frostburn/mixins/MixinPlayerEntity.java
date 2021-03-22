package com.evaan.frostburn.mixins;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.module.modules.misc.Velocity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.options.GameOptions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.util.math.Vec3d;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {

    @Inject(method = "clipAtLedge()Z", at = @At("HEAD"), cancellable = true)
    private void clipAtLedge(CallbackInfoReturnable<Boolean> cir) {
        if(ModuleManager.getModule("SafeWalk").isEnabled()) cir.setReturnValue(true);
    }


    @Inject(method = "tickMovement()V", at = @At("TAIL"), cancellable = true)
    private void tickMovement(CallbackInfo cir) {
        ClientPlayerEntity player = FrostBurn.mc.player;
        if (player == null) return;

        if(ModuleManager.getModule("Fly").isEnabled()) {
            GameOptions options = FrostBurn.mc.options;
            if (options == null) return;

            Boolean jump = options.keyJump.isPressed();
            Boolean sneak = options.keySneak.isPressed();

            Vec3d orig = player.getVelocity();
            double fx, fz;
            fx = orig.x;
            fz = orig.z;

            // Set calculated velocity with appropriate Y
            if (jump && !sneak) {
                player.setVelocity(new Vec3d(fx, 0.5, fz));
            } else if (sneak && !jump) {
                player.setVelocity(new Vec3d(fx, -0.5, fz));
            } else {
                player.setVelocity(new Vec3d(fx, 0, fz));
            }
        }


        // Negate fall damage
        if(ModuleManager.getModule("NoFall").isEnabled()) {
            PlayerMoveC2SPacket packet = new PlayerMoveC2SPacket(true);
            player.networkHandler.sendPacket(packet);
        }
    }
}
