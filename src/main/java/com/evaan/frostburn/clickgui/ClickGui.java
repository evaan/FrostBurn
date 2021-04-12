package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

/**
 * yoinked exetergui from xulu cry about it
 */
public final class ClickGui extends Screen
{
    public ClickGui() {super(new LiteralText("FrostBurn ClickGui"));}

    ArrayList<Panel> panels;

    @Override
    protected void init() {
        panels = new ArrayList<>();
        int x = 20;
        for (Module.Category category : Module.Category.values()) {
            panels.add(new Panel(category, x, 20));
            x+=120;
        }
        panels.forEach(Panel::init);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        panels.forEach(panel -> {panel.render(matrices);});
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        panels.forEach(panel -> {panel.mouseClicked((int)mouseX, (int)mouseY, button);});
        return false;
    }
}