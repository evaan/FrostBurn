package com.evaan.frostburn.module;

import com.evaan.frostburn.module.modules.Test;
import com.evaan.frostburn.module.modules.misc.*;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class ModuleManager {
    public static ArrayList<Module> modules;

    public static void init() {
        modules = new ArrayList<>();

        modules.add(new Test());
        modules.add(new MiddleClickPearl());
        modules.add(new MiddleClickFriend());

        modules.sort(Comparator.comparing(object -> object.name)); //sort the modules alphabetically
    }

    public static Module getModule(String name) {
        Module m = null;
        for (Module module : modules) {
            if (name.equalsIgnoreCase(module.name)) m = module;
        }
        return m;
    }
}
