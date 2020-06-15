package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.item.RubyItem;

import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialItems {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Tutorial.MOD_ID);
	
	public static final RegistryObject<RubyItem> RUBY = ITEMS.register("ruby", () -> new RubyItem());
	public static final RegistryObject<MusicDiscItem> PERSPECTIVES_RECORD = ITEMS.register("perspectives_record", () -> new MusicDiscItem(8, () -> TutorialSounds.PERSPECTIVES.get(), new Properties().maxStackSize(1).group(Tutorial.TUTORIAL_TAB).rarity(Rarity.EPIC)));
	public static final RegistryObject<Item> BROCCOLI = ITEMS.register("broccoli", () -> new Item(new Item.Properties().group(Tutorial.TUTORIAL_TAB).maxStackSize(16).food(Foods.BROCCOLI)));
	public static final RegistryObject<BucketItem> ACID_BUCKET = ITEMS.register("acid_bucket", () -> new BucketItem(() -> TutorialFluids.ACID.get(), new Properties().group(Tutorial.TUTORIAL_TAB).maxStackSize(1)));
	
	public static class Foods {
		public static final Food BROCCOLI = (new Food.Builder()).hunger(2).saturation(0.2F).fastToEat().effect(() -> new EffectInstance(TutorialEffects.ODD.get(), 10), 0.1F).build();
	}
}
