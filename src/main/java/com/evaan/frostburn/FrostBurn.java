package com.evaan.frostburn;

import com.evaan.frostburn.command.Command;
import com.evaan.frostburn.command.CommandManager;
import com.evaan.frostburn.event.events.PacketEvent;
import com.evaan.frostburn.module.ModuleManager;
import com.evaan.frostburn.util.SettingsManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

/**
 * @Author evaan
 * https://github.com/evaan
 */
public class FrostBurn implements ModInitializer {
	public static EventBus EVENT_BUS;

	@Override
	public void onInitialize() {
		EVENT_BUS = new EventManager();
		SettingsManager.init();
		ModuleManager.init();
		CommandManager.init();
		System.out.println("███████╗██████╗░░█████╗░░██████╗████████╗██████╗░██╗░░░██╗██████╗░███╗░░██╗");
		System.out.println("██╔════╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██╔══██╗██║░░░██║██╔══██╗████╗░██║");
		System.out.println("█████╗░░██████╔╝██║░░██║╚█████╗░░░░██║░░░██████╦╝██║░░░██║██████╔╝██╔██╗██║");
		System.out.println("██╔══╝░░██╔══██╗██║░░██║░╚═══██╗░░░██║░░░██╔══██╗██║░░░██║██╔══██╗██║╚████║");
		System.out.println("██║░░░░░██║░░██║╚█████╔╝██████╔╝░░░██║░░░██████╦╝╚██████╔╝██║░░██║██║░╚███║");
		System.out.println("╚═╝░░░░░╚═╝░░╚═╝░╚════╝░╚═════╝░░░░╚═╝░░░╚═════╝░░╚═════╝░╚═╝░░╚═╝╚═╝░░╚══╝");
		System.out.println("FrostBurn has been initialized!");
		System.out.println("https://github.com/evaan/frostburn");
	}
}
