package com.evaan.frostburn.command;

import com.evaan.frostburn.command.commands.BindCommand;
import com.evaan.frostburn.command.commands.HelpCommand;
import com.evaan.frostburn.command.commands.ModulesCommand;
import com.evaan.frostburn.command.commands.ToggleCommand;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class CommandManager {
    public static ArrayList<Command> commands;

    public static void init() {
        commands = new ArrayList<>();

        commands.add(new BindCommand());
        commands.add(new HelpCommand());
        commands.add(new ToggleCommand());
        commands.add(new ModulesCommand());

        commands.sort(Comparator.comparing(object -> object.name[0]));
    }
}
