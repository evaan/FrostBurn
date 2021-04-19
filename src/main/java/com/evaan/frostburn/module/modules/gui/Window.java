package com.evaan.frostburn.module.modules.gui;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.Module;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Window {

    public String title;
    public final Module.Category category;
    public static int[] counter1 = new int[]{1};
    private int bottom;
    private int place;
    private int scroll;
    private final ArrayList<ModuleButton> buttons = new ArrayList<>();
    public final int W;
    public final int H;
    public int X;
    public int Y;
    private int dragX;
    private int dragY;
    public boolean open = true;
    private boolean dragging;
    public static List<Window> windows = new ArrayList<>();

    public Window(Module.Category category, int x, int y, int w, int h)
    {
        this.title = category.toString();

        this.category = category;
        X = x;
        Y = y;
        W = w;
        H = h;

        int yOffset = Y + H;

        //for each module from the category, initiate a module button and add it to buttons list
        for (Module module : FrostBurn..getModules(category))
        {
            ModuleButton button = new ModuleButton(module, X, yOffset, W, H);
            buttons.add(button);
            yOffset += H;

        }

    }

    public void render(int mX, int mY)
    {
        //button counter not in use yet
        counter1 = new int[]{1};

        //dragging
        if (dragging)
        {
            X = dragX + mX;
            Y = dragY + mY;
        }

        //draw top bar
        Gui.drawRect(X, Y, X + W, (Y + H)-1, (new Color(ClickGUI.red.getValue(), ClickGUI.green.getValue(), ClickGUI.blue.getValue(), ClickGUI.alpha.getValue())).getRGB());//119

        Zenith.clickGUI.drawGradient(X, (Y + H)-1, X + W, Y + H, new Color(20, 20, 20, ClickGUI.backalpha.getValue()).getRGB(), new Color(20, 20, 20, ClickGUI.backalpha.getValue()).getRGB());

        //draw title string
        FontUtil.drawString(category.getName(), X + 4, Y + 4);

        if (open)
        {
            int modY = Y + H;

            for (ModuleButton moduleButton : buttons)
            {
                Window.counter1[0] = counter1[0] + 1;
                //draw moduleButton
                moduleButton.setX(X);
                moduleButton.setY(modY);
                moduleButton.render(mX, mY);
                //modY += H;

                //if moduleButton is closed continue to next iteration
                //if (!moduleButton.isOpen()) continue;

                if (moduleButton.isOpen()){
                    //set "dropdown" to reference the dropdown defined inside of moduleButton class
                    Dropdown dropdown = moduleButton.dropdown;

                    dropdown.setX(X);
                    dropdown.setY(modY);
                    //dropdown.opening = true;

                    dropdown.render(GuiUtil.mX, GuiUtil.mY);

                    //boost is multiplied by height beforehand
                    modY += dropdown.getBoost();
					/*if (!moduleButton.isOpen())
					{
					moduleButton.processRightClick();
					}*/

                }
                modY += H;

            }


            if (ClickGUI.thin.getValue()) {
                RenderUtil.drawRectOutline(X, Y, X + W, modY, 0.5d, (new Color(ClickGUI.ored.getValue(), ClickGUI.ogreen.getValue(), ClickGUI.oblue.getValue(), ClickGUI.oalpha.getValue())).getRGB());
            }
            else {
                RenderUtil.drawRectOutline(X, Y, X + W, modY, 1d, (new Color(ClickGUI.ored.getValue(), ClickGUI.ogreen.getValue(), ClickGUI.oblue.getValue(), ClickGUI.oalpha.getValue())).getRGB());
            }

        }

    }

    public void mouseDown(double mX, double mY, int mB)
    {
        if (isHover(X, Y, W, H, mX, mY))
        {
            if (mB == 0)
            {
                dragging = true;
                dragX = X - mX;
                dragY = Y - mY;
            }
            else if (mB == 1)
            {
                if (open)
                {
                    for (ModuleButton button : buttons)
                    {
                        if (button.isOpen())
                        {
                            button.processRightClick();
                        }
                    }
                }
                else if (!open)
                {
                    open = true;
                }
            }
        }

        if (open)
        {
            for (ModuleButton button : buttons)
            {
                button.mouseDown(mX, mY, mB);
                button.dropdown.mouseDown(mX, mY, mB);
            }
        }
    }

    public void close()
    {
        for (ModuleButton button : buttons)
        {
            button.dropdown.close();
        }
    }

    public void mouseUp(double mX, double mY)
    {
        dragging = false;

        if (open)
        {
            for (ModuleButton button : buttons)
            {
                button.dropdown.mouseUp(mX, mY);
            }
        }
    }

    public void keyPress(int key)
    {
        if (open)
        {
            for (ModuleButton button : buttons)
            {
                button.dropdown.keyPress(key);
            }
        }
    }

    private boolean isHover(int X, int Y, int W, int H, int mX, int mY)
    {
        return mX >= X && mX <= X + W && mY >= Y && mY <= Y + H;
    }

    public int getY()
    {
        return Y;
    }

    public void setY(int y)
    {
        Y = y;
    }

    public void setX(int x)
    {
        X = x;
    }

    public void setOpen(boolean Open)
    {
        open = Open;
    }

}
