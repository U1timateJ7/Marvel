package net.tintankgames.marvel.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.world.entity.Veronica;

@OnlyIn(Dist.CLIENT)
public class VeronicaModel extends HierarchicalModel<Veronica> {
    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart dish;
    private final ModelPart bone;
    private final ModelPart rotatedSide;
    private final ModelPart wing1;
    private final ModelPart wing2;

    public VeronicaModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.dish = this.body.getChild("dish");
        this.bone = this.dish.getChild("bone");
        this.rotatedSide = this.dish.getChild("rotatedSide");
        this.wing1 = root.getChild("wing1");
        this.wing2 = root.getChild("wing2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -14.0F, -8.0F, 12.0F, 12.0F, 20.0F).texOffs(41, 94).addBox(-2.0F, -10.0F, 12.0F, 4.0F, 4.0F, 2.0F).texOffs(0, 40).addBox(-8.0F, -16.0F, -24.0F, 16.0F, 16.0F, 16.0F), PartPose.offset(0.0F, 0.0F, -2.0F));
        PartDefinition dish = body.addOrReplaceChild("dish", CubeListBuilder.create().texOffs(0, 93).addBox(-8.0F, -15.0F, -1.0F, 14.0F, 14.0F, 2.0F).texOffs(0, 113).mirror().addBox(5.0F, -15.0F, 1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.01F)).mirror(false).texOffs(0, 113).addBox(-8.0F, -15.0F, 1.0F, 1.0F, 14.0F, 1.0F, new CubeDeformation(0.01F)).texOffs(66, 63).addBox(-1.0F, -12.0F, 1.0F, 0.0F, 8.0F, 12.0F), PartPose.offset(1.0F, 0.0F, 15.0F));
        PartDefinition bone = dish.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(5.0F, 0.0F, -6.0F));
        bone.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(66, 63).addBox(0.0F, -4.0F, -4.0F, 0.0F, 8.0F, 12.0F), PartPose.offsetAndRotation(-6.0F, -8.0F, 11.0F, 0.0F, 0.0F, -1.5708F));
        dish.addOrReplaceChild("rotatedSide", CubeListBuilder.create().texOffs(0, 113).mirror().addBox(-0.5F, -7.0F, -0.5F, 1.0F, 14.0F, 1.0F).mirror(false).texOffs(0, 113).addBox(-13.5F, -7.0F, -0.5F, 1.0F, 14.0F, 1.0F), PartPose.offsetAndRotation(-1.0F, -1.5F, 1.5F, 0.0F, 0.0F, 1.5708F));
        partdefinition.addOrReplaceChild("wing1", CubeListBuilder.create().texOffs(0, 73).addBox(6.0F, -15.0F, -8.0F, 16.0F, 2.0F, 16.0F).texOffs(50, 114).addBox(22.0F, -15.0F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(22.0F, -15.0F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(4.0F, -14.0F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(4.0F, -14.0F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(40.0F, -16.0F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(40.0F, -16.0F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(0, 73).addBox(24.0F, -16.0F, -8.0F, 16.0F, 2.0F, 16.0F).texOffs(0, 73).addBox(42.0F, -17.0F, -8.0F, 16.0F, 2.0F, 16.0F), PartPose.offset(2.0F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("wing2", CubeListBuilder.create().texOffs(0, 73).addBox(-20.0F, -0.6667F, -8.0F, 16.0F, 2.0F, 16.0F).texOffs(50, 114).addBox(-4.0F, -0.6667F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(-4.0F, -0.6667F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(-22.0F, 0.3333F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(-22.0F, 0.3333F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(14.0F, -1.6667F, -6.0F, 2.0F, 2.0F, 2.0F).texOffs(50, 114).addBox(14.0F, -1.6667F, 4.0F, 2.0F, 2.0F, 2.0F).texOffs(0, 73).addBox(-2.0F, -1.6667F, -8.0F, 16.0F, 2.0F, 16.0F).texOffs(0, 73).addBox(16.0F, -2.6667F, -8.0F, 16.0F, 2.0F, 16.0F), PartPose.offsetAndRotation(-28.0F, -14.3333F, 0.0F, 0.0F, 3.1416F, 0.0F));
        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(Veronica p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }

    @Override
    public ModelPart root() {
        return root;
    }
}
