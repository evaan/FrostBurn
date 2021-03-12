package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
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
        if (args.length != 4) {sendMessage("Usage: setting <Module> <Setting> <Value>"); return;}
        if (ModuleManager.getModule(args[1]) == null) {sendMessage("Module not found!"); return;}
        //todo fix this because its shit
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]) == null) {sendMessage("Setting not found!"); return;}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getType() == Setting.Type.BOOLEAN) {SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).setValue(args[3]);}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getType() == Setting.Type.INTEGER) {SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).setValue(args[3]);}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getType() == Setting.Type.STRING) {
            if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getOptions().contains(args[3])) SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).setValue(args[3]);
            else {sendMessage(args[3] + " not found in option!"); return;}
        }
        sendMessage("Set " + SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getName() + " to " + SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).getValue());
    }
}
