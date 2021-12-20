
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import com.ulto.marvel.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModBlocks {
	private static final List<Block> REGISTRY = new ArrayList<>();
	public static final Block HEART_SHAPED_HERB = register(new HeartShapedHerbBlock());
	public static final Block VIBRANIUM_ORE = register(new VibraniumOreBlock());
	public static final Block VIBRANIUM_BLOCK = register(new VibraniumBlockBlock());
	public static final Block TITANIUM_ORE = register(new TitaniumOreBlock());
	public static final Block TITANIUM_BLOCK = register(new TitaniumBlockBlock());
	public static final Block PALLADIUM_ORE = register(new PalladiumOreBlock());
	public static final Block PALLADIUM_BLOCK = register(new PalladiumBlockBlock());
	public static final Block URU_ORE = register(new UruOreBlock());
	public static final Block URU_BLOCK = register(new UruBlockBlock());
	public static final Block HEART_SHAPED_HERB_STAGE_1 = register(new HeartShapedHerbStage1Block());
	public static final Block HEART_SHAPED_HERB_STAGE_2 = register(new HeartShapedHerbStage2Block());
	public static final Block HEART_SHAPED_HERB_STAGE_3 = register(new HeartShapedHerbStage3Block());
	public static final Block HEART_SHAPED_HERB_STAGE_4 = register(new HeartShapedHerbStage4Block());
	public static final Block HEART_SHAPED_HERB_STAGE_5 = register(new HeartShapedHerbStage5Block());
	public static final Block HEART_SHAPED_HERB_STAGE_6 = register(new HeartShapedHerbStage6Block());
	public static final Block HEART_SHAPED_HERB_STAGE_7 = register(new HeartShapedHerbStage7Block());
	public static final Block TEMPORARY_WEB = register(new TemporaryWebBlock());
	public static final Block MJOLNIR_BLOCK = register(new MjolnirBlockBlock());

	private static Block register(Block block) {
		REGISTRY.add(block);
		return block;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Block[0]));
	}

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			HeartShapedHerbBlock.registerRenderLayer();
			HeartShapedHerbStage1Block.registerRenderLayer();
			HeartShapedHerbStage2Block.registerRenderLayer();
			HeartShapedHerbStage3Block.registerRenderLayer();
			HeartShapedHerbStage4Block.registerRenderLayer();
			HeartShapedHerbStage5Block.registerRenderLayer();
			HeartShapedHerbStage6Block.registerRenderLayer();
			HeartShapedHerbStage7Block.registerRenderLayer();
			TemporaryWebBlock.registerRenderLayer();
			MjolnirBlockBlock.registerRenderLayer();
		}
	}
}
