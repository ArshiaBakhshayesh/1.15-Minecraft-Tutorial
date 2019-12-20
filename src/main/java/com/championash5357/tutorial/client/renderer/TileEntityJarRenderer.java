package com.championash5357.tutorial.client.renderer;

import java.lang.reflect.Field;

import com.championash5357.tutorial.tileentity.TileEntityJar;
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
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TileEntityJarRenderer extends TileEntityRenderer<TileEntityJar> {

	public TileEntityJarRenderer(TileEntityRendererDispatcher terd) {
		super(terd);
	}

	@Override
	public void func_225616_a_(TileEntityJar te, float p_225616_2_, MatrixStack p_225616_3_, IRenderTypeBuffer p_225616_4_, int p_225616_5_, int p_225616_6_) {
		ItemEntity cookie = new ItemEntity(Minecraft.getInstance().world, 0, 0, 0, new ItemStack(Items.COOKIE));
		try {
			final Field hover = cookie.getClass().getDeclaredField("hoverStart");
			hover.setAccessible(true);
			hover.setInt(cookie, 0);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		RenderSystem.pushMatrix();
		p_225616_3_.func_227863_a_(new Quaternion(90F, 0, 0, true));
		p_225616_3_.func_227861_a_(0.5, 0.1, -0.08);
		for(int i = 0; i < te.amount(); i++) {
			RenderSystem.pushMatrix();
			p_225616_3_.func_227861_a_(0.0, i % 2 == 1 ? 0.1 : (i == 0 ? 0.0 : -0.1), i == 0 ? 0.0 : -0.0625/2);
			Minecraft.getInstance().getRenderManager().func_229084_a_(cookie, 0, 0, 0, 0F, 0F, p_225616_3_, p_225616_4_, p_225616_5_);
			RenderSystem.popMatrix();
		}
		RenderSystem.popMatrix();
	}
}
