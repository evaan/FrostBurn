package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.Keyboard;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.util.ArrayList;

/**
 * @author Gopro336
 * some code ported from Zenith client (my client)
 * fixed scroll -evaan
 */

public class ClickGui extends Screen implements Wrapper {

    public static ArrayList<Window> windows;

    public ClickGui() {
        super(new LiteralText("ClickGui"));
        windows = new ArrayList<>();
        int xOffset = 3;
        for (Module.Category category : Module.Category.values()) {
            windows.add(new Window(category, xOffset, 3, 100, 20));
            xOffset += 120;
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks) {
        windows.forEach(window -> window.render(matrices, mouseX, mouseY));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        windows.forEach(window -> window.mouseDown(mouseX, mouseY, mouseButton));
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state) {
        windows.forEach(window -> window.mouseUp(mouseX, mouseY));
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        windows.forEach(window -> window.keyPress(keyCode));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    public void drawGradient(MatrixStack matrices, double left, double top, double right, double bottom, int startColor, int endColor) {
        fillGradient(matrices, (int)left, (int)top, (int)right, (int)bottom, startColor, endColor);
    }

    @Override
    public void onClose() {
        windows.forEach(Window::close);
        ModuleManager.getModule("ClickGui").disable();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        if (amount < 0)
        {
            for (Window window : windows)
            {
                window.setY((int)(window.getY() - 8));
            }
        }
        else if (amount > 0)
        {
            for (Window window : windows)
            {
                window.setY((int)(window.getY() + 8));
            }
        }
        return super.mouseScrolled(mouseX, mouseY, amount);
    }
}
