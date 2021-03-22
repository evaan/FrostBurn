package com.evaan.frostburn.mixins;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {

    @Inject(method = "clipAtLedge()Z", at = @At("HEAD"), cancellable = true)
    private void clipAtLedge(CallbackInfoReturnable<Boolean> cir) {
        if(ModuleManager.getModule("SafeWalk").isEnabled()) cir.setReturnValue(true);
    }


    @Inject(method = "tickMovement()V", at = @At("TAIL"), cancellable = true)
    private void tickMovement(CallbackInfo cir) {
        ClientPlayerEntity player = FrostBurn.mc.player;
        if(player == null) return;

        if(ModuleManager.getModule("NoFall").isEnabled()) {
            PlayerMoveC2SPacket packet = new PlayerMoveC2SPacket(true);
            player.networkHandler.sendPacket(packet);
        }
    }
}
