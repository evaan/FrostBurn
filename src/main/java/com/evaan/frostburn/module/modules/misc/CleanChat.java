package com.evaan.frostburn.module.modules.misc;

import com.evaan.frostburn.event.events.PacketEvent;
import com.evaan.frostburn.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.ArrayList;

public class CleanChat extends Module {
    public CleanChat() {
        super("CleanChat", Category.MISC);

        //put the cuss words here or something idk
        cussWords.add("fuck");
        cussWords.add("shit");

    }

    // no more saying cuss words. it is not good. i'm putting a video on youtube about no more saying cuss words. no more saying cuss words guys! it's inappropriate and violent! if you say a cuss word you're like, going to jail and like, when you go to jail, it- bu- when you go to jail if you say- if you say a cuss word you go to jail and when you go to jail you said a cuss word, then, you're only gonna eat broccoli and other vegetables for your whole life. you don't want to eat vegetables, sometimes people like eating sweets but... i eat broccoli. so... i'm okay with broccoli but i do NOT wanna go to jail. you can NOT go to jail. and saying cuss words is illegal. they are now gonna make a law about that. it is illegal, it is inappropriate, it is really violent. i better warn my school about that.
    ArrayList<String> cussWords = new ArrayList<>();

    @EventHandler
    private Listener<PacketEvent.Receive> packetListener = new Listener<>(event -> {
        if (event.getPacket() instanceof ChatMessageC2SPacket) {
            for (String cussWord : cussWords) {
                if (((ChatMessageC2SPacket) event.getPacket()).getChatMessage().toLowerCase().contains(cussWord)) event.cancel();
            }
        }
    });
}
