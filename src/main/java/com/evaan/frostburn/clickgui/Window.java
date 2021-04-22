package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.module.modules.render.ClickGuiMod;
import com.evaan.frostburn.util.Wrapper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

import java.awt.*;
import java.util.ArrayList;
//import java.util.List;

/**
 * @author Gopro336
 * windows still dont close properly
 */
public class Window implements Wrapper {

    public String title;
    public final Module.Category category;
    public static int[] buttonCounter = new int[]{1};
    private final ArrayList<ModuleButton> buttons = new ArrayList<>();
    public final int W;
    public final int H;
    public double X;
    public double Y;
    private double dragX;
    private double dragY;
    public boolean open = true;
    private boolean dragging;

    public Window(Module.Category category, int x, int y, int w, int h) {

        this.title = category.toString();
        this.category = category;
        X = x;
        Y = y;
        W = w;
        H = h;

        double yOffset = Y + H;

        //for each module from the category, initiate a module button and add it to buttons list
        for (Module module : ModuleManager.getModulesInCategory(category))
        {
            ModuleButton button = new ModuleButton(module, X, yOffset, W, H);
            buttons.add(button);
            yOffset += H;

        }
    }

    public void render(MatrixStack matrices, int mX, int mY)
    {
        //button counter not in use yet
        buttonCounter = new int[]{1};

        //dragging
        if (dragging) {
            X = dragX + mX;
            Y = dragY + mY;
        }

        //draw top bar
        FrostBurn.clickGUI.drawGradient(matrices, X, Y, X + W, Y + H, new Color(ClickGuiMod.clickGuiMod.bgR.getValue(), ClickGuiMod.clickGuiMod.bgG.getValue(), ClickGuiMod.clickGuiMod.bgB.getValue(), ClickGuiMod.clickGuiMod.bgA.getValue()).getRGB(), new Color(ClickGuiMod.clickGuiMod.bgR.getValue(), ClickGuiMod.clickGuiMod.bgG.getValue(), ClickGuiMod.clickGuiMod.bgB.getValue(), ClickGuiMod.clickGuiMod.bgA.getValue()).getRGB());//119

        //draw title string
        textRenderer.draw(matrices, new LiteralText(category.getName()), (float)X + 4, (float)Y + 4, new Color(30, 30, 30).getRGB());

        //return if it is closed
        if (!open) return;

        double modY = Y + H;

        for (ModuleButton moduleButton : buttons)
        {
            Window.buttonCounter[0] = buttonCounter[0] + 1;
            //draw moduleButton
            moduleButton.setX(X);
            moduleButton.setY(modY);
            moduleButton.render(matrices, mX, mY);

            //if moduleButton is closed continue to next iteration
            //if (!moduleButton.isOpen()) continue;

            if (moduleButton.isOpen()){
                //set "dropdown" to reference the dropdown defined inside of moduleButton class
                Dropdown dropdown = moduleButton.dropdown;

                dropdown.setX(X);
                dropdown.setY(modY);
                //dropdown.opening = true;

                dropdown.render(matrices, mX, mY);

                //boost is multiplied by height beforehand
                modY += dropdown.getBoost();

            }

            modY += H;
        }
    }

    public void mouseDown(double mX, double mY, int mB)
    {
        if (isHover(X, Y, W, H, mX, mY))
        {
            if (mB == 0) {
                dragging = true;
                dragX = X - mX;
                dragY = Y - mY;
            }
            else if (mB == 1) {
                if (open) {
                    for (ModuleButton button : buttons) {
                        if (button.isOpen())
                        {
                            button.processRightClick();
                        }
                    }
                }
                /*else if (!open) {
                    open = true;
                }*/
            }
        }

        if (open)
            for (ModuleButton button : buttons) {
                button.mouseDown((int)mX, (int)mY, mB);
                button.dropdown.mouseDown((int)mX, (int)mY, mB);
            }
    }

    public void close() {
        buttons.forEach(button -> button.dropdown.close());
    }

    public void mouseUp(double mX, double mY) {
        dragging = false;
        if (!open) return;
        buttons.forEach(button -> button.dropdown.mouseUp((int)mX, (int)mY));
    }

    public void keyPress(int key) {
        if (open)
            buttons.forEach(button -> button.dropdown.keyPress(key));
    }

    private boolean isHover(double X, double Y, double W, double H, double mX, double mY) {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public double getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public void setX(int x) {
        X = x;
    }

    /*public void setOpen(boolean Open) {
        open = Open;
    }*/
}