package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.ConfigManager;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ToggleCommand extends Command {
    public ToggleCommand() {
    	super(new String[]{"toggle", "t"});
    }

    @Override
    public void onCommand(String[] args) {
        if (args.length == 1) {
            sendMessage("Usage: toggle <Module>");
            return;
        }
        
        Module module = ModuleManager.getModule(args[1]);
        if(module != null) {
        	module.toggle();
        } else sendMessage("Module not found!");
    }
}
