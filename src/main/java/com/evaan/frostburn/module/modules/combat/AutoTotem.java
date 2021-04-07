package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

/**
 * @Author evaan, majorsopa
 * https://github.com/evaan
 * https://github.com/majorsopa
 */
public class AutoTotem extends Module {
    public AutoTotem() {super("AutoTotem", Category.COMBAT);}

    @Override
    public void onUpdate() {
        if (mc.player == null) return;
        int i;
        Boolean found = false;
        if (!mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) {
            for (i = 9; i <= 36; i++) {
                if (mc.player.inventory.getStack(i).getItem().equals(Items.TOTEM_OF_UNDYING)) {
                    found = true;
                    break;
                }
            }
            if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING)) && found) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
            }
        }
    }
}
