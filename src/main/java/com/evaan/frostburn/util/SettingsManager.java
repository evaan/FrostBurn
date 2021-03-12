package com.evaan.frostburn.util;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class SettingsManager {
    public static ArrayList<Setting> settings;

    public static void init() {
        settings = new ArrayList<>();
    }

    public static void register(Setting setting) {
        settings.add(setting);
    }

    public static Setting getSetting(Module parent, String name) {
        for (Setting setting : settings) {
            if (parent.equals(setting.parent) && name.equalsIgnoreCase(setting.name)) return setting;
        }
        return null;
    }

    public ArrayList<Setting> getSettings(Module parent) {
        ArrayList<Setting> sets = new ArrayList<>();
        for (Setting setting : settings) {
            if (setting.parent.equals(parent)) sets.add(setting);
        }
        return sets;
    }
}
