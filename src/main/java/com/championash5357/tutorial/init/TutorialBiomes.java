package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.world.biome.DryTundraBiome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialBiomes {

	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Tutorial.MOD_ID);
	
	public static final RegistryObject<DryTundraBiome> DRY_TUNDRA = BIOMES.register("dry_tundra", () -> new DryTundraBiome());
	
	public static void addBiomes() {
		BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(DRY_TUNDRA.get(), 30));
		BiomeManager.addSpawnBiome(DRY_TUNDRA.get());
		
		Biomes.PLAINS.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(TutorialEntities.GOAT.get(), 200, 2, 5));
	}
}
