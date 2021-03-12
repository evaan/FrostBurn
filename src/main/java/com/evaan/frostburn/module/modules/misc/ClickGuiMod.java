package com.evaan.frostburn.module.modules.misc;

 import com.evaan.frostburn.module.Module;
import net.minecraft.client.util.InputUtil;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ClickGuiMod extends Module {
    public ClickGuiMod() {super("ClickGui", Category.MISC); setBind(InputUtil.fromTranslationKey("key.keyboard.right.shift").getCode());}

    @Override
    public void onEnable() {
        //mc.openScreen(new ClickGui());
        disable();
    }
}
