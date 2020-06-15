package com.championash5357.tutorial.util;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class GenerationUtil {

	public static void generate(Decoration stage, ConfiguredFeature<?, ?> feature, Biome... biomes) {
		for(Biome biome : biomes) biome.addFeature(stage, feature);
	}
	
	public static void generateOre(FillerBlockType target, BlockState state, int vein_size, int chunk_amount, int min_height, int max_height_base, int maximum, Biome... biomes) {
		generate(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(target, state, vein_size)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(chunk_amount, min_height, max_height_base, maximum))), biomes);
	}
	
	public static void generateOre(FillerBlockType target, BlockState state, int vein_size, int chunk_amount, int min_height, int max_height_base, int maximum) {
		Biome[] biomes = {};
		generate(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE.withConfiguration(new OreFeatureConfig(target, state, vein_size)).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(chunk_amount, min_height, max_height_base, maximum))), GameRegistry.findRegistry(Biome.class).getValues().toArray(biomes));
	}
}
