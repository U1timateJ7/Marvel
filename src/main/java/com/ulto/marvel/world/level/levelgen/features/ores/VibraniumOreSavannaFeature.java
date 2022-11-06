
package com.ulto.marvel.world.level.levelgen.features.ores;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.level.block.MarvelModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class VibraniumOreSavannaFeature extends OreFeature {

	public static UruOreFeature FEATURE = null;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Set<Supplier<Holder<PlacedFeature>>> PLACED_FEATURES = new HashSet<>();

	public static Feature<?> feature() {
		List<OreConfiguration.TargetBlockState> ORE_VIBRANIUM_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MarvelModBlocks.URU_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MarvelModBlocks.DEEPSLATE_URU_ORE.get().defaultBlockState()));

		FEATURE = new UruOreFeature();

		CONFIGURED_FEATURE = FeatureUtils.register(MarvelMod.MOD_ID + ":ore_vibranium_extra", FEATURE,
				new OreConfiguration(ORE_VIBRANIUM_TARGET_LIST, 8)
		);
		Holder<PlacedFeature> LOWER = PlacementUtils.register(MarvelMod.MOD_ID + ":ore_vibranium_extra", CONFIGURED_FEATURE, List.of(
				CountPlacement.of(10), InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(256)),
				BiomeFilter.biome()));
		PLACED_FEATURES.add(() -> LOWER);

		return FEATURE;
	}

	public static Set<Supplier<Holder<PlacedFeature>>> placedFeatures() {
		return PLACED_FEATURES;
	}

	public static final Set<ResourceLocation> GENERATE_BIOMES = getBiomes();

	private static Set<ResourceLocation> getBiomes() {
		Set<ResourceLocation> set = new HashSet<>();
		ForgeRegistries.BIOMES.tags().getTag(Tags.Biomes.IS_SAVANNA).forEach(biome -> set.add(ForgeRegistries.BIOMES.getKey(biome)));
		return set;
	}

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public VibraniumOreSavannaFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		return super.place(context);
	}
}
