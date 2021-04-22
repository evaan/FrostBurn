package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.ConfigManager;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.SettingsManager;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class SettingCommand extends Command {
    public SettingCommand() {super(new String[]{"setting", "set"});}

	@Override
    public void onCommand(String[] args) {
        if (args.length != 4) {
        	sendMessage("Usage: setting <Module> <Setting> <Value>"); 
        	return;
        }
        
        // Try to find module
        Module module = ModuleManager.getModule(args[1]);
        if (module == null) {
        	sendMessage("Module not found!"); return;
        }
        
        // Try to find the setting on the module
		Setting setting = SettingsManager.getSetting(module, args[2]);
        if (setting == null) {
        	sendMessage("Setting not found!");
        	return;
        } else {
            switch (setting.getType()) {
                case BOOLEAN:
                    setting.setValue(Boolean.parseBoolean(args[3]));
                    Command.sendMessage("Set " + setting.getName() + " to " + args[3]);
                    break;
                case FLOAT:
                    if ((float)setting.getMin() > Float.parseFloat(args[3]) || (float)setting.getMax() < Float.parseFloat(args[3])) {
                        Command.sendMessage("Min: " + setting.getMin() + ", Max: " + setting.getMax());
                        break;
                    }
                    setting.setValue(Float.valueOf(args[3]));
                    Command.sendMessage("Set " + setting.getName() + " to " + args[3]);
                    break;
                case INTEGER:
                    if ((int)setting.getMin() > Integer.parseInt(args[3]) || (int)setting.getMax() < Integer.parseInt(args[3])) {
                        Command.sendMessage("Min: " + setting.getMin() + ", Max: " + setting.getMax());
                        break;
                    }
                    setting.setValue(Integer.parseInt(args[3]));
                    Command.sendMessage("Set " + setting.getName() + " to " + args[3]);
                    break;
                case STRING:
                    System.out.println("STRING " + args[3]);
                    // If it is string type, then we will check if it is a valid option
                    // If the options list is empty, we also allow the setting of the value
                    if (setting.getOptions().contains(args[3]) || setting.getOptions().isEmpty()) {
                        setting.setValue(args[3]);
                        Command.sendMessage("Set " + setting.getName() + " to " + args[3]);
                    } else {
                        sendMessage(args[3] + " not found in options!");
                        return;
                    }
                    break;
                default:
                    System.out.println("WARNING: Unknown or unsupported type found! Type: " + setting.getType());
            }
        }
    }
}
