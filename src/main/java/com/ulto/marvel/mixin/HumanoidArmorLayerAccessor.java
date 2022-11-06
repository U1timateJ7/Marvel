package com.ulto.marvel.mixin;

import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(HumanoidArmorLayer.class)
public interface HumanoidArmorLayerAccessor {
    @Accessor("ARMOR_LOCATION_CACHE")
    static Map<String, ResourceLocation> getArmorLocationCache() {
        throw new AssertionError();
    }
}
