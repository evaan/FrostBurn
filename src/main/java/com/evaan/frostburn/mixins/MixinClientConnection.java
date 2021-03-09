package com.evaan.frostburn.mixins;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.FrostBurn;
import com.evaan.frostburn.command.CommandManager;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.Future;

/**
 * @Author evaan
 * https://github.com/evaan
 */

@Mixin(ClientConnection.class)
public class MixinClientConnection {
    boolean found = false;

    @Inject(method = "send(Lnet/minecraft/network/Packet;Lio/netty/util/concurrent/GenericFutureListener;)V", at = @At("HEAD"), cancellable = true)
    public void send(Packet<?> packet, GenericFutureListener<? extends Future<? super Void>> genericFutureListener_1, CallbackInfo callbackInfo) {
        if (packet instanceof ChatMessageC2SPacket && ((ChatMessageC2SPacket) packet).getChatMessage().startsWith(Command.prefix)) {
            String[] args = ((ChatMessageC2SPacket) packet).getChatMessage().substring(1).split(" ");
            CommandManager.commands.forEach(command -> {for (String name : command.name) {if (args[0].equalsIgnoreCase(name)) command.onCommand(args); found = true;}});
            if (!found) {Command.sendMessage("Command not found! Do " + Command.prefix + "help for a list of commands.");}
            callbackInfo.cancel();
        }
    }
}
