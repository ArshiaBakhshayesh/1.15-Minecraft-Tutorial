package com.championash5357.tutorial.block;

import com.championash5357.tutorial.inventory.container.StorageContainer;
import com.championash5357.tutorial.tileentity.StorageTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class StorageBlock extends Block {

	public StorageBlock() {
		super(Properties.create(Material.ROCK));
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(!worldIn.isRemote && player instanceof ServerPlayerEntity) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile instanceof StorageTileEntity)
				NetworkHooks.openGui((ServerPlayerEntity) player, new InterfaceStorage((StorageTileEntity) tile), (buffer) -> {
					buffer.writeBlockPos(pos);
				});
		}
		return ActionResultType.SUCCESS;
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new StorageTileEntity();
	}
	
	public static class InterfaceStorage implements INamedContainerProvider {
		
		private final StorageTileEntity storage;
		
		public InterfaceStorage(StorageTileEntity storage) {
			this.storage = storage;
		}
		
		@Override
		public ITextComponent getDisplayName() {
			return NarratorChatListener.EMPTY;
		}

		@Override
		public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerIn) {
			return new StorageContainer(windowID, playerInventory, storage);
		}
	}
}
