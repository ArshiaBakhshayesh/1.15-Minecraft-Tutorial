package com.championash5357.tutorial.fluid;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.init.TutorialBlocks;
import com.championash5357.tutorial.init.TutorialFluids;
import com.championash5357.tutorial.init.TutorialItems;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.fluids.FluidAttributes;

public abstract class FluidAcid extends FlowingFluid {

	@Override
	public Fluid getFlowingFluid() {
		return TutorialFluids.FLOWING_ACID;
	}

	@Override
	public Fluid getStillFluid() {
		return TutorialFluids.ACID;
	}

	@Override
	protected boolean canSourcesMultiply() {
		return true;
	}

	@Override
	protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
	}

	@Override
	protected int getSlopeFindDistance(IWorldReader worldIn) {
		return 4;
	}

	@Override
	protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
		return 3;
	}

	@Override
	public Item getFilledBucket() {
		return TutorialItems.ACID_BUCKET;
	}

	@Override
	protected boolean func_215665_a(IFluidState state, IBlockReader world, BlockPos pos, Fluid fluid, Direction direction) {
		return direction == Direction.DOWN && !fluid.isIn(TutorialFluids.Tags.ACID);
	}

	@Override
	public int getTickRate(IWorldReader p_205569_1_) {
		return 60;
	}

	@Override
	protected float getExplosionResistance() {
		return 100.0F;
	}

	@Override
	protected BlockState getBlockState(IFluidState state) {
		return TutorialBlocks.ACID.getDefaultState().with(FlowingFluidBlock.LEVEL, Integer.valueOf(getLevelFromState(state)));
	}
	
	@Override
	public boolean isEquivalentTo(Fluid fluidIn) {
		return fluidIn == TutorialFluids.ACID || fluidIn == TutorialFluids.FLOWING_ACID;
	}
	
	@Override
	protected FluidAttributes createAttributes() {
		return FluidAttributes.builder(new ResourceLocation(Tutorial.MOD_ID, "block/acid_still"), new ResourceLocation(Tutorial.MOD_ID, "block/acid_flow"))
				.translationKey("block.tutorial.acid")
				.build(this);
	}
	
	public static class Flowing extends FluidAcid {

		@Override
		protected void fillStateContainer(Builder<Fluid, IFluidState> builder) {
			super.fillStateContainer(builder);
			builder.add(LEVEL_1_8);
		}
		
		@Override
		public boolean isSource(IFluidState state) {
			return false;
		}

		@Override
		public int getLevel(IFluidState state) {
			return state.get(FluidAcid.LEVEL_1_8);
		}
		
	}
	
	public static class Source extends FluidAcid {

		@Override
		public boolean isSource(IFluidState state) {
			return true;
		}

		@Override
		public int getLevel(IFluidState p_207192_1_) {
			return 8;
		}
		
	}
}
