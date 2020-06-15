package com.championash5357.tutorial.entity.passive;

import com.championash5357.tutorial.init.TutorialEntities;
import com.championash5357.tutorial.init.TutorialItems;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoatEntity extends AnimalEntity {
	
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.HAY_BLOCK, Items.GOLDEN_CARROT);
	
	public GoatEntity(EntityType<? extends GoatEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 2.5));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.3, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0, 0.9f));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0f));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0);
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
		this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0);
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SHEEP_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return damageSourceIn == DamageSource.ANVIL ? SoundEvents.ENTITY_ENDER_DRAGON_DEATH : SoundEvents.ENTITY_COW_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_CHICKEN_DEATH;
	}
	
	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_CAT_HISS, 0.1f, 0.1f);
	}
	
	@Override
	protected float getSoundPitch() {
		return 0.1f;
	}
	
	@Override
	protected float getSoundVolume() {
		return 0.3f;
	}
	
	@Override
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if(stack.isEmpty() && !player.abilities.isCreativeMode && !this.isChild() && this.getEntityWorld().getDayTime() > 13000 && this.getEntityWorld().getDayTime() < 14000 /*&& this.rand.nextFloat() < 0.1*/) {
			player.playSound(SoundEvents.ENTITY_COW_MILK, 1.0f, 0.1f);
			player.setHeldItem(hand, new ItemStack(TutorialItems.BROCCOLI.get()));
			return true;
		} else {
			return super.processInteract(player, hand);
		}
	}
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return TutorialEntities.GOAT.get().create(this.world);
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? sizeIn.height * 0.95f : 1.3f;
	}
}
