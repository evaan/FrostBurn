package com.evaan.frostburn.mixins;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author evaan
 * https://github.com/evaan
 */
@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(at = @At("RETURN"), method = "tick()V", cancellable = true)
    public void tick(CallbackInfo info) {
        ModuleManager.modules.stream().filter(Module::isEnabled).forEach(Module::onUpdate);
    }
}
