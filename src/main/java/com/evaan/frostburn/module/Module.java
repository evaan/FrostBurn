package com.evaan.frostburn.module;

import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.util.Setting;
import com.evaan.frostburn.util.SettingsManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;

import java.awt.*;
import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Module {
    protected final MinecraftClient mc = MinecraftClient.getInstance();
    public ArrayList<Setting> settings;
    
    String name;
    Category category;
    boolean enabled, drawn;
    int bind;

    public Module(String name, Category category) {
    	this.settings = new ArrayList<>();
        this.name = name;
        this.category = category;
        this.enabled = false;
        this.drawn = true;
        this.bind = 0;
    }

    public String getName() {
    	return name;
    }
    
    public Category getCategory() {
    	return category;
    }
    
    public boolean isEnabled() {
    	return enabled;
    }
    
    public boolean isDrawn() {
    	return drawn;
    }
    
    public int getBind() {
    	return bind;
    }

    public void setEnabled(boolean enabled) {
    	if (enabled) enable(); 
    	else disable();
    }
    
    public void setDrawn(boolean drawn) {
    	this.drawn = drawn;
    }
    
    public void setBind(int bind) {
    	this.bind = bind;
    }

    public void onEnable() {}
    
    public void onDisable() {}
    
    public void onUpdate() {}
    
    public void onRender(MatrixStack matrices) {}

    public void onRender1(MatrixStack matrices) {}

    public void toggle() {
    	if (enabled) disable(); 
    	else enable();
    }

    public String getHudInfo() {return "";}
    
    public void enable() {
    	enabled = true; 
    	if(FrostBurn.mc != null) Command.sendMessage(name + Formatting.GREEN + " enabled!"); 
    	FrostBurn.EVENT_BUS.subscribe(this); 
    	onEnable();
    }
    
    public void disable() {
    	enabled = false; 
    	if(FrostBurn.mc != null) Command.sendMessage(name + Formatting.RED + " disabled!"); 
    	FrostBurn.EVENT_BUS.unsubscribe(this); 
    	onDisable();
    }

    public Setting register(Setting setting) { 
    	SettingsManager.register(setting);
    	this.settings.add(setting);
    	return setting; 
    }

    public enum Category {
        COMBAT("Combat"),
        MISC("Misc"),
        RENDER("Render"),
        MOVEMENT("Movement");

        String name;

        Category(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
}
