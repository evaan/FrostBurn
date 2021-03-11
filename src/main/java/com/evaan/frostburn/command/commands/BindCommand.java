package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.util.InputUtil;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class BindCommand extends Command {
    public BindCommand() {super(new String[]{"bind", "b"});}

    @Override
    public void onCommand(String[] args) {
        if (args.length != 3) {
            sendMessage("Usage: bind <Module> <Key>");
            return;
        }
        Module m = ModuleManager.getModule(args[1]);
        if (m == null) {
            sendMessage("Module not found.");
            return;
        }
        m.bind = InputUtil.fromTranslationKey("key.keyboard." + args[2].toLowerCase().replaceAll("right", "right.").replaceAll("r", "right.")).getCode();
        sendMessage(m.name + " bound to " + args[2].toUpperCase());
    }
}
