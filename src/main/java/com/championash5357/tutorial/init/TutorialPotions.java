package com.championash5357.tutorial.init;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.potion.PotionRecipe;

import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TutorialPotions {

	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTION_TYPES, Tutorial.MOD_ID);
	
	public static final RegistryObject<Potion> ODD = POTIONS.register("odd", () -> new Potion(new EffectInstance(TutorialEffects.ODD.get(), 3600)));
	public static final RegistryObject<Potion> LONG_ODD = POTIONS.register("long_odd", () -> new Potion(new EffectInstance(TutorialEffects.ODD.get(), 9600)));
	public static final RegistryObject<Potion> SHORT_ODD = POTIONS.register("short_odd", () -> new Potion(new EffectInstance(TutorialEffects.ODD.get(), 1200)));
	
	/*private static Method brewing;
	
	private static void addMix(Potion base, Item ingredient, Potion result) {
		if(brewing == null) {
			brewing = ObfuscationReflectionHelper.findMethod(PotionBrewing.class, "addMix", Potion.class, Item.class, Potion.class);
			brewing.setAccessible(true);
		}
		
		try {
			brewing.invoke(null, base, ingredient, result);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}*/
	
	public static void addRecipes() {
		BrewingRecipeRegistry.addRecipe(new PotionRecipe(Lazy.concurrentOf(() -> Potions.AWKWARD), Ingredient.fromItems(Items.CHARCOAL), Lazy.concurrentOf(() -> ODD.get())));
		BrewingRecipeRegistry.addRecipe(new PotionRecipe(Lazy.concurrentOf(() -> ODD.get()), Ingredient.fromItems(Items.REDSTONE), Lazy.concurrentOf(() -> LONG_ODD.get())));
		BrewingRecipeRegistry.addRecipe(new PotionRecipe(Lazy.concurrentOf(() -> ODD.get()), Ingredient.fromItems(Items.FEATHER), Lazy.concurrentOf(() -> SHORT_ODD.get())));
		/*addMix(Potions.AWKWARD, Items.CHARCOAL, ODD);
		addMix(ODD, Items.REDSTONE, LONG_ODD);
		addMix(ODD, Items.FEATHER, SHORT_ODD);*/
	}
}
