package com.evaan.frostburn.module.modules.render;

import com.evaan.frostburn.imgui.NewGui;
import com.evaan.frostburn.module.Module;

/**
 * @Author evaan on 4/23/2021
 * https://github.com/evaan
 */
public class ImGuiMod extends Module {
    public ImGuiMod() {super("ImGui", Category.RENDER);}

    @Override
    public void onEnable() {
        mc.openScreen(new NewGui());
        disable();
    }
}
