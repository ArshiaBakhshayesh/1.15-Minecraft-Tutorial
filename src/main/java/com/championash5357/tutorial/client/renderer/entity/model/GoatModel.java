package com.championash5357.tutorial.client.renderer.entity.model;

import com.championash5357.tutorial.entity.passive.GoatEntity;
import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.QuadrupedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GoatModel extends QuadrupedModel<GoatEntity> {
	//private final ModelRenderer horn0;
	//private final ModelRenderer horn1;

	public GoatModel() {
		super(12, 0.0F, false, 10.0F, 4.0F, 2.0F, 2.0F, 24);
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 5.0F, 2.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.setTextureOffset(18, 4).addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F, 0.0F, false);

		this.headModel = new ModelRenderer(this);
		headModel.setRotationPoint(0.0F, 4.0F, -8.0F);
		headModel.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F, 0.0F, false);

		ModelRenderer horn0 = new ModelRenderer(this);
		horn0.setRotationPoint(-2.5F, -4.0F, -2.0F);
		headModel.addChild(horn0);
		setRotationAngle(horn0, -0.3491F, 0.0F, 0.0F);
		horn0.setTextureOffset(22, 0).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 5.0F, 2.0F, 0.0F, false);

		ModelRenderer horn1 = new ModelRenderer(this);
		horn1.setRotationPoint(2.5F, -4.0F, -2.0F);
		headModel.addChild(horn1);
		setRotationAngle(horn1, -0.3491F, 0.0F, 0.0F);
		horn1.setTextureOffset(22, 0).addBox(-0.5F, -3.0F, -1.0F, 1.0F, 4.0F, 2.0F, 0.0F, false);

		--this.legBackRight.rotationPointX;
		++this.legBackLeft.rotationPointX;
		this.legBackRight.rotationPointZ += 0.0F;
		this.legBackLeft.rotationPointZ += 0.0F;
		--this.legFrontRight.rotationPointX;
		++this.legFrontLeft.rotationPointX;
		--this.legFrontRight.rotationPointZ;
		--this.legFrontLeft.rotationPointZ;
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.headModel);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}