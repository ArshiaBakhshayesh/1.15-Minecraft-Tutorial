package com.championash5357.tutorial.init;

import com.championash5357.tutorial.advancements.criterion.ItemStackHandlerTrigger;

import net.minecraft.advancements.CriteriaTriggers;

public class TutorialCriterions {

	public static final ItemStackHandlerTrigger STORAGE_CHANGED = new ItemStackHandlerTrigger("storage_changed");
	
	public static void register() {
		CriteriaTriggers.register(STORAGE_CHANGED);
	}
}
