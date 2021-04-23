package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Friends;
import com.evaan.frostburn.util.Setting;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.Items;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @Author evaan on 4/22/2021
 * https://github.com/evaan
 */
public class MiddleClick extends Module {
    public MiddleClick() {
        super("MiddleClick", Category.MISC);
        modes.add("Friend");
        modes.add("Pearl");
    }

    public ArrayList<String> modes = new ArrayList<>();
    Setting<String> mode = register(new Setting("Mode", this, modes, "Friend"));

    boolean pressed = false;

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.player.world == null) return;
        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 1 && !pressed) {
            pressed = true;
            if (mode.getValue().equalsIgnoreCase("friend")) {
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
            } else if (mode.getValue().equalsIgnoreCase("pearl")) {
                int oldSlot = mc.player.inventory.selectedSlot;
                for (int i = 0; i < 9; i++) {
                    if (mc.player.inventory.getStack(i).getItem().equals(Items.ENDER_PEARL)) {
                        mc.player.inventory.selectedSlot = i;
                        break;
                    }
                }
                if (mc.crosshairTarget.getType() != HitResult.Type.BLOCK && mc.crosshairTarget.getType() != HitResult.Type.ENTITY) mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
                mc.player.inventory.selectedSlot = oldSlot;
            }
        } else if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 0)
            pressed = false;
    }
}
