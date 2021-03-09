package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.ModuleManager;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class BindCommand extends Command {
    public BindCommand() {super(new String[]{"bind", "b"});}

    @Override
    public void onCommand(String[] args) {
        if (args.length != 3) {
            sendMessage("Usage: bind <Module> <Bind>");
            return;
        }
        if (ModuleManager.getModule(args[1]) == null) {
            sendMessage("Module not found!");
            return;
        }
    }
}
