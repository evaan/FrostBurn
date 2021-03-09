package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.command.CommandManager;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class HelpCommand extends Command {
    public HelpCommand() {super(new String[]{"help"});}

    @Override
    public void onCommand(String[] args) {
        String tmp = "Commands (" + CommandManager.commands.size() + "): ";
        sendMessage("FrostBurn 1.0 by evaan");
        sendMessage("https://github.com/evaan/frostburn");
        for (Command command : CommandManager.commands) {
            tmp += StringUtils.capitalize(command.name[0]) +", ";
        }
        sendMessage(tmp.substring(0, tmp.length()-2));
    }
}
