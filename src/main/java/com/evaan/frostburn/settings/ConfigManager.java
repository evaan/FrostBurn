package com.evaan.frostburn.settings;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.Setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class ConfigManager {
	
	public static String configPath;
	public static Properties config = null;
	public static String currentConfigName;
	public static File configFile;
	
	private static void prepare() {
		Path configDir = FrostBurn.mc.runDirectory.toPath().normalize().resolve("frostburn");
		new File(configDir.toString()).mkdirs();
		configFile = new File(configDir.toString() + "/" + currentConfigName + ".xml");
		// CREATE DIR
		configPath = configFile.getAbsolutePath();
		try {
			configFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		config = new Properties();
		ConfigManager.reloadConfig();
	}
	
	public static void reloadConfig() {
		
		FileInputStream input = null;

		try {
			input = new FileInputStream(configPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			config.loadFromXML(input);
			input.close();
		} catch (InvalidPropertiesFormatException e) {
			// XML probably hasn't been written yet
			ConfigManager.save();
		} catch (IOException e) {
			// RIP
		}
	}
	
	public static void save() {
		FileOutputStream fileHandle = null;
		try {
			fileHandle = new FileOutputStream(configPath);
			
			// empty so we can write new config
			PrintWriter writer = new PrintWriter(configPath);
			writer.print("");
			writer.close();
			
			// store config
			config.storeToXML(fileHandle, null);
			fileHandle.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void setValue(String option, String value) {
		config.setProperty(option, String.valueOf(value));
		ConfigManager.save();
		
	}
	
	public static String getValue(String key, String backup) {
		return config.getProperty(key, backup);
	}
	
	public static void loadConfig(String configName) {
		System.out.println("Loading FrostBurn config named \"" + configName + "\"");
		
		currentConfigName = configName;
		ConfigManager.prepare();
        
        for(String moduleName : ModuleManager.getModuleNames()) {
        	
        	// Load modules enabled/disabled
        	Module module = ModuleManager.getModule(moduleName);
        	String key = moduleName + ".enabled";
        	String settingString = config.getProperty(key, "false");
        	Boolean enabled = settingString.equalsIgnoreCase("true") ? true : false;
        	module.setEnabled(enabled);
        	
        	// Load settings for the module
        	/*for(Setting setting : module.settings) {
        		String settingName = setting.getName();
        		String key = moduleName + "." + settingName;
        	}*/
        	
        	// We're done here
        	System.out.println("Loaded config for " + moduleName);
        }
        
        System.out.println("Finished loading FrostBurn config");
	}
	
}
	