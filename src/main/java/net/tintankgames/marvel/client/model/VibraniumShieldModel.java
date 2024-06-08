package net.tintankgames.marvel.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
public class VibraniumShieldModel extends Model {
    private final ModelPart root;

    public VibraniumShieldModel(ModelPart root) {
        this(RenderType::entitySolid, root);
    }

    public VibraniumShieldModel(Function<ResourceLocation, RenderType> renderType, ModelPart root) {
        super(renderType);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("shield", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -1.0F, -6.0F, 10.0F, 1.0F, 12.0F).texOffs(24, 15).addBox(5.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F).texOffs(0, 15).addBox(-6.0F, -1.0F, -5.0F, 1.0F, 1.0F, 10.0F).texOffs(0, 0).mirror().addBox(3.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F).mirror(false).texOffs(0, 0).addBox(-4.0F, -0.5F, -1.0F, 1.0F, 1.0F, 2.0F).texOffs(49, 6).addBox(-3.0F, 0.25F, -1.0F, 6.0F, 0.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, Mth.PI, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }

    @Override
    public void renderToBuffer(
            PoseStack p_103919_, VertexConsumer p_103920_, int p_103921_, int p_103922_, float p_103923_, float p_103924_, float p_103925_, float p_103926_
    ) {
        this.root.render(p_103919_, p_103920_, p_103921_, p_103922_, p_103923_, p_103924_, p_103925_, p_103926_);
    }
}
