package com.championash5357.tutorial.client.gui;

import com.championash5357.tutorial.Tutorial;
import com.championash5357.tutorial.inventory.container.ContainerStorage;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class GuiStorage extends ContainerScreen<ContainerStorage> {

	private static final ResourceLocation INVENTORY = new ResourceLocation(Tutorial.MOD_ID, "textures/gui/container/storage_gui.png");
	
	public GuiStorage(ContainerStorage storage, PlayerInventory player, ITextComponent title) {
		super(storage, player, title);
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
		this.minecraft.getTextureManager().bindTexture(INVENTORY);
		this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

}
