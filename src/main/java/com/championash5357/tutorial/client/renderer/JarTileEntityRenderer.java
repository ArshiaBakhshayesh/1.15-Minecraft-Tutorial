package com.championash5357.tutorial.client.renderer;

import com.championash5357.tutorial.tileentity.JarTileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class JarTileEntityRenderer extends TileEntityRenderer<JarTileEntity> {

	public JarTileEntityRenderer(TileEntityRendererDispatcher terd) {
		super(terd);
	}

	@Override
	public void render(JarTileEntity te, float partialTicks, MatrixStack stack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
		ItemEntity cookie = new ItemEntity(Minecraft.getInstance().world, 0, 0, 0, new ItemStack(Items.COOKIE));
		
		ObfuscationReflectionHelper.setPrivateValue(ItemEntity.class, cookie, 0, "field_70290_d");
		/*try {
			
			final Field hover = ObfuscationReflectionHelper.findField(ItemEntity.class, "field_70290_d"); //cookie.getClass().getDeclaredField("hoverStart");
			hover.setInt(cookie, 0);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}*/
		
		RenderSystem.pushMatrix();
		stack.rotate(new Quaternion(90F, 0, 0, true));
		stack.translate(0.5, 0.1, -0.08);
		for(int i = 0; i < te.amount(); i++) {
			RenderSystem.pushMatrix();
			stack.translate(0.0, i % 2 == 1 ? 0.1 : (i == 0 ? 0.0 : -0.1), i == 0 ? 0.0 : -0.0625/2);
			Minecraft.getInstance().getRenderManager().renderEntityStatic(cookie, 0, 0, 0, 0F, 0F, stack, buffer, combinedLight);
			RenderSystem.popMatrix();
		}
		RenderSystem.popMatrix();
	}
}
