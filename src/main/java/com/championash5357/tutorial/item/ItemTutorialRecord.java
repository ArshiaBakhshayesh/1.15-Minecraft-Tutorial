package com.championash5357.tutorial.item;

import com.championash5357.tutorial.Tutorial;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.SoundEvent;

public class ItemTutorialRecord extends MusicDiscItem {

	public ItemTutorialRecord(int comparatorValueIn, SoundEvent soundIn, Properties builder, String registry) {
		super(comparatorValueIn, soundIn, builder);
		this.setRegistryName(Tutorial.MOD_ID, registry);
	}
}
