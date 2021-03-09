package com.evaan.frostburn.command.commands;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.util.Friends;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FriendCommand extends Command {
    public FriendCommand() {super(new String[]{"friend"});}

    @Override
    public void onCommand(String[] args) {
        if (args.length != 3) {
            sendMessage("Usage: friend <add/del> <Player>");
            return;
        }
        if (!args[1].equalsIgnoreCase("add") && !args[1].equalsIgnoreCase("del")) {
            sendMessage("Usage: friend <add/del> <Player>");
            return;
        }
        if (args[1].equalsIgnoreCase("add")) {
            if (Friends.isFriend(args[2])) {
                sendMessage(args[2] + " is already your friend!");
                return;
            } else {
                Friends.friends.add(args[2]);
                sendMessage("Added " + args[2] + " to your friends list!");
            }
        }
        if (args[1].equalsIgnoreCase("del")) {
            if (!Friends.isFriend(args[2])) {
                sendMessage(args[2] + " is already not your friend!");
                return;
            } else {
                Friends.friends.add(args[2]);
                sendMessage("Removed " + args[2] + " from your friends list!");
            }
        }
    }
}
