package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.potion.effect.ModEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialEffects {

	public static final ModEffect ODD = Null();
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerEffect(final RegistryEvent.Register<Effect> event) {
			final Effect[] effects = {
					new ModEffect(EffectType.NEUTRAL, 0xD4F0AD).setRegistryName("odd")
			};
			
			event.getRegistry().registerAll(effects);
		}
	}
}
