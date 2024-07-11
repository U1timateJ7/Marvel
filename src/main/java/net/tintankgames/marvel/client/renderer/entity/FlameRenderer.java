package net.tintankgames.marvel.client.renderer.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.entity.projectile.Flame;

@OnlyIn(Dist.CLIENT)
public class FlameRenderer extends EntityRenderer<Flame> {
    public FlameRenderer(EntityRendererProvider.Context p_174420_) {
        super(p_174420_);
    }

    @Override
    public ResourceLocation getTextureLocation(Flame mjolnir) {
        return MarvelSuperheroes.id("textures/entity/flame.png");
    }
}
