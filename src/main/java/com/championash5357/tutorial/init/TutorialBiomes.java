package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.world.biome.BiomeDryTundra;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialBiomes {

	public static final BiomeDryTundra DRY_TUNDRA = Null();
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerBiomes(final RegistryEvent.Register<Biome> event) {
			final Biome[] biomes = {
				new BiomeDryTundra().setRegistryName(Tutorial.MOD_ID, "dry_tundra")	
			};
			
			event.getRegistry().registerAll(biomes);
		}
	}
	
	public static void addBiomes() {
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(DRY_TUNDRA, 30));
		BiomeManager.addSpawnBiome(DRY_TUNDRA);
	}
}
