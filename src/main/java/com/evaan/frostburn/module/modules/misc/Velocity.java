package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.event.events.PacketEvent;
import com.evaan.frostburn.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.packet.s2c.play.EntityVelocityUpdateS2CPacket;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class Velocity extends Module {
    public Velocity() {super("Velocity", Category.MISC);}

    @EventHandler
    private Listener<PacketEvent.Receive> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof EntityVelocityUpdateS2CPacket && ((EntityVelocityUpdateS2CPacket) event.getPacket()).getId() == mc.player.getEntityId()) event.cancel();
        else if (event.getPacket() instanceof ExplosionS2CPacket) event.cancel();
    });
}
