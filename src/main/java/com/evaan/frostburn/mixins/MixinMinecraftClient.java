package com.evaan.frostburn.mixins;

import com.evaan.frostburn.FrostBurn;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author evaan
 * https://github.com/evaan
 */
@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {

    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    public void getWindowTitle(CallbackInfoReturnable<String> ci){
        ci.setReturnValue("FrostBurn 1.0");
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;setIcon(Ljava/io/InputStream;Ljava/io/InputStream;)V"))
    public void setAlternativeWindowIcon(Window window, InputStream inputStream1, InputStream inputStream2) throws IOException {
        window.setIcon(
                FrostBurn.class.getResourceAsStream("/assets/frostburn/16.png"),
                FrostBurn.class.getResourceAsStream("/assets/frostburn/32.png")
        );
    }
}
