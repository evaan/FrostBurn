package com.evaan.frostburn.mixins;

import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @Author evaan
 * https://github.com/evaan
 */
@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At(value = "INVOKE", target = "net/minecraft/client/util/InputUtil.isKeyPressed(JI)Z", ordinal = 5), cancellable = true)
    private void onKeyEvent(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo callbackInfo) {
        ModuleManager.modules.forEach(module -> { if (module.getBind() == key) module.toggle(); });
    }
}
