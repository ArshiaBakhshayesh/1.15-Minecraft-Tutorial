package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.championash5357.tutorial.Tutorial;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
@ObjectHolder(Tutorial.MOD_ID)
public class TutorialSounds {
	
	public static final SoundEvent PERSPECTIVES = Null();
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		private static Map<ResourceLocation, SoundEvent> sound_events;
		
		@SubscribeEvent
		public static void register(final RegistryEvent.Register<SoundEvent> event) {
			Preconditions.checkState(sound_events != null, "SoundEvents weren't initialized before registration");
			sound_events.values().forEach(event.getRegistry()::register);
		}
		
		public static SoundEvent getSoundEvent(final ResourceLocation id) {
			Preconditions.checkState(sound_events != null, "Attempt to get SoundEvent before initialization");
			Preconditions.checkState(sound_events.containsKey(id), "Attempt to get non-existent SoundEvent %s", id);
			
			return sound_events.get(id);
		}
		
		public static void initialize() {
			Preconditions.checkState(sound_events == null, "Attempt to re-intialize Sound Events");
			
			sound_events = new ImmutableMap.Builder<ResourceLocation, SoundEvent>()
					.put(createSoundEvent("perspectives"))
					.build();
		}
		
		private static Map.Entry<ResourceLocation, SoundEvent> createSoundEvent(final String name) {
			final ResourceLocation id = new ResourceLocation(Tutorial.MOD_ID, name);
			final SoundEvent sound = new SoundEvent(id).setRegistryName(id);
			return Pair.of(id, sound);
		}
	}
}
