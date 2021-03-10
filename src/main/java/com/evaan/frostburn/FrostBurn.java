package com.evaan.frostburn;

import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.setting.SettingsManager;
import com.google.common.eventbus.Subscribe;
import net.fabricmc.api.ModInitializer;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	@Override
	public void onInitialize() {
		SettingsManager.init();
		ModuleManager.init();
		CommandManager.init();
	}
}
