package com.evaan.frostburn.setting;

import com.evaan.frostburn.module.Module;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Setting<T> {
    public String name;
    public Module parent;
    public T value, min, max;
    public ArrayList<T> options;
    public Type type;

    public Setting(String name, Module parent, T value) {
        this.name = name;
        this.parent = parent;
        this.value = value;
        this.type = Type.BOOLEAN;
    }

    public Setting(String name, Module parent, T value, T min, T max) {
        this.name = name;
        this.parent = parent;
        this.value = value;
        this.min = min;
        this.max = max;
        this.type = Type.INTEGER;
    }

    public Setting(String name, Module parent, ArrayList<T> options, T value) {
        this.name = name;
        this.parent = parent;
        this.value = value;
        this.options = options;
        this.type = Type.STRING;
    }

    public enum Type {BOOLEAN, INTEGER, STRING}
}
