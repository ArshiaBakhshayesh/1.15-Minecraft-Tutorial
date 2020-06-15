package com.championash5357.tutorial.potion;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.util.Lazy;

public class PotionRecipe implements IBrewingRecipe {

	@Nonnull private final Lazy<Potion> input;
	@Nonnull private final Ingredient reagent;
	@Nonnull private final Lazy<Potion> output;
	
	public PotionRecipe(final Lazy<Potion> input, final Ingredient reagent, final Lazy<Potion> output) {
		this.input = input;
		this.reagent = reagent;
		this.output = output;
	}
	
	public Potion getInput() {
		return input.get();
	}
	
	public Potion getOutput() {
		return output.get();
	}
	
	@Override
	public boolean isInput(ItemStack input) {
		return PotionUtils.getPotionFromItem(input).equals(getInput());
	}

	@Override
	public boolean isIngredient(ItemStack ingredient) {
		return this.reagent.test(ingredient);
	}

	@Override
	public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
		return isInput(input) && isIngredient(ingredient) ? PotionUtils.addPotionToItemStack(new ItemStack(Items.POTION), getOutput()) : ItemStack.EMPTY;
	}
}
