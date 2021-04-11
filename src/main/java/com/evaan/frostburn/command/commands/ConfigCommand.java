package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.settings.ConfigManager;

public class ConfigCommand extends Command {

    public ConfigCommand() {
    	super(new String[]{"config", "c"});
    }

    @Override
    public void onCommand(String[] args) {
    	if (args.length == 1) {
            sendMessage("Usage: config <Config Name>");
            return;
        }
    	
    	ConfigManager.loadConfig(args[1]);
    	sendMessage("Loaded config named " + args[1]);
    }
}
