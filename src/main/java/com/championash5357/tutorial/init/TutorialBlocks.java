package com.championash5357.tutorial.init;

import java.util.function.Function;

import javax.annotation.Nullable;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.block.AcidBlock;
import com.championash5357.tutorial.block.AdvancedCraftingTableBlock;
import com.championash5357.tutorial.block.JarBlock;
import com.championash5357.tutorial.block.RubyOreBlock;
import com.championash5357.tutorial.block.StonePedestalBlock;
import com.championash5357.tutorial.block.StorageBlock;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialBlocks {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Tutorial.MOD_ID);
	
	public static final RegistryObject<RubyOreBlock> RUBY_ORE = register("ruby_ore", new RubyOreBlock(), block -> new BlockItem(block, new Item.Properties().maxStackSize(32).rarity(Rarity.UNCOMMON).group(Tutorial.TUTORIAL_TAB)));
	public static final RegistryObject<JarBlock> JAR = register("jar", new JarBlock(), block -> new BlockItem(block, new Item.Properties().group(Tutorial.TUTORIAL_TAB)));
	public static final RegistryObject<StorageBlock> STORAGE = register("storage", new StorageBlock(), block -> new BlockItem(block, new Item.Properties().group(Tutorial.TUTORIAL_TAB)));
	public static final RegistryObject<AdvancedCraftingTableBlock> ADVANCED_CRAFTING_TABLE = register("advanced_crafting_table", new AdvancedCraftingTableBlock(), block -> new BlockItem(block, new Item.Properties().group(Tutorial.TUTORIAL_TAB)));
	public static final RegistryObject<StonePedestalBlock> STONE_PEDESTAL = register("stone_pedestal", new StonePedestalBlock(), block -> new BlockItem(block, new Item.Properties().group(Tutorial.TUTORIAL_TAB)));
	public static final RegistryObject<AcidBlock> ACID = register("acid", new AcidBlock(), null);
	
	private static <V extends Block> RegistryObject<V> register(String name, V block, @Nullable Function<V, BlockItem> function) {
		if(function != null) {
			TutorialItems.ITEMS.register(name, () -> function.apply(block));
		}
		
		return BLOCKS.register(name, () -> block);
	}
}
