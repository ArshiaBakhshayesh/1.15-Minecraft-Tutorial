package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.entity.passive.GoatEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialEntities {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Tutorial.MOD_ID);
	
	public static final RegistryObject<EntityType<GoatEntity>> GOAT = ENTITY_TYPES.register("goat", () -> EntityType.Builder.create(GoatEntity::new, EntityClassification.CREATURE).size(0.9f, 1.4f).setTrackingRange(32).build("goat"));
}
