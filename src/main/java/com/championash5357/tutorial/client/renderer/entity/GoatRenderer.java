package com.championash5357.tutorial.client.renderer.entity;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.client.renderer.entity.model.GoatModel;
import com.championash5357.tutorial.entity.passive.GoatEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoatRenderer extends MobRenderer<GoatEntity, GoatModel>{

	private static final ResourceLocation GOAT_TEXTURES = new ResourceLocation(Tutorial.MOD_ID, "textures/entity/goat/goat.png");
	
	public GoatRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GoatModel(), 0.7f);
	}

	@Override
	public ResourceLocation getEntityTexture(GoatEntity entity) {
		return GOAT_TEXTURES;
	}

}
