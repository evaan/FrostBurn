package com.evaan.frostburn.mixins;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author evaan
 * https://github.com/evaan
 */
@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(at = @At("HEAD"), method = "renderHand", cancellable = true)
    private void renderHand(MatrixStack matrixStack_1, Camera camera, float tickDelta, CallbackInfo info) {
        ModuleManager.modules.stream().filter(Module::isEnabled).forEach(module -> {module.onRender(matrixStack_1);});
    }
}
