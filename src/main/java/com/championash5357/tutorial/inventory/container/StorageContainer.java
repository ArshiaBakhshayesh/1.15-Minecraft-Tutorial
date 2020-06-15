package com.championash5357.tutorial.inventory.container;

import com.championash5357.tutorial.init.TutorialContainers;
import com.championash5357.tutorial.init.TutorialCriterions;
import com.championash5357.tutorial.tileentity.StorageTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class StorageContainer extends Container {

	private final StorageTileEntity storage;
	
	public StorageContainer(final int windowID, PlayerInventory player) {
		this(windowID, player, new StorageTileEntity());
	}
	
	public StorageContainer(final int windowID, PlayerInventory player, StorageTileEntity storage) {
		super(TutorialContainers.STORAGE.get(), windowID);
		this.storage = storage;
		ItemStackHandler inventory = storage.getInventory();
		
		for(int y = 0; y < 3; ++y)
			for(int x = 0; x < 3; ++x)
				this.addSlot(new SlotItemHandler(inventory, x + y * 3, 62 + x * 18, 17 + y * 18) {
					@Override
					public void onSlotChanged() {
						storage.save();
						if(!player.player.world.isRemote) TutorialCriterions.STORAGE_CHANGED.trigger((ServerPlayerEntity) player.player, storage.getInventory());
					}
				});
		
		for(int y = 0; y < 3; ++y)
			for(int x = 0; x < 9; ++x)
				this.addSlot(new Slot(player, 9 + x + y * 9, 8 + x * 18, 84 + y * 18));
		
		for(int x = 0; x < 9; ++x)
			this.addSlot(new Slot(player, x, 8 + x * 18, 142));
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.storage.interact(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack previous = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(index);
		
		if(slot != null && slot.getHasStack()) {
			ItemStack current = slot.getStack();
			previous = current.copy();
			
			if(index < 9) {
				if(!this.mergeItemStack(current, 9, 45, true))
					return ItemStack.EMPTY;
			} else {
				if(!this.mergeItemStack(current, 0, 9, false))
					return ItemStack.EMPTY;
			}
			
			if(current.getCount() == 0)
				slot.putStack((ItemStack) ItemStack.EMPTY);
			else
				slot.onSlotChanged();
			
			if(current.getCount() == previous.getCount())
				return ItemStack.EMPTY;
			slot.onTake(playerIn, current);
		}
		
		return previous;
	}
}
