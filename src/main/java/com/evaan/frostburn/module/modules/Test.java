package com.evaan.frostburn.module.modules;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.setting.Setting;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Test extends Module {
    public Test(){super("Test", Category.MISC);}

    Setting<Boolean> gaming = register(new Setting<>("Test", this, false));

    @Override
    public void onUpdate() {
        Command.sendMessage("this is a test");
    }

    @Override
    public void onRender() {
        System.out.println(gaming.value);
    }
}
