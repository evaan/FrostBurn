package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.setting.Setting;
import com.evaan.frostburn.setting.SettingsManager;

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
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]) == null) {sendMessage("Setting not found!"); return;}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).type == Setting.Type.BOOLEAN) {SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).value = Boolean.parseBoolean(args[3]);}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).type == Setting.Type.INTEGER) {SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).value = Integer.parseInt(args[3]);}
        if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).type == Setting.Type.STRING) {
            if (SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).options.contains(args[3])) SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).value = args[3];
            else {sendMessage(args[3] + " not found in option!"); return;}
        }
        sendMessage("Set " + SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).name + " to " + SettingsManager.getSetting(ModuleManager.getModule(args[1]), args[2]).value);
    }
}
