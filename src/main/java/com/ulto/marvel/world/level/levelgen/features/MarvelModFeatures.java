package com.ulto.marvel.world.level.levelgen.features;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.world.level.levelgen.features.ores.*;
import com.ulto.marvel.world.level.levelgen.features.plants.HeartShapedHerbFeature;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class MarvelModFeatures {
	public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, MarvelMod.MOD_ID);

	static {
		REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	private static final List<FeatureRegistration> FEATURE_REGISTRATIONS = new ArrayList<>();

	public static final RegistryObject<Feature<?>> ORE_VIBRANIUM =
			register("ore_vibranium", VibraniumOreFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, VibraniumOreFeature.GENERATE_BIOMES, VibraniumOreFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> ORE_VIBRANIUM_EXTRA =
			register("ore_vibranium_extra", VibraniumOreSavannaFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, VibraniumOreSavannaFeature.GENERATE_BIOMES, VibraniumOreSavannaFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> ORE_TITANIUM =
			register("ore_titanium", TitaniumOreFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, TitaniumOreFeature.GENERATE_BIOMES, TitaniumOreFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> ORE_PALLADIUM =
			register("ore_palladium", PalladiumOreFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, PalladiumOreFeature.GENERATE_BIOMES, PalladiumOreFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> ORE_URU =
			register("ore_uru_lower", UruOreFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, UruOreFeature.GENERATE_BIOMES, UruOreFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> ORE_URU_MOUNTAINS =
			register("ore_uru", UruOreMountainsFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.UNDERGROUND_ORES, UruOreMountainsFeature.GENERATE_BIOMES, UruOreMountainsFeature.placedFeatures()));
	public static final RegistryObject<Feature<?>> HEART_SHAPED_HERB =
			register("heart_shaped_herb", HeartShapedHerbFeature::feature,
					new FeatureRegistration(GenerationStep.Decoration.VEGETAL_DECORATION, HeartShapedHerbFeature.GENERATE_BIOMES, HeartShapedHerbFeature.placedFeatures()));

	private static RegistryObject<Feature<?>> register(String registryname, Supplier<Feature<?>> feature, FeatureRegistration featureRegistration) {
		FEATURE_REGISTRATIONS.add(featureRegistration);
		return REGISTRY.register(registryname, feature);
	}

	@SubscribeEvent public static void addFeaturesToBiomes(BiomeLoadingEvent event) {
		for (FeatureRegistration registration : FEATURE_REGISTRATIONS) {
			for (Supplier<Holder<PlacedFeature>> placedFeature : registration.placedFeature())
				if (registration.biomes() == null || registration.biomes().contains(event.getName()))
					event.getGeneration().getFeatures(registration.stage()).add(placedFeature.get());
		}
	}

	private record FeatureRegistration (GenerationStep.Decoration stage, Set<ResourceLocation> biomes, Set<Supplier<Holder<PlacedFeature>>> placedFeature) {}
}
