package com.championash5357.tutorial.block;

import com.championash5357.tutorial.inventory.container.ContainerStorage;
import com.championash5357.tutorial.tileentity.TileEntityStorage;

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

public class BlockStorage extends Block {

	public BlockStorage() {
		super(Properties.create(Material.ROCK));
		this.setRegistryName("storage");
	}
	
	@Override
	public ActionResultType func_225533_a_(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if(!worldIn.isRemote && player instanceof ServerPlayerEntity) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile instanceof TileEntityStorage)
				NetworkHooks.openGui((ServerPlayerEntity) player, new InterfaceStorage((TileEntityStorage) tile), (buffer) -> {
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
		return new TileEntityStorage();
	}
	
	public static class InterfaceStorage implements INamedContainerProvider {
		
		private final TileEntityStorage storage;
		
		public InterfaceStorage(TileEntityStorage storage) {
			this.storage = storage;
		}
		
		@Override
		public ITextComponent getDisplayName() {
			return NarratorChatListener.field_216868_a;
		}

		@Override
		public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerIn) {
			return new ContainerStorage(windowID, playerInventory, storage);
		}
	}
}
