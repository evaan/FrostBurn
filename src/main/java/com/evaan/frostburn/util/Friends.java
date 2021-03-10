package com.evaan.frostburn.util;

import java.util.ArrayList;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Friends {
    private static Friends instance = null;

    private ArrayList<String> friends;

    private Friends() {
        friends = new ArrayList<>();
    }

    public static Friends getInstance() {
        if(instance == null)
            instance = new Friends();
        return instance;
    }

    public void addFriend(String name) {
        friends.add(name);
    }

    public void removeFriend(String name) {
        friends.remove(name);
    }

    public boolean isFriend(String name) {
        return friends.contains(name);
    }
}
