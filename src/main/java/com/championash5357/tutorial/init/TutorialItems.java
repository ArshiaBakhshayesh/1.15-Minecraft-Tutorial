package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.item.ItemRuby;
import com.championash5357.tutorial.item.ItemTutorialRecord;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialItems {
	
	public static final ItemRuby RUBY = Null();
	public static final ItemTutorialRecord PERSPECTIVES_RECORD = Null();
	public static final Item BROCCOLI = Null();
	
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
					new Item(new Item.Properties().group(Tutorial.TUTORIAL_TAB).maxStackSize(16).food(Foods.BROCCOLI)).setRegistryName("broccoli")
			};
			
			event.getRegistry().registerAll(item);
		}
	}
}
