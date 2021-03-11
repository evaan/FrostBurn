package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.setting.Setting;
import com.google.common.collect.Streams;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class KillAura extends Module {
    public KillAura() {super("KillAura", Category.COMBAT);}

    Setting<Float> range = register(new Setting("Range", this, 4.0f, 0.1f, 6.0f));
    Setting<Boolean> switchItem = register(new Setting("Switch", this, true));
    //todo rotate

    @Override
    public void onUpdate() {
        if (mc.player == null || mc.world == null) return;
        if(mc.player.getAttackCooldownProgress(0) < 1) return;
        List<Entity> players = Streams.stream(mc.world.getEntities()).filter(e -> e instanceof PlayerEntity && mc.player.distanceTo(e) <= range.value && e != mc.player).collect(Collectors.toList());
        if (!players.isEmpty()) {
            PlayerEntity player = (PlayerEntity) players.get(0);
            System.out.println(player.getName());
            int oldSlot = mc.player.inventory.selectedSlot;
            if (switchItem.value) {
                for (int i = 0; i < 9; i++) {
                    if (mc.player.inventory.getStack(i).getItem() instanceof SwordItem)
                        mc.player.inventory.selectedSlot = i;
                }
            }
            mc.interactionManager.attackEntity(mc.player, player);
            mc.player.swingHand(Hand.MAIN_HAND);
        }
    }
}
