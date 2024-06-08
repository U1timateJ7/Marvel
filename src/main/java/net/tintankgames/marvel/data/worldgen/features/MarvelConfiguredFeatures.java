package net.tintankgames.marvel.data.worldgen.features;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.List;

public class MarvelConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_VIBRANIUM = create("ore_vibranium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_VIBRANIUM_BURIED = create("ore_vibranium_buried");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_VIBRANIUM_EXTRA = create("ore_vibranium_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_TITANIUM = create("ore_titanium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_PALLADIUM = create("ore_palladium");

    private static ResourceKey<ConfiguredFeature<?, ?>> create(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, MarvelSuperheroes.id(id));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder.Reference<ConfiguredFeature<?, ?>> register(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        return bootstrapContext.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext) {
        RuleTest stoneOreReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateOreReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> vibraniumOres = List.of(
                OreConfiguration.target(stoneOreReplaceables, MarvelBlocks.VIBRANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, MarvelBlocks.DEEPSLATE_VIBRANIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> titaniumOres = List.of(
                OreConfiguration.target(stoneOreReplaceables, MarvelBlocks.TITANIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, MarvelBlocks.DEEPSLATE_TITANIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> palladiumOres = List.of(
                OreConfiguration.target(stoneOreReplaceables, MarvelBlocks.PALLADIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateOreReplaceables, MarvelBlocks.DEEPSLATE_PALLADIUM_ORE.get().defaultBlockState())
        );
        register(bootstrapContext, ORE_VIBRANIUM, Feature.ORE, new OreConfiguration(vibraniumOres, 4, 0.7F));
        register(bootstrapContext, ORE_VIBRANIUM_BURIED, Feature.ORE, new OreConfiguration(vibraniumOres, 4, 1.0F));
        register(bootstrapContext, ORE_VIBRANIUM_EXTRA, Feature.ORE, new OreConfiguration(vibraniumOres, 4));
        register(bootstrapContext, ORE_TITANIUM, Feature.ORE, new OreConfiguration(titaniumOres, 9));
        register(bootstrapContext, ORE_PALLADIUM, Feature.ORE, new OreConfiguration(palladiumOres, 8));
    }
}
