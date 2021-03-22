package com.evaan.frostburn.mixins;

import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.particle.ParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {

    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    public void renderWeather(LightmapTextureManager manager, float f, double d, double e, double g, CallbackInfo cir) {
        if(ModuleManager.getModule("NoWeather").isEnabled()) cir.cancel();
    }

    @Inject(method = "addParticle", at = @At("HEAD"), cancellable = true)
    public void addParticle(ParticleEffect parameters, boolean shouldAlwaysSpawn, double x, double y, double z, double velocityX, double velocityY, double velocityZ, CallbackInfo cir) {
        if(ModuleManager.getModule("NoParticle").isEnabled()) cir.cancel();
    }

}
