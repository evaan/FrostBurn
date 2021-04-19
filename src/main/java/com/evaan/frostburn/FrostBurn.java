package com.evaan.frostburn;

import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.ConfigManager;
import com.evaan.frostburn.util.SettingsManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	public static MinecraftClient mc = MinecraftClient.getInstance();
	public static EventBus EVENT_BUS;
	public static String clientVersionString = "FrostBurn 1.0";

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

		//todo load default config

		ClientLifecycleEvents.CLIENT_STOPPING.register((minecraftClient) -> {
			//todo save default config
		});
	}
}
