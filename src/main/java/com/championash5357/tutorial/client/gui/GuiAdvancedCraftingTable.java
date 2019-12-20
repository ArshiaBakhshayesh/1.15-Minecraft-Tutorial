package com.championash5357.tutorial.client.gui;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.inventory.container.ContainerAdvancedCraftingTable;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiAdvancedCraftingTable extends ContainerScreen<ContainerAdvancedCraftingTable> {

	private static final ResourceLocation GUI = new ResourceLocation(Tutorial.MOD_ID, "textures/gui/container/advanced_crafting_table.png");
	private static final ResourceLocation INVENTORY = new ResourceLocation(Tutorial.MOD_ID, "textures/gui/container/separated_inventory.png");
	
	public GuiAdvancedCraftingTable(ContainerAdvancedCraftingTable table, PlayerInventory player, ITextComponent title) {
		super(table, player, title);
		this.xSize = 188;
		this.ySize = 206;
	}
	
	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		this.renderBackground();
		super.render(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI);
		this.blit(this.guiLeft, this.guiTop, 0, 0, 188, 110);
		this.minecraft.getTextureManager().bindTexture(INVENTORY);
		this.blit(this.guiLeft + 6, this.guiTop + 116, 0, 0, 176, 90);
	}

}
