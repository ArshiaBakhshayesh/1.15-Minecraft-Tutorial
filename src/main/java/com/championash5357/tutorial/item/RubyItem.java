package com.championash5357.tutorial.item;

import java.util.List;

import com.championash5357.tutorial.Tutorial;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class RubyItem extends Item {

	public RubyItem() {
		super(new Properties().maxStackSize(32).group(Tutorial.TUTORIAL_TAB).rarity(Rarity.UNCOMMON));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add((new StringTextComponent("" + TextFormatting.RED)).appendSibling(new TranslationTextComponent("information." + this.getTranslationKey())));
		//tooltip.add(new StringTextComponent(TextFormatting.RED + "A shiny more valuable than emerald."));
	}
}
