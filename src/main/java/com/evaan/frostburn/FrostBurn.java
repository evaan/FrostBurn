package com.evaan.frostburn;

import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.SettingsManager;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("███████╗██████╗░░█████╗░░██████╗████████╗██████╗░██╗░░░██╗██████╗░███╗░░██╗");
		System.out.println("██╔════╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗████╗░██║");
		System.out.println("█████╗░░██████╔╝██║░░██║╚█████╗░░░░██║░░░██████╦╝██║░░░██║██████╔╝██╔██╗██║");
		System.out.println("██╔══╝░░██╔══██╗██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██╔══██╗██║╚████║");
		System.out.println("██║░░░░░██║░░██║╚█████╔╝██████╔╝░░░██║░░░██████╦╝╚██████╔╝██║░░██║██║░╚███║");
		System.out.println("╚═╝░░░░░╚═╝░░╚═╝░╚════╝░╚═════╝░░░░╚═╝░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝");
		System.out.println("FrostBurn has been initialized!");
		System.out.println("https://github.com/evaan/frostburn");
		SettingsManager.init();
		ModuleManager.init();
		CommandManager.init();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
}
