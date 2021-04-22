package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

public class ClickGuiMod extends Module {
    public ClickGuiMod() {super("ClickGui", Category.RENDER);}

    public static ClickGuiMod clickGuiMod;

    public Setting<Integer> bgR = register(new Setting("BackgroundRed", this, 218, 0, 255));
    public Setting<Integer> bgG = register(new Setting("BackgroundGreen", this, 218, 0, 255));
    public Setting<Integer> bgB = register(new Setting("BackgroundBlue", this, 218, 0, 255));
    public Setting<Integer> bgA = register(new Setting("BackgroundAlpha", this, 232, 0, 255));
    public Setting<Integer> textR = register(new Setting("TextRed", this, 30, 0, 255));
    public Setting<Integer> textG = register(new Setting("TextGreen", this, 30, 0, 255));
    public Setting<Integer> textB = register(new Setting("TextBlue", this, 30, 0, 255));
    public Setting<Integer> textA = register(new Setting("TextAlpha", this, 255, 0, 255));
    public Setting<Integer> textAltR = register(new Setting("TextAltRed", this, 30, 0, 255));
    public Setting<Integer> textAltG = register(new Setting("TextAltGreen", this, 30, 0, 255));
    public Setting<Integer> textAltB = register(new Setting("TextAltBlue", this, 216, 0, 255));
    public Setting<Integer> textAltA = register(new Setting("TextAltAlpha", this, 232, 0, 255));


    @Override
    public void onEnable() {if (mc.player == null) {disable(); return;} clickGuiMod = this; mc.openScreen(FrostBurn.clickGUI);}

    @Override
    public void onDisable() {if (mc.player != null) mc.openScreen(null);}
}
