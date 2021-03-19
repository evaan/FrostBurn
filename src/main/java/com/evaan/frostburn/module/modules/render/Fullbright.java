package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.module.Module;

public class Fullbright extends Module {
    public Fullbright() {super("Fullbright", Category.RENDER);}

    double oldGamma;

    @Override
    public void onEnable() {
        oldGamma = mc.options.gamma;
        mc.options.gamma = 100;
    }

    @Override
    public void onDisable() {
        mc.options.gamma = oldGamma;
    }
}
