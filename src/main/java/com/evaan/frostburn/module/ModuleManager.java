package com.evaan.frostburn.module;

import com.evaan.frostburn.module.modules.combat.*;
import com.evaan.frostburn.module.modules.misc.*;
import com.evaan.frostburn.module.modules.render.*;
import com.evaan.frostburn.module.modules.movement.*;

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

        modules.add(new ClickGuiMod());
        modules.add(new AirPlace());
        modules.add(new Surround());
        modules.add(new KillAura());
        modules.add(new AutoAnchor());
        modules.add(new BedAura());
        modules.add(new AutoTotem());
        modules.add(new Velocity());
        modules.add(new Sprint());
        modules.add(new FakePlayer());
        modules.add(new Criticals());
        modules.add(new DiscordRPC());
        modules.add(new AutoStaircase());
        modules.add(new Fullbright());
        modules.add(new Scaffold());
        modules.add(new Zoom());
        modules.add(new Jesus());
        modules.add(new CrystalAura());
        modules.add(new NoWeather());
        modules.add(new NoParticle());
        modules.add(new SafeWalk());
        modules.add(new NoFall());
        modules.add(new YawLock());
        modules.add(new Offhand());
        modules.add(new Nuker());
        modules.add(new HUD());
        modules.add(new CleanChat());
        modules.add(new MiddleClick());
        modules.add(new ImGuiMod());

        modules.sort(Comparator.comparing(object -> object.name)); //sort the modules alphabetically
    }

    public static Module getModule(String name) {
        Module m = null;
        for (Module module : modules) {
            if (name.equalsIgnoreCase(module.name)) m = module;
        }
        return m;
    }

    public static ArrayList<Module> getModulesInCategory(Module.Category category) {
        ArrayList<Module> cat = new ArrayList<>();
        for (Module module : modules) {
            if (module.category.equals(category)) cat.add(module);
        }
        return cat;
    }
    
    public static ArrayList<String> getModuleNames() {
    	ArrayList<String> names = new ArrayList<>();
    	for (Module module : modules) {
            names.add(module.getName());
        }
    	return names;
    }
}
