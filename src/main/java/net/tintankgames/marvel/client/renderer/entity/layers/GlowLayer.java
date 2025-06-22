package net.tintankgames.marvel.client.renderer.entity.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.tintankgames.marvel.client.renderer.MarvelRenderTypes;

import java.util.function.Predicate;

public class GlowLayer<T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private final ResourceLocation texture;
    private final Predicate<T> predicate;

    public GlowLayer(RenderLayerParent<T, M> parent, ResourceLocation texture, Predicate<T> predicate) {
        super(parent);
        this.texture = texture;
        this.predicate = predicate;
    }

    @Override
    public void render(PoseStack p_117349_, MultiBufferSource p_117350_, int p_117351_, T p_117352_, float p_117353_, float p_117354_, float p_117355_, float p_117356_, float p_117357_, float p_117358_) {
        if (predicate.test(p_117352_)) renderColoredEmissiveModel(getParentModel(), texture, p_117349_, p_117350_, p_117351_, p_117352_, -1);
    }

    public static <T extends LivingEntity> void renderColoredEmissiveModel(EntityModel<T> p_117377_, ResourceLocation p_117378_, PoseStack p_117379_, MultiBufferSource p_117380_, int p_117381_, T p_117382_, int p_350384_) {
        VertexConsumer vertexconsumer = p_117380_.getBuffer(MarvelRenderTypes.entityEmissive(p_117378_));
        p_117377_.renderToBuffer(p_117379_, vertexconsumer, p_117381_, LivingEntityRenderer.getOverlayCoords(p_117382_, 0.0F), p_350384_);
    }
}
