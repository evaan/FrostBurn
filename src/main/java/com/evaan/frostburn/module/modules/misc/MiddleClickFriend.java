package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Friends;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class MiddleClickFriend extends Module {
    public boolean pressed = false; //fixes spam click

    public MiddleClickFriend() {
        super("MiddleClickFriend", Category.MISC);
    }

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 1 && !pressed) {
            pressed = true;
            Optional<Entity> lookingAt = DebugRenderer.getTargetedEntity(mc.player, 6); //6 is the vanilla reach right
            if (lookingAt.isPresent()) {
                if (Friends.getInstance().isFriend(lookingAt.get().getName().asString())) {
                    Friends.getInstance().removeFriend(lookingAt.get().getName().asString());
                    Command.sendMessage("Removed " + Formatting.RED + lookingAt.get().getName().asString() + Formatting.WHITE + " from your friends list");
                } else if (!Friends.getInstance().isFriend(lookingAt.get().getName().asString())) {
                    Friends.getInstance().addFriend(lookingAt.get().getName().asString());
                    Command.sendMessage("Added " + Formatting.GREEN + lookingAt.get().getName().asString() + Formatting.WHITE + " to your friends list");
                }
            }
        } else if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 0)
            pressed = false;
    }
}
