package com.championash5357.tutorial.client.proxy;

import java.util.Map;
import java.util.Random;

import com.championash5357.tutorial.client.gui.AdvancedCraftingTableScreen;
import com.championash5357.tutorial.client.gui.StorageScreen;
import com.championash5357.tutorial.client.renderer.JarTileEntityRenderer;
import com.championash5357.tutorial.client.renderer.StonePedestalTileEntityRenderer;
import com.championash5357.tutorial.init.TutorialBlocks;
import com.championash5357.tutorial.init.TutorialContainers;
import com.championash5357.tutorial.init.TutorialTileEntities;
import com.championash5357.tutorial.proxy.IProxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.IRegistryDelegate;

public class ClientProxy implements IProxy {

	@Override
	public void setup(IEventBus forge, IEventBus mod) {
		mod.addListener(this::clientSetup);
		mod.addListener(this::registerColors);
	}

	
	private void clientSetup(final FMLClientSetupEvent event) {
		blockRenders();
		tileEntityRenders();
		ScreenManager.registerFactory(TutorialContainers.STORAGE.get(), StorageScreen::new);
		ScreenManager.registerFactory(TutorialContainers.ADVANCED_CRAFTING_TABLE.get(), AdvancedCraftingTableScreen::new);
	}
	
	private void blockRenders() {
		RenderTypeLookup.setRenderLayer(TutorialBlocks.JAR.get(), RenderType.getCutout());
	}
	
	private void tileEntityRenders() {
		ClientRegistry.bindTileEntityRenderer(TutorialTileEntities.JAR.get(), JarTileEntityRenderer::new);
		ClientRegistry.bindTileEntityRenderer(TutorialTileEntities.STONE_PEDESTAL.get(), StonePedestalTileEntityRenderer::new);
	}
	
	private void registerColors(final ColorHandlerEvent.Item event) {
		Map<IRegistryDelegate<Item>, IItemColor> color_map = ObfuscationReflectionHelper.getPrivateValue(ItemColors.class, event.getItemColors(), "field_186732_a");
		if(color_map.containsKey(Items.POTION.delegate)) {
			color_map.replace(Items.POTION.delegate, (stack, tintIndex) -> {
				return (new Random()).nextInt(16777215);
			});
		}
	}
}
