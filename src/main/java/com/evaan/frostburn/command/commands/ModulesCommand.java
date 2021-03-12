package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.util.Formatting;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ModulesCommand extends Command {
    public ModulesCommand() {super(new String[]{"modules", "mods"});}

    @Override
    public void onCommand(String[] args) {
        String message = "Modules (" + ModuleManager.modules.size() + "): ";
        for (Module module : ModuleManager.modules) {
            if (module.isEnabled()) message += Formatting.GREEN + module.getName() + Formatting.WHITE + ", ";
            else message += Formatting.RED + module.getName() + Formatting.WHITE + ", ";
        }
        sendMessage(message.substring(0, message.length()-2));
    }
}
