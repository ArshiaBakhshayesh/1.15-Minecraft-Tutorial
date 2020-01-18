package com.championash5357.tutorial.event;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.command.impl.HelloCommand;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID)
public class TutorialEvents {

	@SubscribeEvent
	public static void onServerStarting(final FMLServerStartingEvent event) {
		HelloCommand.register(event.getCommandDispatcher());
	}
}
