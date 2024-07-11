package net.tintankgames.marvel.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;

public class StormbreakerModel extends Model {
    private final ModelPart root;

    public StormbreakerModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("stormbreaker", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 22.0F, 2.0F).texOffs(16, 8).addBox(-2.0F, -13.0F, -2.0F, 4.0F, 5.0F, 4.0F).texOffs(0, 25).addBox(-3.0F, -12.75F, -1.0F, 2.0F, 5.0F, 2.0F).texOffs(16, 0).addBox(1.0F, -12.5F, -1.5F, 5.0F, 4.0F, 3.0F).texOffs(14, 23).addBox(-5.0F, -13.75F, -1.0F, 2.0F, 7.0F, 2.0F).texOffs(24, 21).addBox(-7.0F, -14.75F, -1.0F, 2.0F, 9.0F, 2.0F).texOffs(9, 0).addBox(-8.0F, -13.75F, -1.0F, 1.0F, 7.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, Mth.PI, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    
    @Override
    public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_, int color) {
        root.render(p_103111_, p_103112_, p_103113_, p_103114_, color);
    }
}
