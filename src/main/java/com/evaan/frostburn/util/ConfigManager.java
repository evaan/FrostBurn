package com.evaan.frostburn.util;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import net.minecraft.client.MinecraftClient;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
	public static File configFolder;
	public static File configFile;
	public static Properties config;

	public static void prepare(String name) {
		try {
			configFolder = new File(MinecraftClient.getInstance().runDirectory + File.separator + "FrostBurn");
			configFile = new File(configFolder + File.separator + name + ".xml");
			if (!configFolder.exists()) configFolder.mkdirs();
			if (!configFile.exists()) configFile.createNewFile();
			config = new Properties();
		} catch (Exception ignored) {}
	}

	public static void save(String name) {
		try {
			System.out.println("Saving config " + name + ".");
			prepare(name);
			for (Module module : ModuleManager.modules) {
				config.setProperty(module.getName() + ".enabled", String.valueOf(module.isEnabled()));
				config.setProperty(module.getName() + ".bind", String.valueOf(module.getBind()));
				for (Setting setting : SettingsManager.getSettings(module)) {
					config.setProperty(module.getName() + "." + setting.getName(), String.valueOf(setting.getValue()));
				}
			}
			config.storeToXML(new FileOutputStream(configFile), null);
		} catch (Exception ignored) {}
	}

	public static void load(String name) {
		try {
			System.out.println("Loading config " + name + ".");
			prepare(name);
			config.loadFromXML(new FileInputStream(configFile));
			for (Module module : ModuleManager.modules) {
				if (Boolean.parseBoolean(config.getProperty(module.getName()+".enabled")) != module.isEnabled()) module.setEnabled(Boolean.parseBoolean(config.getProperty(module.getName()+".enabled")));
				module.setBind(Integer.parseInt(config.getProperty(module.getName() + ".bind")));
				for (Setting setting : module.settings) {
					String value = config.getProperty(module.getName() + "." + setting.getName(), null);
					if (value != null) {
						switch (setting.getType()) {
							case FLOAT:
								if ((float)setting.getMin() >= Float.parseFloat(value) || (float)setting.getMax() <= Float.parseFloat(value)) setting.setValue(Float.parseFloat(value));
								break;
							case INTEGER:
								if ((int)setting.getMin() >= Integer.parseInt(value) || (int)setting.getMax() <= Integer.parseInt(value)) setting.setValue(Integer.parseInt(value));
								break;
							case BOOLEAN:
								setting.setValue(Boolean.parseBoolean(value));
								break;
							case STRING:
								if (setting.getOptions().contains(value) || setting.getOptions().contains(value)) setting.setValue(value);
								break;
						}
					}
				}
			}
		} catch (Exception e) {e.printStackTrace();}
	}
}
	