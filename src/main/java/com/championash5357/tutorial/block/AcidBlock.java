package com.championash5357.tutorial.block;

import com.championash5357.tutorial.init.TutorialFluids;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AcidBlock extends FlowingFluidBlock {

	public AcidBlock() {
		super(() -> TutorialFluids.ACID.get(), Block.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100.0F).noDrops());
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if(this.getFluid().isIn(TutorialFluids.Tags.ACID)) {
			if(entityIn instanceof LivingEntity) {
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.POISON, 160, 2));
			}
			else entityIn.remove();
		}
	}
}
