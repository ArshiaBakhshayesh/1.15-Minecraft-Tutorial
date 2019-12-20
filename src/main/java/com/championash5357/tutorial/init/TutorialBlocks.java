package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.block.BlockAdvancedCraftingTable;
import com.championash5357.tutorial.block.BlockJar;
import com.championash5357.tutorial.block.BlockRubyOre;
import com.championash5357.tutorial.block.BlockStorage;
import com.google.common.base.Preconditions;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialBlocks {
	
	public static final BlockRubyOre RUBY_ORE = Null();
	public static final BlockJar JAR = Null();
	public static final BlockStorage STORAGE = Null();
	public static final BlockAdvancedCraftingTable ADVANCED_CRAFTING_TABLE = Null();
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerBlock(final RegistryEvent.Register<Block> event) {
			TutorialSounds.Register.initialize();
			
			final Block[] blocks = {
					new BlockRubyOre(),
					new BlockJar(),
					new BlockStorage(),
					new BlockAdvancedCraftingTable()
			};
			
			event.getRegistry().registerAll(blocks);
		}
		
		@SubscribeEvent
		public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
			final BlockItem[] items = {
					new BlockItem(RUBY_ORE, new Item.Properties().maxStackSize(32).rarity(Rarity.UNCOMMON).group(Tutorial.TUTORIAL_TAB)),
					new BlockItem(JAR, new Item.Properties().group(Tutorial.TUTORIAL_TAB)),
					new BlockItem(STORAGE, new Item.Properties().group(Tutorial.TUTORIAL_TAB)),
					new BlockItem(ADVANCED_CRAFTING_TABLE, new Item.Properties().group(Tutorial.TUTORIAL_TAB))
			};
			
			for(final BlockItem item : items) {
				final Block block = item.getBlock();
				final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has a null registry name", block);
				event.getRegistry().register(item.setRegistryName(registryName));
			}
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void renders() {
		RenderTypeLookup.setRenderLayer(JAR, RenderType.func_228643_e_());
	}
}
