package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.inventory.container.AdvancedCraftingTableContainer;
import com.championash5357.tutorial.inventory.container.StorageContainer;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class TutorialContainers {

	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, Tutorial.MOD_ID);
	
	public static final RegistryObject<ContainerType<StorageContainer>> STORAGE = CONTAINER_TYPES.register("storage", () -> new ContainerType<>(StorageContainer::new));
	public static final RegistryObject<ContainerType<AdvancedCraftingTableContainer>> ADVANCED_CRAFTING_TABLE = CONTAINER_TYPES.register("advanced_crafting_table", () -> new ContainerType<>(AdvancedCraftingTableContainer::new));
}
