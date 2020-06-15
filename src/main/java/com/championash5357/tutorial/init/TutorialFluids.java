package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.fluid.AcidFluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialFluids {

	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Tutorial.MOD_ID);
	
	public static final RegistryObject<AcidFluid.Flowing> FLOWING_ACID = FLUIDS.register("flowing_acid", () -> new AcidFluid.Flowing());
	public static final RegistryObject<AcidFluid.Source> ACID = FLUIDS.register("acid", () -> new AcidFluid.Source());
	
	public static class Tags {
		public static final Tag<Fluid> ACID = new FluidTags.Wrapper(new ResourceLocation(Tutorial.MOD_ID, "acid"));
	}
}
