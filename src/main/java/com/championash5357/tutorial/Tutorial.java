package com.championash5357.tutorial;

import com.championash5357.tutorial.client.proxy.ClientProxy;
import com.championash5357.tutorial.config.TutorialConfig;
import com.championash5357.tutorial.init.TutorialBiomes;
import com.championash5357.tutorial.init.TutorialBlocks;
import com.championash5357.tutorial.init.TutorialContainers;
import com.championash5357.tutorial.init.TutorialCriterions;
import com.championash5357.tutorial.init.TutorialEffects;
import com.championash5357.tutorial.init.TutorialEntities;
import com.championash5357.tutorial.init.TutorialFluids;
import com.championash5357.tutorial.init.TutorialItems;
import com.championash5357.tutorial.init.TutorialPotions;
import com.championash5357.tutorial.init.TutorialSounds;
import com.championash5357.tutorial.init.TutorialTab;
import com.championash5357.tutorial.init.TutorialTileEntities;
import com.championash5357.tutorial.proxy.IProxy;
import com.championash5357.tutorial.proxy.ServerProxy;
import com.championash5357.tutorial.util.GenerationUtil;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.world.gen.feature.OreFeatureConfig.FillerBlockType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@SuppressWarnings("deprecation")
@Mod(Tutorial.MOD_ID)
public class Tutorial {

	public static final String MOD_ID = "tutorial";

	public static IProxy proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

	public static final ItemGroup TUTORIAL_TAB = new TutorialTab();

	public Tutorial() {
		ModLoadingContext.get().registerConfig(Type.COMMON, TutorialConfig.COMMON_SPEC, "tutorial-common.toml");

		IEventBus mod = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus forge = MinecraftForge.EVENT_BUS;

		proxy.setup(forge, mod);
		register(mod);

		mod.addListener(this::setup);
		mod.addListener(this::enqueueIMC);
		mod.addListener(this::processIMC);
	}

	private void setup(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(() -> {
			ShapedRecipe.setCraftingSize(TutorialConfig.COMMON.crafting_width.get(), TutorialConfig.COMMON.crafting_height.get());
			TutorialCriterions.register();
			TutorialPotions.addRecipes();
			TutorialBiomes.addBiomes();
			GenerationUtil.generateOre(FillerBlockType.NATURAL_STONE, TutorialBlocks.RUBY_ORE.get().getDefaultState(), 8, 1, 0, 0, 16);
		});
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {

	}

	private void processIMC(final InterModProcessEvent event) {

	}

	private void register(IEventBus mod) {
		TutorialBiomes.BIOMES.register(mod);
		TutorialBlocks.BLOCKS.register(mod);
		TutorialContainers.CONTAINER_TYPES.register(mod);
		TutorialEffects.EFFECTS.register(mod);
		TutorialEntities.ENTITY_TYPES.register(mod);
		TutorialFluids.FLUIDS.register(mod);
		TutorialItems.ITEMS.register(mod);
		TutorialPotions.POTIONS.register(mod);
		TutorialSounds.SOUND_EVENTS.register(mod);
		TutorialTileEntities.TILE_ENTITY_TYPES.register(mod);
	}
}
