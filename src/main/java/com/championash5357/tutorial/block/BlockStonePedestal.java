package com.championash5357.tutorial.block;

import com.championash5357.tutorial.tileentity.TileEntityStonePedestal;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockStonePedestal extends Block {

	public BlockStonePedestal() {
		super(Properties.create(Material.ROCK).hardnessAndResistance(3.0f));
		setRegistryName("stone_pedestal");
	}

	@Override
	public ActionResultType func_225533_a_(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		TileEntityStonePedestal pedestal = (TileEntityStonePedestal) world.getTileEntity(pos);
		if(player.getHeldItem(hand).isEmpty()) {
			ItemStack stack = pedestal.removeItemStack();
			player.addItemStackToInventory(stack);
			return stack.isEmpty() ? ActionResultType.PASS : ActionResultType.SUCCESS;
		} else if(player.getHeldItem(hand).getItem() instanceof SwordItem) {
			int angle = (int) Math.abs(Math.round(player.getRotatedYaw(Rotation.CLOCKWISE_180) / 45.0));
			boolean works = pedestal.setItemStack(player.getHeldItem(hand), angle == 8 ? 0 : angle);
			return works ? ActionResultType.SUCCESS : ActionResultType.PASS;
		}
		return ActionResultType.PASS;
	}
	
	@Override
	public void harvestBlock(World worldIn, PlayerEntity player, BlockPos pos, BlockState state, TileEntity te, ItemStack stack) {
		TileEntityStonePedestal pedestal = (TileEntityStonePedestal) te;
		if(!worldIn.isRemote) worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), pedestal.removeItemStack()));
		
		super.harvestBlock(worldIn, player, pos, state, te, stack);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return Block.makeCuboidShape(0, 0, 0, 16, 8, 16);
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityStonePedestal();
	}
}
