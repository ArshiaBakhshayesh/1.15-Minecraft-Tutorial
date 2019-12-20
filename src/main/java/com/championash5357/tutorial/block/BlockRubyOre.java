package com.championash5357.tutorial.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

public class BlockRubyOre extends Block {

	public BlockRubyOre() {
		super(Properties.create(Material.ROCK, MaterialColor.RED).hardnessAndResistance(3.0F, 2.0F).lightValue(8).sound(SoundType.SLIME).slipperiness(.2F));
		this.setRegistryName("ruby_ore");
	}
	
	@Override
	public int getHarvestLevel(BlockState state) {
		return 2;
	}
	
	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.AXE;
	}
}
