package com.ulto.marvel.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class IronManCharger extends Model {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "iron_man_suit_charger"), "main");
	public final ModelPart base;
	private final ModelPart label;

	public IronManCharger(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.base = root.getChild("base");
		this.label = root.getChild("label");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("base", CubeListBuilder.create().texOffs(0, 0).addBox(-7.0F, -2.0F, -4.0F, 14.0F, 2.0F, 8.0F).texOffs(0, 13).addBox(-6.0F, -2.25F, -3.0F, 12.0F, 2.0F, 6.0F).texOffs(0, 24).addBox(-7.0F, -0.15F, -4.0F, 14.0F, 0.0F, 8.0F, new CubeDeformation(0.25F)), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
		partdefinition.addOrReplaceChild("label", CubeListBuilder.create().texOffs(36, 0).addBox(-3.0F, -1.5F, -4.01F, 6.0F, 1.0F, 0.0F), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		base.render(poseStack, buffer, packedLight, packedOverlay);
		label.render(poseStack, buffer, packedLight, packedOverlay);
	}

	public static class Battery25 extends Model {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "iron_man_suit_charger_battery_25"), "main");
		public final ModelPart battery25;

		public Battery25(ModelPart root) {
			super(RenderType::entityCutoutNoCull);
			this.battery25 = root.getChild("battery25");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();
			partdefinition.addOrReplaceChild("battery25", CubeListBuilder.create().texOffs(0, 0).addBox(-5.5F, -1.5F, -4.001F, 1.0F, 1.0F, 0.0F), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			battery25.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}

	public static class Battery50 extends Model {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "iron_man_suit_charger_battery_50"), "main");
		public final ModelPart battery50;

		public Battery50(ModelPart root) {
			super(RenderType::entityCutoutNoCull);
			this.battery50 = root.getChild("battery50");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();
			partdefinition.addOrReplaceChild("battery50", CubeListBuilder.create().texOffs(0, 1).addBox(-5.5F, -1.5F, -4.002F, 1.0F, 1.0F, 0.0F), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			battery50.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}

	public static class Battery75 extends Model {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "iron_man_suit_charger_battery_75"), "main");
		public final ModelPart battery75;

		public Battery75(ModelPart root) {
			super(RenderType::entityCutoutNoCull);
			this.battery75 = root.getChild("battery75");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();
			partdefinition.addOrReplaceChild("battery75", CubeListBuilder.create().texOffs(0, 2).addBox(-5.5F, -1.5F, -4.003F, 1.0F, 1.0F, 0.0F), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			battery75.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}

	public static class Battery100 extends Model {
		public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("marvel", "iron_man_suit_charger_battery_100"), "main");
		public final ModelPart battery100;

		public Battery100(ModelPart root) {
			super(RenderType::entityCutoutNoCull);
			this.battery100 = root.getChild("battery100");
		}

		public static LayerDefinition createBodyLayer() {
			MeshDefinition meshdefinition = new MeshDefinition();
			PartDefinition partdefinition = meshdefinition.getRoot();
			partdefinition.addOrReplaceChild("battery100", CubeListBuilder.create().texOffs(0, 3).addBox(-5.5F, -1.5F, -4.004F, 1.0F, 1.0F, 0.0F), PartPose.offsetAndRotation(8.0F, 0.0F, 8.0F, 180f, 0f, 0f));
			return LayerDefinition.create(meshdefinition, 64, 64);
		}

		@Override
		public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
			battery100.render(poseStack, buffer, packedLight, packedOverlay);
		}
	}
}