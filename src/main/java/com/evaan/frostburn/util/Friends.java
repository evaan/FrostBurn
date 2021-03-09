package com.evaan.frostburn.util;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Friends {
    public static ArrayList<String> friends = new ArrayList<>();

    public static boolean isFriend(String name) {return friends.contains(name);}
}
