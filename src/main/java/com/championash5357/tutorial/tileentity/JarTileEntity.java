package com.championash5357.tutorial.tileentity;

import com.championash5357.tutorial.init.TutorialTileEntities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

public class JarTileEntity extends TileEntity {

	private int amount = 0;
	
	public JarTileEntity() {
		super(TutorialTileEntities.JAR.get());
	}

	@Override
	public void read(CompoundNBT compound) {
		this.amount = compound.getInt("amount");
		super.read(compound);
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.putInt("amount", amount);
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
		return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
	}
	
	public void addCookie() {
		amount++;
		markDirty();
	}
	
	public void removeCookie() {
		amount--;
		markDirty();
	}
	
	public int amount() {
		return amount;
	}
}
