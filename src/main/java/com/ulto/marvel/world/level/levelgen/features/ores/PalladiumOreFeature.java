
package com.ulto.marvel.world.level.levelgen.features.ores;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.level.block.MarvelModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.OreFeature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

public class PalladiumOreFeature extends OreFeature {

	public static PalladiumOreFeature FEATURE = null;
	public static Holder<ConfiguredFeature<OreConfiguration, ?>> CONFIGURED_FEATURE = null;
	public static Set<Supplier<Holder<PlacedFeature>>> PLACED_FEATURES = new HashSet<>();

	public static Feature<?> feature() {
		List<OreConfiguration.TargetBlockState> ORE_PALLADIUM_TARGET_LIST = List.of(OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, MarvelModBlocks.PALLADIUM_ORE.get().defaultBlockState()), OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, MarvelModBlocks.DEEPSLATE_PALLADIUM_ORE.get().defaultBlockState()));

		FEATURE = new PalladiumOreFeature();

		CONFIGURED_FEATURE = FeatureUtils.register(MarvelMod.MOD_ID + ":ore_palladium_buried", FEATURE,
				new OreConfiguration(ORE_PALLADIUM_TARGET_LIST, 9, 0.5f)
		);
		Holder<PlacedFeature> MIDDLE = PlacementUtils.register(MarvelMod.MOD_ID + ":ore_palladium", CONFIGURED_FEATURE, List.of(
				CountPlacement.of(10), InSquarePlacement.spread(),
				HeightRangePlacement.triangle(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(32)),
				BiomeFilter.biome()));
		PLACED_FEATURES.add(() -> MIDDLE);

		Holder<PlacedFeature> LOWER = PlacementUtils.register(MarvelMod.MOD_ID + ":ore_palladium_lower", CONFIGURED_FEATURE, List.of(
				CountPlacement.of(UniformInt.of(0, 1)), InSquarePlacement.spread(),
				HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(-48)),
				BiomeFilter.biome()));
		PLACED_FEATURES.add(() -> LOWER);

		return FEATURE;
	}

	public static Set<Supplier<Holder<PlacedFeature>>> placedFeatures() {
		return PLACED_FEATURES;
	}

	public static final Set<ResourceLocation> GENERATE_BIOMES = null;

	private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.OVERWORLD);

	public PalladiumOreFeature() {
		super(OreConfiguration.CODEC);
	}

	public boolean place(FeaturePlaceContext<OreConfiguration> context) {
		WorldGenLevel world = context.level();
		if (!generate_dimensions.contains(world.getLevel().dimension()))
			return false;
		return super.place(context);
	}
}
