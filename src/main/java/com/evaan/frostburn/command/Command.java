package com.evaan.frostburn.command;

import com.evaan.frostburn.FrostBurn;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Command {
    public static String prefix = ",";

    public String[] name;
    public Command(String[] name) {this.name = name;}
    public void onCommand(String[] args) {}

    public static void sendMessage(String message) {
    	try {
    		FrostBurn.mc.player.sendMessage(new LiteralText(Formatting.BLUE + "[FrostBurn] " + Formatting.WHITE + message), false);
    	} catch (Exception e) {}
    }
}
