package com.championash5357.tutorial.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class TutorialTab extends ItemGroup {

	public TutorialTab() {
		super("tutorial");
		this.setBackgroundImageName("tutorial.png");
	}

	@Override
	public ItemStack createIcon() {
		ItemStack stack = new ItemStack(TutorialItems.RUBY);
		stack.addEnchantment(Enchantment.getEnchantmentByID(0), 1);
		return stack;
	}

}
