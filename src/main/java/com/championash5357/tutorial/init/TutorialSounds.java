package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class TutorialSounds {
	
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Tutorial.MOD_ID);
	
	public static final RegistryObject<SoundEvent> PERSPECTIVES = SOUND_EVENTS.register("perspectives", () -> new SoundEvent(new ResourceLocation(Tutorial.MOD_ID, "perspectives")));
}
