package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.ModuleManager;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ToggleCommand extends Command {
    public ToggleCommand() {super(new String[]{"toggle", "t"});}

    @Override
    public void onCommand(String[] args) {
        if (args.length == 1) {
            sendMessage("Usage: toggle <Module>");
            return;
        }
        if (ModuleManager.getModule(args[1]) != null) {
            ModuleManager.getModule(args[1]).toggle();
        } else sendMessage("Module not found!");
    }
}
