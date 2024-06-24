package net.tintankgames.marvel.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class MjolnirModel extends Model {
    private final ModelPart root;

    public MjolnirModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("mjolnir", CubeListBuilder.create().texOffs(0, 18).addBox(-0.5F, 2.5F, -0.5F, 1.0F, 8.0F, 1.0F).texOffs(0, 27).addBox(-0.5F, 10.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.1F)).texOffs(10, 12).addBox(-1.5F, -2.75F, -1.5F, 3.0F, 1.0F, 3.0F).texOffs(0, 0).addBox(-2.5F, -2.5F, -3.5F, 5.0F, 5.0F, 7.0F).texOffs(0, 12).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 1.0F).texOffs(0, 12).addBox(-2.0F, -2.0F, 3.0F, 4.0F, 4.0F, 1.0F).texOffs(17, 0).addBox(-1.5F, -1.5F, 3.25F, 3.0F, 3.0F, 1.0F).texOffs(17, 0).addBox(-1.5F, -1.5F, -4.25F, 3.0F, 3.0F, 1.0F), PartPose.offset(0.0F, 0.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    
    @Override
    public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_, float p_103115_, float p_103116_, float p_103117_, float p_103118_) {
        root.render(p_103111_, p_103112_, p_103113_, p_103114_, p_103115_, p_103116_, p_103117_, p_103118_);
    }
}
