  package com.championash5357.tutorial;

import com.championash5357.tutorial.client.gui.GuiAdvancedCraftingTable;
import com.championash5357.tutorial.client.gui.GuiStorage;
import com.championash5357.tutorial.config.TutorialConfig;
import com.championash5357.tutorial.init.TutorialBiomes;
import com.championash5357.tutorial.init.TutorialBlocks;
import com.championash5357.tutorial.init.TutorialContainers;
import com.championash5357.tutorial.init.TutorialCriterions;
import com.championash5357.tutorial.init.TutorialPotions;
import com.championash5357.tutorial.init.TutorialTab;
import com.championash5357.tutorial.init.TutorialTileEntities;
import com.championash5357.tutorial.util.GenerationUtil;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

@Mod(Tutorial.MOD_ID)
@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
public class Tutorial {
	
	public static final String MOD_ID = "tutorial";
	
	public static final ItemGroup TUTORIAL_TAB = new TutorialTab();
	
	public Tutorial() {
		ModLoadingContext.get().registerConfig(Type.COMMON, TutorialConfig.COMMON_SPEC, "tutorial-common.toml");
	}
	
	@SubscribeEvent
	public static void setup(final FMLCommonSetupEvent event) {
		ShapedRecipe.setCraftingSize(TutorialConfig.COMMON.crafting_width.get(), TutorialConfig.COMMON.crafting_height.get());
		TutorialCriterions.register();
		TutorialPotions.addRecipes();
		TutorialBiomes.addBiomes();
		GenerationUtil.generateOre(FillerBlockType.NATURAL_STONE, TutorialBlocks.RUBY_ORE.getDefaultState(), 8, 1, 0, 0, 16);
	}
	
	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public static void clientSetup(final FMLClientSetupEvent event) {
		TutorialBlocks.renders();
		TutorialTileEntities.renders();
		ScreenManager.registerFactory(TutorialContainers.STORAGE, GuiStorage::new);
		ScreenManager.registerFactory(TutorialContainers.ADVANCED_CRAFTING_TABLE, GuiAdvancedCraftingTable::new);
	}
	
	@SubscribeEvent
	public static void enqueueIMC(final InterModEnqueueEvent event) {
		
	}
	
	@SubscribeEvent
	public static void processIMC(final InterModProcessEvent event) {
		
	}
}
