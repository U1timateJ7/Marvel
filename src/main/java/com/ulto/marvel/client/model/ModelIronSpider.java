package com.ulto.marvel.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.0.1
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports
public class ModelIronSpider<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in
	// the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "model_iron_spider"), "main");
	public final ModelPart Body;
	public final ModelPart RightArm;
	public final ModelPart LeftArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	public final ModelPart RightBoot;
	public final ModelPart LeftBoot;

	public ModelIronSpider(ModelPart root) {
		this.Body = root.getChild("Body");
		this.RightArm = root.getChild("RightArm");
		this.LeftArm = root.getChild("LeftArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
		this.RightBoot = root.getChild("RightBoot");
		this.LeftBoot = root.getChild("LeftBoot");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition Body = partdefinition.addOrReplaceChild("Body",
				CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.52F)),
				PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition spiderarm1 = Body.addOrReplaceChild("spiderarm1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-2.75F, -0.7F, 2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.4F, 4.0F, 0.0F, 0.0F, 0.0F, -0.0873F));
		PartDefinition part1 = spiderarm1.addOrReplaceChild("part1",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.25F, -2.45F, 6.0F, 0.6545F, 0.0F, -0.3491F));
		PartDefinition part2 = spiderarm1.addOrReplaceChild("part2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.65F, -9.0F, 10.0F, 1.7453F, 0.0F, -0.3491F));
		PartDefinition part3 = spiderarm1.addOrReplaceChild("part3",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.8F, -14.95F, 5.5F, 2.9234F, 0.0F, -0.3491F));
		PartDefinition part4 = spiderarm1.addOrReplaceChild("part4",
				CubeListBuilder.create().texOffs(3, 3).addBox(-1.25F, 1.45F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.15F)),
				PartPose.offsetAndRotation(-6.8F, -14.95F, 5.5F, -2.8362F, 0.0F, -0.3491F));
		PartDefinition spiderarm4 = Body.addOrReplaceChild("spiderarm4", CubeListBuilder.create(),
				PartPose.offsetAndRotation(-3.1F, 5.0F, 0.0F, 0.0F, 0.0F, -2.2253F));
		PartDefinition bone3 = spiderarm4.addOrReplaceChild("bone3",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(-2.25F, -0.2F, 2.5F, 0.0F, 0.0F, 0.3491F));
		PartDefinition part13 = spiderarm4.addOrReplaceChild("part13",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.25F, -2.45F, 6.0F, 0.6545F, 0.0F, -0.3491F));
		PartDefinition part14 = spiderarm4.addOrReplaceChild("part14",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.65F, -9.0F, 10.0F, 1.7453F, 0.0F, -0.3491F));
		PartDefinition part15 = spiderarm4.addOrReplaceChild("part15",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-6.8F, -14.95F, 5.5F, 2.9234F, 0.0F, -0.3491F));
		PartDefinition part16 = spiderarm4.addOrReplaceChild("part16",
				CubeListBuilder.create().texOffs(3, 3).addBox(-1.25F, 1.45F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.15F)),
				PartPose.offsetAndRotation(-6.8F, -14.95F, 5.5F, -2.8362F, 0.0F, -0.3491F));
		PartDefinition spiderarm2 = Body.addOrReplaceChild("spiderarm2", CubeListBuilder.create(),
				PartPose.offsetAndRotation(1.75F, 3.96F, 0.5F, 0.0F, 0.0F, 0.829F));
		PartDefinition bone = spiderarm2.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, -0.16F, 2.0F, 0.0F, 0.0F, -0.7418F));
		PartDefinition part5 = spiderarm2.addOrReplaceChild("part5",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.41F, 5.5F, 0.6545F, 0.0F, -0.3491F));
		PartDefinition part6 = spiderarm2.addOrReplaceChild("part6",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.4F, -8.96F, 9.5F, 1.7453F, 0.0F, -0.3491F));
		PartDefinition part7 = spiderarm2.addOrReplaceChild("part7",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.55F, -14.91F, 5.0F, 2.9234F, 0.0F, -0.3491F));
		PartDefinition part8 = spiderarm2.addOrReplaceChild("part8",
				CubeListBuilder.create().texOffs(3, 3).addBox(-1.25F, 1.45F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.15F)),
				PartPose.offsetAndRotation(-4.55F, -14.91F, 5.0F, -2.8362F, 0.0F, -0.3491F));
		PartDefinition spiderarm3 = Body.addOrReplaceChild("spiderarm3", CubeListBuilder.create(),
				PartPose.offsetAndRotation(1.65F, 6.86F, 0.5F, 0.0F, 0.0F, 2.8362F));
		PartDefinition bone2 = spiderarm3.addOrReplaceChild("bone2",
				CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.25F)),
				PartPose.offsetAndRotation(0.0F, -0.16F, 2.0F, 0.0F, 0.0F, -1.0472F));
		PartDefinition part9 = spiderarm3.addOrReplaceChild("part9",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.41F, 5.5F, 0.6545F, 0.0F, -0.3491F));
		PartDefinition part10 = spiderarm3.addOrReplaceChild("part10",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-2.4F, -8.96F, 9.5F, 1.7453F, 0.0F, -0.3491F));
		PartDefinition part11 = spiderarm3.addOrReplaceChild("part11",
				CubeListBuilder.create().texOffs(0, 0).addBox(-1.25F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.55F, -14.91F, 5.0F, 2.9234F, 0.0F, -0.3491F));
		PartDefinition part12 = spiderarm3.addOrReplaceChild("part12",
				CubeListBuilder.create().texOffs(3, 3).addBox(-1.25F, 1.45F, 3.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(-0.15F)),
				PartPose.offsetAndRotation(-4.55F, -14.91F, 5.0F, -2.8362F, 0.0F, -0.3491F));
		PartDefinition RightArm = partdefinition.addOrReplaceChild("RightArm",
				CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)),
				PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition LeftArm = partdefinition.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(40, 16).mirror()
				.addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg",
				CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 16).mirror()
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
		PartDefinition RightBoot = partdefinition.addOrReplaceChild("RightBoot",
				CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.52F)),
				PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition LeftBoot = partdefinition.addOrReplaceChild("LeftBoot", CubeListBuilder.create().texOffs(0, 16).mirror()
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.52F)).mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue,
			float alpha) {
		Body.render(poseStack, buffer, packedLight, packedOverlay);
		RightArm.render(poseStack, buffer, packedLight, packedOverlay);
		LeftArm.render(poseStack, buffer, packedLight, packedOverlay);
		RightLeg.render(poseStack, buffer, packedLight, packedOverlay);
		LeftLeg.render(poseStack, buffer, packedLight, packedOverlay);
		RightBoot.render(poseStack, buffer, packedLight, packedOverlay);
		LeftBoot.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}
