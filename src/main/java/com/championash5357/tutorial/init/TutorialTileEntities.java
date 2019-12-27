package com.championash5357.tutorial.init;

import com.championash5357.tutorial.client.renderer.TileEntityJarRenderer;
import com.championash5357.tutorial.client.renderer.TileEntityStonePedestalRenderer;
import com.championash5357.tutorial.tileentity.TileEntityAdvancedCraftingTable;
import com.championash5357.tutorial.tileentity.TileEntityJar;
import com.championash5357.tutorial.tileentity.TileEntityStonePedestal;
import com.championash5357.tutorial.tileentity.TileEntityStorage;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;
import static com.championash5357.tutorial.util.InjectionUtil.Null;

import com.championash5357.tutorial.Tutorial;
@ObjectHolder(Tutorial.MOD_ID)
public class TutorialTileEntities {

	public static final TileEntityType<TileEntityJar> JAR = Null();
	public static final TileEntityType<TileEntityStorage> STORAGE = Null();
	public static final TileEntityType<TileEntityAdvancedCraftingTable> ADVANCED_CRAFTING_TABLE = Null();
	public static final TileEntityType<TileEntityStonePedestal> STONE_PEDESTAL = Null();

	@Mod.EventBusSubscriber(modid = Tutorial.MOD_ID, bus = Bus.MOD)
	public static class Register {
		
		@SubscribeEvent
		public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
			final TileEntityType<?>[] tile_entities = {
					TileEntityType.Builder.create(TileEntityJar::new, TutorialBlocks.JAR).build(null).setRegistryName(Tutorial.MOD_ID, "jar"),
					TileEntityType.Builder.create(TileEntityStorage::new).build(null).setRegistryName(Tutorial.MOD_ID, "storage"),
					TileEntityType.Builder.create(TileEntityAdvancedCraftingTable::new).build(null).setRegistryName(Tutorial.MOD_ID, "advanced_crafting_table"),
					TileEntityType.Builder.create(TileEntityStonePedestal::new, TutorialBlocks.STONE_PEDESTAL).build(null).setRegistryName(Tutorial.MOD_ID, "stone_pedestal")
			};
			
			event.getRegistry().registerAll(tile_entities);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void renders() {
		ClientRegistry.bindTileEntityRenderer(JAR, new TileEntityJarRenderer(TileEntityRendererDispatcher.instance));
		ClientRegistry.bindTileEntityRenderer(STONE_PEDESTAL, new TileEntityStonePedestalRenderer(TileEntityRendererDispatcher.instance));
	}
}