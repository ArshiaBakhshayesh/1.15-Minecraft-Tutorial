package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.tileentity.AdvancedCraftingTableTileEntity;
import com.championash5357.tutorial.tileentity.JarTileEntity;
import com.championash5357.tutorial.tileentity.StonePedestalTileEntity;
import com.championash5357.tutorial.tileentity.StorageTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class TutorialTileEntities {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Tutorial.MOD_ID);
	
	public static final RegistryObject<TileEntityType<JarTileEntity>> JAR = TILE_ENTITY_TYPES.register("jar", () -> TileEntityType.Builder.create(JarTileEntity::new, TutorialBlocks.JAR.get()).build(null));
	public static final RegistryObject<TileEntityType<StorageTileEntity>> STORAGE = TILE_ENTITY_TYPES.register("storage", () -> TileEntityType.Builder.create(StorageTileEntity::new).build(null));
	public static final RegistryObject<TileEntityType<AdvancedCraftingTableTileEntity>> ADVANCED_CRAFTING_TABLE = TILE_ENTITY_TYPES.register("advanced_crafting_table", () -> TileEntityType.Builder.create(AdvancedCraftingTableTileEntity::new).build(null));
	public static final RegistryObject<TileEntityType<StonePedestalTileEntity>> STONE_PEDESTAL = TILE_ENTITY_TYPES.register("stone_pedestal", () -> TileEntityType.Builder.create(StonePedestalTileEntity::new, TutorialBlocks.STONE_PEDESTAL.get()).build(null));
}