package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.fluid.FluidAcid;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Tutorial.MOD_ID)
public class TutorialFluids {

	public static final FluidAcid.Flowing FLOWING_ACID = Null();
	public static final FluidAcid.Source ACID = Null();
	
	public static class Tags {
		public static final Tag<Fluid> ACID = new FluidTags.Wrapper(new ResourceLocation(Tutorial.MOD_ID, "acid"));
	}
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerFluids(final RegistryEvent.Register<Fluid> event) {
			final Fluid[] fluids = {
					new FluidAcid.Flowing().setRegistryName(Tutorial.MOD_ID, "flowing_acid"),
					new FluidAcid.Source().setRegistryName(Tutorial.MOD_ID, "acid")
			};
			
			event.getRegistry().registerAll(fluids);
		}
	}
}
