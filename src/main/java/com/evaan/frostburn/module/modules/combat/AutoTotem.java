package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class AutoTotem extends Module {
    public AutoTotem() {super("AutoTotem", Category.COMBAT);}

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.currentScreen instanceof InventoryScreen) return;
        for (int i = 9; i <= 36; i++) {
            if (mc.player.inventory.getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
            }
        }
    }
}
