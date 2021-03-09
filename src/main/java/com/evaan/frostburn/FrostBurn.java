package com.evaan.frostburn;

import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.module.ModuleManager;
import net.fabricmc.api.ModInitializer;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	@Override
	public void onInitialize() {
		ModuleManager.init();
		CommandManager.init();
	}
}
