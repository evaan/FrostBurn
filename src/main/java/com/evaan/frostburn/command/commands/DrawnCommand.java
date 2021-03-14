package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.ModuleManager;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class DrawnCommand extends Command {
    public DrawnCommand() {super(new String[]{"drawn", "d"});}

    @Override
    public void onCommand(String[] args) {
        if (args.length == 1) {
            sendMessage("Usage: drawn <Module>");
            return;
        }
        if (ModuleManager.getModule(args[1]) != null) {
            ModuleManager.getModule(args[1]).setDrawn(ModuleManager.getModule(args[1]).isDrawn());
        } else sendMessage("Module not found!");
    }
}
