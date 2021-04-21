package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class Offhand extends Module {
    public Offhand() {super("Offhand", Category.MISC);}

    Setting<Boolean> totemOnFall = register(
            new Setting(
                    "TotemOnFall",
                    this,
                    false
            )
    );
    Setting<Float> fallDistance = register(
            new Setting(
                    "FallDistance",
                    this,
                    15f,
                    0f,
                    256f
            )
    );
    Setting<Float> totemSwapHealth = register(
            new Setting(
                    "TotemSwapHealth",
                    this,
                    12f,
                    0f,
                    36f
            )
    );
    Setting<Boolean> offhandCrystal = register(
            new Setting(
                    "OffhandCrystal",
                    this,
                    false
            )
    );

    @Override
    public void onUpdate() {
        if (mc.player == null) return;

        if ((mc.player.getAbsorptionAmount() + mc.player.getHealth() <= totemSwapHealth.getValue()) ||
                (totemOnFall.getValue() && mc.player.fallDistance >= fallDistance.getValue())) {
            itemSwap(Items.TOTEM_OF_UNDYING);
        }  else if (offhandCrystal.getValue()) {
            itemSwap(Items.END_CRYSTAL);
        }
    }

    private void itemSwap(Item item) {
        if (mc.player == null) return;
        int i;
        Boolean found = false;
        if (!(mc.player.getOffHandStack().getItem().equals(item))) {
            for (i = 9; i <= 36; i++) {
                if (mc.player.inventory.getStack(i).getItem().equals(item)) {
                    found = true;
                    break;
                }
            }
            if (!(mc.player.getOffHandStack().getItem().equals(item)) && found) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
            }
        }
    }
}
