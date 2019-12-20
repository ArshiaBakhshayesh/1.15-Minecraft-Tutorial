package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.item.ItemRuby;
import com.championash5357.tutorial.item.ItemTutorialRecord;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.item.Rarity;
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
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {
			final Item[] item = {
					new ItemRuby(),
					new ItemTutorialRecord(8, TutorialSounds.Register.getSoundEvent(new ResourceLocation(Tutorial.MOD_ID, "perspectives")), new Properties().maxStackSize(1).group(Tutorial.TUTORIAL_TAB).rarity(Rarity.EPIC), "perspectives_record")
			};
			
			event.getRegistry().registerAll(item);
		}
	}
}
