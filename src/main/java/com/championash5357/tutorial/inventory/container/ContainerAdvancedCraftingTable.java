package com.championash5357.tutorial.inventory.container;

import java.util.Optional;

import com.championash5357.tutorial.init.TutorialContainers;
import com.championash5357.tutorial.tileentity.TileEntityAdvancedCraftingTable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.CraftingResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.network.play.server.SSetSlotPacket;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.world.World;

public class ContainerAdvancedCraftingTable extends Container {
	
	public CraftingInventory craftMatrix = new CraftingInventory(this, 5, 5);
	public CraftResultInventory craftResult = new CraftResultInventory();
	private final IWorldPosCallable callable;
	private final TileEntityAdvancedCraftingTable table;
	private final PlayerEntity player;
	
	public ContainerAdvancedCraftingTable(final int windowID, PlayerInventory player) {
		this(windowID, player, new TileEntityAdvancedCraftingTable(), IWorldPosCallable.DUMMY);
	}
	
	public ContainerAdvancedCraftingTable(final int windowID, PlayerInventory player, TileEntityAdvancedCraftingTable table, IWorldPosCallable callable) {
		super(TutorialContainers.ADVANCED_CRAFTING_TABLE, windowID);
		this.table = table;
		this.player = player.player;
		this.callable = callable;
		this.addSlot(new CraftingResultSlot(this.player, this.craftMatrix, this.craftResult, 0, 141, 47));
		
		for(int y = 0; y < 5; ++y)
			for(int x = 0; x < 5; ++x)
				this.addSlot(new Slot(this.craftMatrix, x + y * 5, 11 + x * 18, 11 + y * 18));
		
		for(int y = 0; y < 3; ++y)
			for(int x = 0; x < 9; ++x)
				this.addSlot(new Slot(player, 9 + x + y * 9, 14 + x * 18, 124 + y * 18));
		
		for(int x = 0; x < 9; ++x)
			this.addSlot(new Slot(player, x, 14 + x * 18, 182));
		
		updateMatrix();
	}
	
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return table.interact(playerIn);
	}
	
	@Override
	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
		return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.callable.consume((world, player) -> {
			slotChangedCraftingGrid(windowId, world, this.player, craftMatrix, craftResult);
		});
		super.onCraftMatrixChanged(inventoryIn);
	}
	
	protected static void slotChangedCraftingGrid(int windowID, World world, PlayerEntity player, CraftingInventory matrix, CraftResultInventory result) {
		if(!world.isRemote) {
			ServerPlayerEntity sp = (ServerPlayerEntity) player;
			ItemStack stack = ItemStack.EMPTY;
			Optional<ICraftingRecipe> optional = world.getServer().getRecipeManager().getRecipe(IRecipeType.CRAFTING, matrix, world);
			if(optional.isPresent()) {
				ICraftingRecipe recipe = optional.get();
				if(result.canUseRecipe(world, sp, recipe)) stack = recipe.getCraftingResult(matrix);
			}
			
			result.setInventorySlotContents(0, stack);
			sp.connection.sendPacket(new SSetSlotPacket(windowID, 0, stack));
		}
	}
	
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		saveMatrix();
	}
	
	private void updateMatrix() {
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
			craftMatrix.setInventorySlotContents(i, table.getStackInSlot(i));
	}
	
	private void saveMatrix() {
		for(int i = 0; i < craftMatrix.getSizeInventory(); i++)
			table.setStackInSlot(i, craftMatrix.getStackInSlot(i));
		table.markDirty();
	}
	
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      if (slot != null && slot.getHasStack()) {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         if (index == 0) {
	            itemstack1.getItem().onCreated(itemstack1, table.getWorld(), playerIn);
	            if (!this.mergeItemStack(itemstack1, 26, 62, true)) {
	               return ItemStack.EMPTY;
	            }

	            slot.onSlotChange(itemstack1, itemstack);
	         } else if (index >= 26 && index < 53) {
	            if (!this.mergeItemStack(itemstack1, 53, 62, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (index >= 53 && index < 62) {
	            if (!this.mergeItemStack(itemstack1, 26, 53, false)) {
	               return ItemStack.EMPTY;
	            }
	         } else if (!this.mergeItemStack(itemstack1, 26, 62, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	         } else {
	            slot.onSlotChanged();
	         }

	         if (itemstack1.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);
	         if (index == 0) {
	            playerIn.dropItem(itemstack2, false);
	         }
	      }

	      return itemstack;
	   }
}
