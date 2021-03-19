package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.DiscordUtil;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class DiscordRPC extends Module {
    public DiscordRPC() {super("DiscordRPC", Category.MISC);}

    @Override
    public void onEnable() {
        DiscordUtil.start();
    }

    @Override
    public void onDisable() {
        DiscordUtil.end();
    }
}
