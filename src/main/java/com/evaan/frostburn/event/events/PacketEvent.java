package com.evaan.frostburn.event.events;

import com.evaan.frostburn.event.FrostBurnEvent;
import net.minecraft.network.Packet;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class PacketEvent extends FrostBurnEvent {
    private final Packet packet;

    public PacketEvent(Packet packet) {this.packet = packet;}
    public Packet getPacket() {return packet;}
    public static class Receive extends PacketEvent { public Receive(Packet packet) {super(packet);}}
    public static class Send extends PacketEvent { public Send(Packet packet) {super(packet);}}
}
