package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ModuleButton {
    Module module;
    int x;
    int y;

    public ModuleButton(Module module, int x, int y) {
        this.module = module;
        this.x = x;
        this.y = y;
    }

    TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    public void render(MatrixStack matrices) {
        if (module.isEnabled()) DrawableHelper.fill(matrices, x, y-2, x+100, y+10, 0xa3a3a3a3);
        textRenderer.draw(matrices, new LiteralText(module.getName()), x+50-(textRenderer.getWidth(module.getName())/2), y, 0xffffff);
    }
}
