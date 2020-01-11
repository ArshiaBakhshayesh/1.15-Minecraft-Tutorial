package com.championash5357.tutorial.tileentity;

import com.championash5357.tutorial.init.TutorialTileEntities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityStonePedestal extends TileEntity {

	private ItemStackHandler inventory;
	private int direction;
	
	public TileEntityStonePedestal() {
		super(TutorialTileEntities.STONE_PEDESTAL);
		inventory = new ItemStackHandler(1);
		direction = 0;
	}

	@Override
	public void read(CompoundNBT compound) {
		inventory.deserializeNBT(compound.getCompound("inventory"));
		direction = compound.getInt("direction");
		super.read(compound);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("inventory", inventory.serializeNBT());
		compound.putInt("direction", direction);
		return super.write(compound);
	}
	
	@Override
	public CompoundNBT getUpdateTag() {
		return this.write(new CompoundNBT());
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		this.read(pkt.getNbtCompound());
	}
	
	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		return new SUpdateTileEntityPacket(pos, 0, this.getUpdateTag());
	}
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.of(() -> this.inventory).cast();
		return super.getCapability(cap, side);
	}
	
	public boolean setItemStack(ItemStack stack, int direction) {
		if(inventory.getStackInSlot(0).isEmpty()) {
			ItemStack inv = stack.copy();
			stack.shrink(1);
			inv.setCount(1);
			inventory.setStackInSlot(0, inv);
			this.direction = direction;
			markDirty();
			return true;
		}
		return false;
	}
	
	public ItemStack removeItemStack() {
		if(!inventory.getStackInSlot(0).isEmpty()) {
			ItemStack stack = inventory.getStackInSlot(0).copy();
			inventory.setStackInSlot(0, ItemStack.EMPTY);
			markDirty();
			return stack;
		}
		return ItemStack.EMPTY;
	}
	
	/*public ItemStack getInventory() {
		return inventory.getStackInSlot(0);
	}*/
	
	public int getDirection() {
		return direction;
	}
}
