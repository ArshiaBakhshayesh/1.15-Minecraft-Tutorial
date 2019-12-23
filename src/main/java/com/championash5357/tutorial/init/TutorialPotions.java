package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.championash5357.tutorial.Tutorial;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionBrewing;
import net.minecraft.potion.Potions;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialPotions {

	public static final Potion ODD = Null();
	public static final Potion LONG_ODD = Null();
	public static final Potion SHORT_ODD = Null();
	
	private static Method brewing;
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerPotion(final RegistryEvent.Register<Potion> event) {
			final Potion[] potions = {
					new Potion(new EffectInstance(TutorialEffects.ODD, 3600)).setRegistryName("odd"),
					new Potion(new EffectInstance(TutorialEffects.ODD, 9600)).setRegistryName("long_odd"),
					new Potion(new EffectInstance(TutorialEffects.ODD, 1200)).setRegistryName("short_odd")
			};
			
			event.getRegistry().registerAll(potions);
		}
	}
	
	private static void addMix(Potion base, Item ingredient, Potion result) {
		if(brewing == null) {
			brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewing.setAccessible(true);
		}
		
		try {
			brewing.invoke(null, base, ingredient, result);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public static void addRecipes() {
		addMix(Potions.AWKWARD, Items.CHARCOAL, ODD);
		addMix(ODD, Items.REDSTONE, LONG_ODD);
		addMix(ODD, Items.FEATHER, SHORT_ODD);
	}
}
