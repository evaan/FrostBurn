package com.evaan.frostburn.module.modules.gui;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Keyboard;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class ClickGui extends Screen implements Wrapper {

    public static ArrayList<Window> windows = new ArrayList<>();


    protected ClickGui(Text title)
    {
        super(title);

        int xOffset = 3;

        for (Module.Category category : Module.Category.values())
        {
            //if (category == Module.Category.) continue;

            windows.add(new Window(category, xOffset, 3, 110, 15));
            xOffset += 120;
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float partialTicks)
    {
        //doScroll();

        for (Window window : windows)
        {
            window.render(mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton)
    {
        for (Window window : windows)
        {
            window.mouseDown(mouseX, mouseY, mouseButton);
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int state)
    {
        for (Window window : windows)
        {
            window.mouseUp(mouseX, mouseY);
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers)
    {
        for (Window window : windows)
        {
            window.keyPress(keyCode);
        }

        if (keyCode == Keyboard.KEY_ESCAPE)
        {
            mc.openScreen(null);

            if (mc.currentScreen == null)
            {
                //mc.setIngameFocus();
            }
        }
        return false;
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }

    public void drawGradient(MatrixStack matrices, double left, double top, double right, double bottom, int startColor, int endColor)
    {
        //drawGradientRect((int)left, (int)top, (int)right, (int)bottom, startColor, endColor);
        fillGradient(matrices, (int)left, (int)top, (int)right, (int)bottom, startColor, endColor);
    }

    @Override
    public void onClose()
    {
        for (Window window : windows)
        {
            window.close();
        }

        //Zenith.moduleManager.getModule("ClickGUI").disable();
    }

    /*private void doScroll()
    {
        int w = Mouse.getDWheel();
        if (w < 0)
        {
            for (Window window : windows)
            {
                window.setY(window.getY() - 8);
            }
        }
        else if (w > 0)
        {
            for (Window window : windows)
            {
                window.setY(window.getY() + 8);
            }
        }
    }*/

    public static ArrayList<Window> getWindows() {
        return windows;
    }


    public static Window getWindowByName(String in) {
        for (Window w : getWindows()) {
            if (w.title.equalsIgnoreCase(in)) {
                return w;
            }
        }
        return null;
    }
}
