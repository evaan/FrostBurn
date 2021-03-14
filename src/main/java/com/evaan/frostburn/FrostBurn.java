package com.evaan.frostburn;

import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.DiscordUtil;
import com.evaan.frostburn.util.SettingsManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.fabricmc.api.ModInitializer;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	public static EventBus EVENT_BUS;

	@Override
	public void onInitialize() {
		EVENT_BUS = new EventManager();
		SettingsManager.init();
		ModuleManager.init();
		CommandManager.init();
		System.out.println("███████╗██████╗░░█████╗░░██████╗████████╗██████╗░██╗░░░██╗██████╗░███╗░░██╗");
		System.out.println("██╔════╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗████╗░██║");
		System.out.println("█████╗░░██████╔╝██║░░██║╚█████╗░░░░██║░░░██████╦╝██║░░░██║██████╔╝██╔██╗██║");
		System.out.println("██╔══╝░░██╔══██╗██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██╔══██╗██║╚████║");
		System.out.println("██║░░░░░██║░░██║╚█████╔╝██████╔╝░░░██║░░░██████╦╝╚██████╔╝██║░░██║██║░╚███║");
		System.out.println("╚═╝░░░░░╚═╝░░╚═╝░╚════╝░╚═════╝░░░░╚═╝░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝");
		System.out.println("FrostBurn has been initialized!");
		System.out.println("https://github.com/evaan/frostburn");
	}
}
