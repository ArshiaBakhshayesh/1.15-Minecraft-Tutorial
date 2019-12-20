package com.championash5357.tutorial.init;

import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.inventory.container.ContainerAdvancedCraftingTable;
import com.championash5357.tutorial.inventory.container.ContainerStorage;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
@ObjectHolder(Tutorial.MOD_ID)
public class TutorialContainers {

	public static final ContainerType<ContainerStorage> STORAGE = Null();
	public static final ContainerType<ContainerAdvancedCraftingTable> ADVANCED_CRAFTING_TABLE = Null();
	
	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
			final ContainerType<?>[] containers = {
				new ContainerType<>(ContainerStorage::new).setRegistryName("storage"),
				new ContainerType<>(ContainerAdvancedCraftingTable::new).setRegistryName("advanced_crafting_table")
			};
			
			event.getRegistry().registerAll(containers);
		}
	}
}
