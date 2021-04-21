package com.evaan.frostburn.clickgui;

import com.evaan.frostburn.clickgui.button.ModuleButton;
import com.evaan.frostburn.clickgui.button.SettingButton;
import com.evaan.frostburn.clickgui.button.buttons.BoolButton;
import com.evaan.frostburn.clickgui.button.buttons.ModeButton;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.SettingsManager;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;

/**
 * @author Gopro336
 */
public class Dropdown {

    private final double W;
    private final double H;
    public double X;
    public double Y;
    private final Module module;
    private final ModuleButton moduleButton;
    private static int modY = 0;

    private final ArrayList<SettingButton> buttons = new ArrayList<>();

    public Dropdown(ModuleButton mButton, Module module, double x, double y, double w, double h) {
        X = x;
        Y = y;
        W = w;
        H = h;

        this.moduleButton = mButton;
        this.module = module;
        int boost = 0;

        initGui(boost);
    }

    public void initGui(int boost) {

        //if (SettingsManager.getSettings(module) == null) return;

        for (Setting<?> setting : SettingsManager.getSettings(module)) {

            SettingButton settingButton;

            if (setting.getValue() instanceof Boolean) {

                //set the setting button
                settingButton = new BoolButton(moduleButton, module, setting, X, Y + (boost * H), W, H);
                buttons.add(settingButton);

            }
            if (setting.getValue() instanceof String) {

                //set the setting button
                settingButton = new ModeButton(moduleButton, module, setting, X, Y + (boost * H), W, H);
                buttons.add(settingButton);

            }
            /*if (setting.isNumber()) {

                //set the setting button
                settingButton = new SliderButton(module, setting, X, Y + (boost * H), W, H, mX, mY, false);
                buttons.add(settingButton);

            }*/

            //add bind button
        }
    }

    public void render(MatrixStack matrices, int mX, int mY) {
        modY = 0;
        int boost = 0;

        for (SettingButton button : buttons){
            ++boost;
            button.setX(X);
            button.setY(Y + (boost * H));
            button.render(matrices, mX, mY);
            button.update();
            Window.buttonCounter[0] = Window.buttonCounter[0] + 1;

            modY = boost;
        }

    }

    public void mouseDown(int mX, int mY, int mB) {
        buttons.forEach(settingButton -> settingButton.mouseDown(mX, mY, mB));
    }

    public void mouseUp(int mX, int mY) {
        buttons.forEach(settingButton -> settingButton.mouseUp(mX, mY));
    }

    public void keyPress(int key) {
        buttons.forEach(settingButton -> settingButton.keyPress(key));
    }

    public void close() {
        buttons.forEach(SettingButton::close);
    }

    public void setX(double x) {
        X = x;
    }

    public void setY(double y) {
        Y = y;
    }

    //Returns boost multiplied by the height. Used for adding to the main height.
    public double getBoost() {
        ///return (int)(moduleButton.getDropdownProgressPercentage() * (modY*H))/100;
        return modY*H;
    }

}