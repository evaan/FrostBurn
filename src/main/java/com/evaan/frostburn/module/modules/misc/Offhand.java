package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.Setting;
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
    Setting<Integer> fallDistance = register(
            new Setting(
                    "FallDistance",
                    this,
                    15
            )
    );
    Setting<Float> totemSwapHealth = register(
            new Setting(
                    "TotemSwapHealth",
                    this,
                    12,
                    0,
                    36
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

        if (mc.player.getAbsorptionAmount() + mc.player.getHealth() <= totemSwapHealth.getValue()) {
            totemSwap();
        } else if (totemOnFall.getValue() && mc.player.fallDistance >= fallDistance.getValue()) {
            totemSwap();
        } else if (offhandCrystal.getValue()) {
            crystalSwap();
        }
    }

    private void crystalSwap() {
        if (mc.player == null) return;
        int i;
        Boolean found = false;
        if (!(mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL))) {
            for (i = 9; i <= 36; i++) {
                if (mc.player.inventory.getStack(i).getItem().equals(Items.END_CRYSTAL)) {
                    found = true;
                    break;
                }
            }
            if (!(mc.player.getOffHandStack().getItem().equals(Items.END_CRYSTAL)) && found) {
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, i, 0, SlotActionType.PICKUP, mc.player);
                mc.interactionManager.clickSlot(mc.player.currentScreenHandler.syncId, 45, 0, SlotActionType.PICKUP, mc.player);
            }
        }
    }

    private void totemSwap() {
        if (mc.player == null) return;
        int i;
        Boolean found = false;
        if (!(mc.player.getOffHandStack().getItem().equals(Items.TOTEM_OF_UNDYING))) {
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
