package net.tintankgames.marvel.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class SuitModel<T extends LivingEntity> extends HumanoidModel<T> {
    public final ModelPart root;

    public SuitModel(ModelPart root) {
        super(root);
        this.root = root;
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
            head.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(33, 9).addBox(-1.2F, -4.0F, -0.5F, 7.0F, 6.0F, 0.0F), PartPose.offsetAndRotation(4.1F, -4.0F, -2.8F, 0.0F, -1.0908F, 0.0F));
            head.addOrReplaceChild("mask2", CubeListBuilder.create().texOffs(33, 9).mirror().addBox(-6.5F, -4.0F, -0.9F, 7.0F, 6.0F, 0.0F).mirror(false), PartPose.offsetAndRotation(-3.4F, -4.0F, -3.2F, 0.0F, 1.0908F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).texOffs(46, 1).addBox(-4.0F, -2.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
            right_arm.addOrReplaceChild("right_claws", CubeListBuilder.create().texOffs(55, 55).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 7.0F, 0.0F).texOffs(55, 55).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 7.0F, 0.0F).texOffs(55, 55).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F), PartPose.offset(-2.5F, 11.5F, -1.0F));
            PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01F)).mirror().texOffs(46, 1).addBox(-1.0F, -2.0F, -2.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));
            left_arm.addOrReplaceChild("left_claws", CubeListBuilder.create().texOffs(55, 55).mirror().addBox(-1.0F, -3.0F, 3.0F, 2.0F, 7.0F, 0.0F).texOffs(55, 55).addBox(-1.0F, -3.0F, 2.0F, 2.0F, 7.0F, 0.0F).texOffs(55, 55).addBox(-1.0F, -3.0F, 1.0F, 2.0F, 7.0F, 0.0F).mirror(false), PartPose.offset(2.5F, 11.5F, -2.0F));
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

    public static LayerDefinition createWaspBodyLayer(ArmorItem.Type... types) {
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
            partdefinition.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(32, 32).mirror().addBox(0.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F).mirror(false).texOffs(0, 32).mirror().addBox(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 0.0F).mirror(false), PartPose.offsetAndRotation(2.0F, 5.0F, 2.0F, 0.0F, -0.2618F, 0.0F));
            partdefinition.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(32, 32).addBox(-16.0F, -16.0F, 0.0F, 16.0F, 16.0F, 0.0F).texOffs(0, 32).addBox(-16.0F, 0.0F, 0.0F, 16.0F, 16.0F, 0.0F), PartPose.offsetAndRotation(-2.0F, 5.0F, 2.0F, 0.0F, 0.2618F, 0.0F));
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
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(48, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.02F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createThorBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.16f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            head.addOrReplaceChild("helmet1", CubeListBuilder.create().texOffs(32, -9).addBox(0.0F, -9.0F, 0.0F, 0.0F, 9.0F, 9.0F), PartPose.offsetAndRotation(-4.2F, -3.0F, -2.0F, 0.0F, -0.2618F, 0.0F));
            head.addOrReplaceChild("helmet2", CubeListBuilder.create().texOffs(32, -9).addBox(0.0F, -9.0F, 0.0F, 0.0F, 9.0F, 9.0F), PartPose.offsetAndRotation(4.2F, -3.0F, -2.0F, 0.0F, 0.2618F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)).texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.01f)).texOffs(0, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
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

    public static LayerDefinition createIronManMark1BodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.15F)).texOffs(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.15F)).texOffs(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).texOffs(0, 0).addBox(-6.0F, 4.75F, -1.0F, 2.0F, 5.0F, 2.0F).texOffs(0, 17).addBox(-5.5F, 9.75F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(13, 17).addBox(-4.0F, 5.75F, -1.0F, 1.0F, 1.0F, 2.0F).texOffs(13, 17).addBox(-4.0F, 7.75F, -1.0F, 1.0F, 1.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.15F)).texOffs(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)), PartPose.offset(5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.15F)).texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).texOffs(56, 32).addBox(-4.25F, 0.8F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(-1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.15F)).texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.4F)).texOffs(56, 40).addBox(2.25F, 0.8F, -1.0F, 2.0F, 6.0F, 2.0F), PartPose.offset(1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(48, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.45F)), PartPose.offset(1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createIronManBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.26f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51f)), PartPose.offset(0.0F, 0.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.27f)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26f)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.265f)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51f)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51f)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createWarMachineBodyLayer(ArmorItem.Type... types) {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        List<ArmorItem.Type> armorTypes = Arrays.asList(types);
        if (armorTypes.contains(ArmorItem.Type.HELMET)) {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.26F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.51F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.CHESTPLATE)) {
            PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.27F)).texOffs(19, 19).addBox(-4.0F, 0.0F, -2.75F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(18, 32).addBox(-4.0F, 0.0F, 1.75F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.25F)).texOffs(56, 35).addBox(-3.1F, 1.0F, 2.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.26F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            PartDefinition gun = body.addOrReplaceChild("gun", CubeListBuilder.create().texOffs(15, 17).addBox(-6.55F, -3.1F, 3.1F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));
            gun.addOrReplaceChild("gun2", CubeListBuilder.create().texOffs(45, 34).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 6.0F, 1.0F), PartPose.offsetAndRotation(-4.0F, 2.0F, 3.6F, 0.0F, 0.0F, -0.5672F));
            gun.addOrReplaceChild("gun3", CubeListBuilder.create().texOffs(45, 39).addBox(-0.9791F, -0.9F, -5.0456F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.01F)).texOffs(36, 37).addBox(-0.4791F, -0.4F, -11.0456F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.01F)).texOffs(37, 35).addBox(-0.9791F, -0.9F, -7.0456F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.01F)).texOffs(37, 35).addBox(-0.9791F, -0.9F, -9.0456F, 2.0F, 2.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(-6.1209F, -4.25F, 3.6456F, 0.0F, 0.3491F, 0.0F));
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(5.0F, 2.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.265F)), PartPose.offset(-5.0F, 2.0F, 0.0F));
        } else {
            partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        if (armorTypes.contains(ArmorItem.Type.BOOTS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(1.9F, 12.0F, 0.0F));
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.51F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
        } else if (!armorTypes.contains(ArmorItem.Type.LEGGINGS)) {
            partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);
            partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        }
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        List<ModelPart> parts = new ArrayList<>(List.of(this.body, this.rightArm, this.leftArm, this.rightLeg, this.leftLeg, this.hat));
        if (root.hasChild("left_wing") && root.hasChild("right_wing")) {
            parts.add(root.getChild("left_wing"));
            parts.add(root.getChild("right_wing"));
        }
        return ImmutableList.copyOf(parts);
    }
}
