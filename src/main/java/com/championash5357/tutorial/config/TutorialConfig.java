package com.championash5357.tutorial.config;

import org.apache.commons.lang3.tuple.Pair;

import com.championash5357.tutorial.Tutorial;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
public class TutorialConfig {
	
	public static class Common {
		
		public final IntValue crafting_width;
		public final IntValue crafting_height;
		
		public Common(ForgeConfigSpec.Builder builder) {
			builder.comment("Tutorial Mod Configurations")
				   .push("tutorial");
			
			crafting_width = builder
					.comment("This sets the crafting width of the game. If a value is higher declared by a different mod, this becomes obsolete.")
					.translation("tutorial.configgui.crafting_width")
					.worldRestart()
					.defineInRange("crafting_width", 3, 3, 5);
			
			crafting_height = builder
					.comment("This sets the crafting height of the game. If a value is higher declared by a different mod, this becomes obsolete.")
					.translation("tutorial.configgui.crafting_height")
					.worldRestart()
					.defineInRange("crafting_height", 3, 3, 5);
			
			builder.pop();
		}
	}
	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;
	static {
		final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	@SubscribeEvent
	public static void onLoad(final ModConfig.Loading event) {
		
	}
	
	@SubscribeEvent
	public static void onFileChange(final ModConfig.ConfigReloading event) {
		
	}
}
