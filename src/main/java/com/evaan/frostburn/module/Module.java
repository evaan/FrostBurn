package com.evaan.frostburn.module;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.setting.Setting;
import com.evaan.frostburn.setting.SettingsManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Formatting;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    
    public String name;
    public Category category;
    public boolean enabled, drawn;
    public int bind;

    public Module(String name, Category category) {
        this.name = name;
        this.category = category;
        this.enabled = false;
        this.drawn = true;
        this.bind = 0;
    }

    public void onEnable() {}
    public void onDisable() {}
    public void onUpdate() {}
    public void onRender() {}
    public void toggle() {if (enabled) disable(); else enable();}
    public void enable() {enabled = true; Command.sendMessage(name + Formatting.GREEN + " enabled!");}
    public void disable() {enabled = false; Command.sendMessage(name + Formatting.RED + " disabled!");}

    public Setting register(Setting setting) { SettingsManager.register(setting); return setting; }

    public boolean isEnabled() {return enabled;}
    public boolean isDrawn() {return drawn;}

    public enum Category{COMBAT, MISC, RENDER}
}
