package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Panel {
    Module.Category category;
    int x;
    int y;

    public Panel(Module.Category category, int x, int y) {
        this.category = category;
        this.x = x;
        this.y = y;
    }

    ArrayList<ModuleButton> moduleButtons;
    TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;

    public void init() {
        moduleButtons = new ArrayList<>();
        int y1 = y+16;
        for (Module module : ModuleManager.getModulesInCategory(category)) {
            moduleButtons.add(new ModuleButton(module, x, y1));
            y1+=15;
        }
    }

    public void render(MatrixStack matrices) {
        DrawableHelper.fill(matrices, x, y, x+100, ModuleManager.getModulesInCategory(category).size()*15+35, 0xcc323232);
        textRenderer.draw(matrices, new LiteralText(category.name()), x+50-(textRenderer.getWidth(category.name())/2), y+2, 0xffffff);
        moduleButtons.forEach(moduleButton -> {moduleButton.render(matrices);});
    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (button != 0) return;
        moduleButtons.forEach(moduleButton -> {
            if (mouseX >= moduleButton.x && mouseX <= moduleButton.x + 100 && mouseY >= moduleButton.y && mouseY <= moduleButton.y + 8) moduleButton.module.toggle();
        });
    }
}
