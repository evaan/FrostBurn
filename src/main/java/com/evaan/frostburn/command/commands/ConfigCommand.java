package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.util.ConfigManager;

public class ConfigCommand extends Command {

    public ConfigCommand() {
    	super(new String[]{"config", "c"});
    }

    @Override
    public void onCommand(String[] args) {
    	if (args.length == 1) {
            sendMessage("Usage: config <save/load> <Config Name>");
            return;
        }

    	if (args[1].equalsIgnoreCase("save")) {
    	    ConfigManager.save(args[2]);
        } else if (args[1].equalsIgnoreCase("load")) {
            ConfigManager.load(args[2]);
        } else sendMessage("Usage: <save/load> <Config Name>");
    }
}
