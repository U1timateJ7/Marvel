package net.tintankgames.marvel.mixin;

import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import net.tintankgames.marvel.data.worldgen.features.MarvelConfiguredFeatures;
import net.tintankgames.marvel.data.worldgen.placements.MarvelPlacements;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VanillaRegistries.class)
public abstract class VanillaRegistriesMixin {
    @Mixin(FeatureUtils.class)
    public static class FeatureUtilsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void multiverseBootstrap(BootstrapContext<ConfiguredFeature<?, ?>> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelConfiguredFeatures.bootstrap(bootstrapContext);
        }
    }

    @Mixin(PlacementUtils.class)
    public static class PlacementUtilsMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void multiverseBootstrap(BootstrapContext<PlacedFeature> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelPlacements.bootstrap(bootstrapContext);
        }
    }

    @Mixin(DamageTypes.class)
    public interface DamageTypesMixin {
        @Inject(at = @At("RETURN"), method = "bootstrap")
        private static void multiverseBootstrap(BootstrapContext<DamageType> bootstrapContext, CallbackInfo ci) {
            if (DatagenModLoader.isRunningDataGen()) MarvelDamageTypes.bootstrap(bootstrapContext);
        }
    }
}
