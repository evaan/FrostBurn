package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.Module;

public class NewGui extends Module {
    public NewGui() {super("NewGui", Category.RENDER);}

    @Override
    public void onEnable() {mc.openScreen(FrostBurn.clickGUI);}

    @Override
    public void onDisable() {mc.openScreen(null);}
}
