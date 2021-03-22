package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
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
        
        Module module = ModuleManager.getModule(args[1]);
        if (module == null) {
        	sendMessage("Module not found!"); return;
        }
        
		Setting setting = SettingsManager.getSetting(module, args[2]);
        if (setting == null) {
        	sendMessage("Setting not found!");
        	return;
        } else if (setting.getType() != Setting.Type.STRING) {
        	setting.setValue(args[3]);
        } else if (setting.getType() == Setting.Type.STRING) {
            if (setting.getOptions().contains(args[3]) || setting.getOptions().isEmpty()) { 
            	setting.setValue(args[3]);
            } else {
            	sendMessage(args[3] + " not found in option!"); 
            	return;
            }
        }
        
        sendMessage("Set " + setting.getName() + " to " + setting.getValue());
    }
}
