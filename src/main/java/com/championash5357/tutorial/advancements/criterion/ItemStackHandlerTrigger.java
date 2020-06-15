package com.championash5357.tutorial.advancements.criterion;

import java.util.Iterator;
import java.util.List;

import com.championash5357.tutorial.Tutorial;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.advancements.criterion.AbstractCriterionTrigger;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.ItemStackHandler;

public class ItemStackHandlerTrigger extends AbstractCriterionTrigger<ItemStackHandlerTrigger.Instance> {

	private final ResourceLocation id;
	
	public ItemStackHandlerTrigger(String id) {
		this.id = new ResourceLocation(Tutorial.MOD_ID, id);
	}
	
	@Override
	public ResourceLocation getId() {
		return id;
	}

	@Override
	public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
		JsonObject object = JSONUtils.getJsonObject(json, "slots", new JsonObject());
		MinMaxBounds.IntBound occupied = MinMaxBounds.IntBound.fromJson(object.get("occupied"));
		MinMaxBounds.IntBound full = MinMaxBounds.IntBound.fromJson(object.get("full"));
		MinMaxBounds.IntBound empty = MinMaxBounds.IntBound.fromJson(object.get("empty"));
		ItemPredicate[] items = ItemPredicate.deserializeArray(json.get("items"));
		return new Instance(occupied, full, empty, items);
	}
	
	public void trigger(ServerPlayerEntity player, ItemStackHandler inventory) {
		this.func_227070_a_(player.getAdvancements(), instance -> {return instance.test(inventory);});
	}
	
	public class Instance extends CriterionInstance {

		private final MinMaxBounds.IntBound occupied;
		private final MinMaxBounds.IntBound full;
		private final MinMaxBounds.IntBound empty;
		private final ItemPredicate[] items;
		
		public Instance(MinMaxBounds.IntBound occupied, MinMaxBounds.IntBound full, MinMaxBounds.IntBound empty, ItemPredicate[] items) {
			super(id);
			this.occupied = occupied;
			this.full = full;
			this.empty = empty;
			this.items = items;
		}

		@Override
		public JsonElement serialize() {
			JsonObject json = new JsonObject();
			if(!this.occupied.isUnbounded() || !this.full.isUnbounded() || !this.empty.isUnbounded()) {
				JsonObject json1 = new JsonObject();
				json1.add("occupied", this.occupied.serialize());
				json1.add("full", this.full.serialize());
				json1.add("empty", this.empty.serialize());
				json.add("slots", json1);
			}
			
			if(this.items.length > 0) {
				JsonArray array = new JsonArray();
				
				for(ItemPredicate item : this.items) array.add(item.serialize());
				json.add("items", array);
			}
			
			return json;
		}
		
		public boolean test(ItemStackHandler inventory) {
			int full = 0;
			int empty = 0;
			int occupied = 0;
			List<ItemPredicate> list = Lists.newArrayList(this.items);
			
			for(int i = 0; i < inventory.getSlots(); ++i) {
				ItemStack stack = inventory.getStackInSlot(i);
				if(stack.isEmpty()) ++empty;
				else {
					++occupied;
					if(stack.getCount() >= stack.getMaxStackSize()) ++full;
					
					Iterator<ItemPredicate> iterator = list.iterator();
					
					while(iterator.hasNext()) {
						ItemPredicate predicate = iterator.next();
						if(predicate.test(stack)) iterator.remove();
					}
				}
			}
			
			if(!this.full.test(full)) return false;
			else if (!this.empty.test(empty)) return false;
			else if (!this.occupied.test(occupied)) return false;
			else return list.isEmpty();
		}
	}
}
