
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package com.ulto.marvel.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.ArrayList;

import com.ulto.marvel.block.entity.MjolnirBlockBlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage7BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage6BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage5BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage4BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage3BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage2BlockEntity;
import com.ulto.marvel.block.entity.HeartShapedHerbStage1BlockEntity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModBlockEntities {
	private static final List<BlockEntityType<?>> REGISTRY = new ArrayList<>();
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_1 = register("marvel:heart_shaped_herb_stage_1",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_1, HeartShapedHerbStage1BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_2 = register("marvel:heart_shaped_herb_stage_2",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_2, HeartShapedHerbStage2BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_3 = register("marvel:heart_shaped_herb_stage_3",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_3, HeartShapedHerbStage3BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_4 = register("marvel:heart_shaped_herb_stage_4",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_4, HeartShapedHerbStage4BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_5 = register("marvel:heart_shaped_herb_stage_5",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_5, HeartShapedHerbStage5BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_6 = register("marvel:heart_shaped_herb_stage_6",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_6, HeartShapedHerbStage6BlockEntity::new);
	public static final BlockEntityType<?> HEART_SHAPED_HERB_STAGE_7 = register("marvel:heart_shaped_herb_stage_7",
			MarvelModBlocks.HEART_SHAPED_HERB_STAGE_7, HeartShapedHerbStage7BlockEntity::new);
	public static final BlockEntityType<?> MJOLNIR_BLOCK = register("marvel:mjolnir_block", MarvelModBlocks.MJOLNIR_BLOCK,
			MjolnirBlockBlockEntity::new);

	private static BlockEntityType<?> register(String registryname, Block block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		BlockEntityType<?> blockEntityType = BlockEntityType.Builder.of(supplier, block).build(null).setRegistryName(registryname);
		REGISTRY.add(blockEntityType);
		return blockEntityType;
	}

	@SubscribeEvent
	public static void registerTileEntity(RegistryEvent.Register<BlockEntityType<?>> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new BlockEntityType[0]));
	}
}
