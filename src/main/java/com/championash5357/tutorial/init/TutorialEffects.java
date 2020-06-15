package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.potion.effect.ModEffect;

import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialEffects {

	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Tutorial.MOD_ID);
	
	public static final RegistryObject<ModEffect> ODD = EFFECTS.register("odd", () -> new ModEffect(EffectType.NEUTRAL, 0xD4F0AD));
}
