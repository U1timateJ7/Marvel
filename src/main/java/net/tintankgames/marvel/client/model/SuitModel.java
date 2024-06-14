package net.tintankgames.marvel.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SuitModel<T extends LivingEntity> extends HumanoidModel<T> {
    public SuitModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51f)), PartPose.offset(0.0F, 0.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createPantherBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01f))
                    .texOffs(24, 0).addBox(-4.0F, -9.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 0).addBox(3.0F, -9.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51f)), PartPose.offset(0.0F, 0.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f))
                    .texOffs(24, 4).addBox(1.0F, 10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(2.0F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(0.0F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(2.0F, 10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f))
                    .texOffs(24, 4).addBox(-1.0F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(-3.0F, 10.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(-3.0F, 10.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
                    .texOffs(24, 4).addBox(-2.0F, 10.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createWolverineBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(33, 9).addBox(-1.2F, -4.0F, -0.5F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(4.1F, -4.0F, -2.8F, 0.0F, -1.0908F, 0.0F));
            head.addOrReplaceChild("mask2", CubeListBuilder.create().texOffs(33, 9).mirror().addBox(-6.5F, -4.0F, -0.9F, 7.0F, 6.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(-3.4F, -4.0F, -3.2F, 0.0F, 1.0908F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).texOffs(46, 1).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
            right_arm.addOrReplaceChild("right_claws", CubeListBuilder.create().texOffs(55, 55).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)).texOffs(55, 55).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)).texOffs(55, 55).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offset(-2.5F, 11.5F, -1.0F));
            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror().texOffs(46, 1).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
            left_arm.addOrReplaceChild("left_claws", CubeListBuilder.create().texOffs(55, 55).mirror().addBox(-1.0F, -3.0F, 3.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)).texOffs(55, 55).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)).texOffs(55, 55).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(2.5F, 11.5F, -2.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createCyclopsBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01f)).texOffs(24, 0).addBox(-4.0F, -5.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createSpiderManBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51f)), PartPose.offset(0.0F, 0.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
