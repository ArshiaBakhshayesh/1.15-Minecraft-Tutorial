package com.championash5357.tutorial.client.renderer;

import com.championash5357.tutorial.tileentity.TileEntityStonePedestal;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;

@SuppressWarnings("deprecation")
@OnlyIn(Dist.CLIENT)
public class TileEntityStonePedestalRenderer extends TileEntityRenderer<TileEntityStonePedestal> {

	public TileEntityStonePedestalRenderer(TileEntityRendererDispatcher dispatcher) {
		super(dispatcher);
	}

	@Override
	public void func_225616_a_(TileEntityStonePedestal te, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int p_225616_5_, int p_225616_6_) {
		te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(inventory -> {
			if(!inventory.getStackInSlot(0).isEmpty()) {
				ItemStack stack = inventory.getStackInSlot(0);
				
				matrix.func_227861_a_(.5, .75, .5);
				matrix.func_227863_a_(new Quaternion(0f, -te.getDirection() * 45f, 0f, true));
				matrix.func_227863_a_(new Quaternion(0f, 0f, 135f, true));
				
				Minecraft.getInstance().getItemRenderer().func_229110_a_(stack, TransformType.FIXED, p_225616_5_, p_225616_6_, matrix, buffer);
			}
		});
	}
}
