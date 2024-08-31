package net.tintankgames.marvel.mixin;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.data.MarvelStructureSets;
import net.tintankgames.marvel.data.worldgen.MarvelPools;
import net.tintankgames.marvel.data.worldgen.features.MarvelConfiguredFeatures;
import net.tintankgames.marvel.data.worldgen.placements.MarvelPlacements;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.HydraAgentSkins;
import net.tintankgames.marvel.world.entity.HydraAgentVariants;
import net.tintankgames.marvel.world.level.block.entity.MarvelBannerPatterns;
import net.tintankgames.marvel.world.level.levelgen.structure.MarvelStructures;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VanillaRegistries.class)
public abstract class VanillaRegistriesMixin {
    @Shadow @Final private static RegistrySetBuilder BUILDER;

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/core/RegistrySetBuilder;getEntryKeys()Ljava/util/List;", shift = At.Shift.BEFORE), method = "<clinit>")
    private static void marvelBootstrap(CallbackInfo ci) {
        if (DatagenModLoader.isRunningDataGen()) {
            BUILDER.add(MarvelRegistries.HYDRA_AGENT_VARIANT, HydraAgentVariants::bootstrap);
            BUILDER.add(MarvelRegistries.HYDRA_AGENT_SKIN, HydraAgentSkins::bootstrap);
        }
    }

    @Mixin(FeatureUtils.class)
    public static class FeatureUtilsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelConfiguredFeatures.bootstrap(bootstrapContext);
        }
    }

    @Mixin(PlacementUtils.class)
    public static class PlacementUtilsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<PlacedFeature> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelPlacements.bootstrap(bootstrapContext);
        }
    }

    @Mixin(Structures.class)
    public static class StructuresMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<Structure> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelStructures.bootstrap(bootstrapContext);
        }
    }

    @Mixin(StructureSets.class)
    public interface StructureSetsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<StructureSet> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelStructureSets.bootstrap(bootstrapContext);
        }
    }

    @Mixin(Pools.class)
    public static class PoolsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<StructureTemplatePool> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelPools.bootstrap(bootstrapContext);
        }
    }

    @Mixin(DamageTypes.class)
    public interface DamageTypesMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<DamageType> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelDamageTypes.bootstrap(bootstrapContext);
        }
    }

    @Mixin(BannerPatterns.class)
    public static class BannerPatternsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void marvelBootstrap(BootstrapContext<BannerPattern> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelBannerPatterns.bootstrap(bootstrapContext);
        }
    }
}
