package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.module.Module;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class MiddleClickPearl extends Module {
    public MiddleClickPearl() {super("MiddleClickPearl", Category.MISC);}

    public boolean pressed = false; //fixes spam click

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) return;
        if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 1 && !pressed) {
            int oldSlot = mc.player.inventory.selectedSlot;
            pressed = true;
            boolean found = false;
            for (int i = 0; i < 9; i++) {
                if (mc.player.inventory.getStack(i).getItem().equals(Items.ENDER_PEARL)) {
                    mc.player.inventory.selectedSlot = i;
                    found = true;
                    break;
                }
            }
            if (mc.crosshairTarget.getType() != HitResult.Type.BLOCK && mc.crosshairTarget.getType() != HitResult.Type.ENTITY) mc.interactionManager.interactItem(mc.player, mc.world, Hand.MAIN_HAND);
            mc.player.inventory.selectedSlot = oldSlot;
        } else if (GLFW.glfwGetMouseButton(mc.getWindow().getHandle(), GLFW.GLFW_MOUSE_BUTTON_MIDDLE) == 0) pressed = false;
    }
}
