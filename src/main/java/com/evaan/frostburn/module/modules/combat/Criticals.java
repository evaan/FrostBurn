package com.evaan.frostburn.module.modules.combat;

import com.evaan.frostburn.event.events.PacketEvent;
import com.evaan.frostburn.module.Module;
import com.evaan.frostburn.util.packet.PlayerInteractEntityC2SUtils;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.packet.c2s.play.PlayerInteractEntityC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Criticals extends Module {
    public Criticals() {super("Criticals", Category.COMBAT);}

    @EventHandler
    private final Listener<PacketEvent.Send> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof PlayerInteractEntityC2SPacket) {
            if(PlayerInteractEntityC2SUtils.getInteractType((PlayerInteractEntityC2SPacket) event.getPacket()) == PlayerInteractEntityC2SUtils.InteractType.ATTACK && mc.player.isOnGround()) {
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY() + 0.1f, mc.player.getZ(), true));
                mc.getNetworkHandler().sendPacket(new PlayerMoveC2SPacket.PositionAndOnGround(mc.player.getX(), mc.player.getY(), mc.player.getZ(), false));
            }
        }
    });
}
