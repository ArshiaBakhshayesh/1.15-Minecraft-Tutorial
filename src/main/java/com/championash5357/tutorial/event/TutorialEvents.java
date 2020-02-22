package com.championash5357.tutorial.event;

import java.util.Random;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.command.impl.HelloCommand;
import com.championash5357.tutorial.init.TutorialItems;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID)
public class TutorialEvents {

	@SubscribeEvent
	public static void onServerStarting(final FMLServerStartingEvent event) {
		HelloCommand.register(event.getCommandDispatcher());
	}
	
	@SubscribeEvent
	public static void onEntityStruck(final EntityStruckByLightningEvent event) {
		if(event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if(player.getHeldItemOffhand().getItem() == TutorialItems.BROCCOLI) event.setCanceled(true);
		}
	}
	
	@SubscribeEvent
	public static void onJump(final LivingJumpEvent event) {
		if(event.getEntityLiving() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			Random rand = new Random();
			player.addVelocity(rand.nextFloat() > .5 ? -rand.nextInt(10) : rand.nextInt(10), rand.nextInt(1), rand.nextFloat() > .5 ? -rand.nextInt(10) : rand.nextInt(10));
		}
	}
}
