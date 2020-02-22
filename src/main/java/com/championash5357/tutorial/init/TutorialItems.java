package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import java.util.Map;
import java.util.Random;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.item.ItemRuby;
import com.championash5357.tutorial.item.ItemTutorialRecord;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Items;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IRegistryDelegate;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialItems {
	
	public static final ItemRuby RUBY = Null();
	public static final ItemTutorialRecord PERSPECTIVES_RECORD = Null();
	public static final Item BROCCOLI = Null();
	public static final BucketItem ACID_BUCKET = Null();
	
	public static class Foods {
		public static final Food BROCCOLI = (new Food.Builder()).hunger(2).saturation(0.2F).fastToEat().effect(new EffectInstance(TutorialEffects.ODD, 10), 0.1F).build();
	}
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] item = {
					new ItemRuby(),
					new ItemTutorialRecord(8, TutorialSounds.Register.getSoundEvent(new ResourceLocation(Tutorial.MOD_ID, "perspectives")), new Properties().maxStackSize(1).group(Tutorial.TUTORIAL_TAB).rarity(Rarity.EPIC), "perspectives_record"),
					new Item(new Item.Properties().group(Tutorial.TUTORIAL_TAB).maxStackSize(16).food(Foods.BROCCOLI)).setRegistryName("broccoli"),
					new BucketItem(() -> TutorialFluids.ACID, new Properties().group(Tutorial.TUTORIAL_TAB).maxStackSize(1)).setRegistryName(new ResourceLocation(Tutorial.MOD_ID, "acid_bucket"))
			};
			
			event.getRegistry().registerAll(item);
		}
		
		@SubscribeEvent
		public static void registerColors(final ColorHandlerEvent.Item event) {
			Map<IRegistryDelegate<Item>, IItemColor> color_map = ObfuscationReflectionHelper.getPrivateValue(ItemColors.class, event.getItemColors(), "colors");
			if(color_map.containsKey(Items.POTION.delegate)) {
				color_map.replace(Items.POTION.delegate, (stack, tintIndex) -> {
					return (new Random()).nextInt(16777215);
				});
			}
		}
	}
}
