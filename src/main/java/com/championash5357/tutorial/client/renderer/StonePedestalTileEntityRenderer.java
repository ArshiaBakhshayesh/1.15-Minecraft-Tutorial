package com.championash5357.tutorial.client.renderer;

import com.championash5357.tutorial.tileentity.StonePedestalTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class StonePedestalTileEntityRenderer extends TileEntityRenderer<StonePedestalTileEntity> {

	public StonePedestalTileEntityRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void render(StonePedestalTileEntity te, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
			if(!inventory.getStackInSlot(0).isEmpty()) {
				ItemStack stack = inventory.getStackInSlot(0);
				
				matrix.translate(.5, .75, .5);
				matrix.rotate(new Quaternion(0f, -te.getDirection() * 45f, 0f, true));
				matrix.rotate(new Quaternion(0f, 0f, 135f, true));
				
				Minecraft.getInstance().getItemRenderer().renderItem(stack, TransformType.FIXED, combinedLight, combinedOverlay, matrix, buffer);
			}
		});
	}
}
