package com.ulto.marvel.client.renderer.entity.layer;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.ulto.marvel.world.item.GlowingArmor;
import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class GlowingHumanoidArmorLayer<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> extends RenderLayer<T, M> {
    private static final Map<String, ResourceLocation> GLOW_ARMOR_LOCATION_CACHE = Maps.newHashMap();
    private final A innerModel;
    private final A outerModel;

    public GlowingHumanoidArmorLayer(RenderLayerParent<T, M> p_117075_, A p_117076_, A p_117077_) {
        super(p_117075_);
        this.innerModel = p_117076_;
        this.outerModel = p_117077_;
    }

    public void render(PoseStack p_117096_, MultiBufferSource p_117097_, int p_117098_, T p_117099_, float p_117100_, float p_117101_, float p_117102_, float p_117103_, float p_117104_, float p_117105_) {
        if (shouldRender(p_117099_)) {
            p_117096_.scale(1.0075f, 1.0075f, 1.0075f);
            p_117096_.translate(0,  -0.005, 0);
            this.renderArmorPiece(p_117096_, p_117097_, p_117098_, p_117099_, EquipmentSlot.CHEST, this.getArmorModel(EquipmentSlot.CHEST));
            p_117096_.scale(1.002f, 1.002f, 1.002f);
            p_117096_.translate(0,  -0.005, 0);
            this.renderArmorPiece(p_117096_, p_117097_, p_117098_, p_117099_, EquipmentSlot.LEGS, this.getArmorModel(EquipmentSlot.LEGS));
            p_117096_.scale(1.002f, 1.002f, 1.002f);
            p_117096_.translate(0,  -0.005, 0);
            this.renderArmorPiece(p_117096_, p_117097_, p_117098_, p_117099_, EquipmentSlot.FEET, this.getArmorModel(EquipmentSlot.FEET));
            p_117096_.scale(1.002f, 1.002f, 1.002f);
            p_117096_.translate(0,  0.02, 0);
            this.renderArmorPiece(p_117096_, p_117097_, p_117098_, p_117099_, EquipmentSlot.HEAD, this.getArmorModel(EquipmentSlot.HEAD));
        }
    }

    private boolean shouldRender(T entity) {
        return IronManSuitItem.hasPower(entity, 0);
    }

    private void renderArmorPiece(PoseStack p_117119_, MultiBufferSource p_117120_, int packedLight, T p_117121_, EquipmentSlot p_117122_, A p_117124_) {
        ItemStack itemstack = p_117121_.getItemBySlot(p_117122_);
        if (itemstack.getItem() instanceof ArmorItem armoritem) {
            if (armoritem.getSlot() == p_117122_ && armoritem instanceof GlowingArmor) {
                this.getParentModel().copyPropertiesTo(p_117124_);
                this.setPartVisibility(p_117124_, p_117122_);
                net.minecraft.client.model.Model model = getArmorModelHook(p_117121_, itemstack, p_117122_, p_117124_);
                this.renderModel(p_117119_, p_117120_, packedLight, model, this.getArmorResource(p_117121_, itemstack, p_117122_, p_117122_ == EquipmentSlot.HEAD && armoritem instanceof IronManSuitItem ? (itemstack.getOrCreateTag().getBoolean("Open") ? "open" : null) : null));
            }
        }
    }

    protected void setPartVisibility(A p_117126_, EquipmentSlot p_117127_) {
        p_117126_.setAllVisible(false);
        switch (p_117127_) {
            case HEAD -> {
                p_117126_.head.visible = true;
                p_117126_.hat.visible = true;
            }
            case CHEST -> {
                p_117126_.body.visible = true;
                p_117126_.rightArm.visible = true;
                p_117126_.leftArm.visible = true;
            }
            case LEGS -> {
                p_117126_.body.visible = true;
                p_117126_.rightLeg.visible = true;
                p_117126_.leftLeg.visible = true;
            }
            case FEET -> {
                p_117126_.rightLeg.visible = true;
                p_117126_.leftLeg.visible = true;
            }
        }

    }

    private void renderModel(PoseStack p_117107_, MultiBufferSource p_117108_, int packedLight, net.minecraft.client.model.Model p_117112_, ResourceLocation armorResource) {
        VertexConsumer vertexconsumer = p_117108_.getBuffer(RenderType.eyes(armorResource));
        p_117112_.renderToBuffer(p_117107_, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    private A getArmorModel(EquipmentSlot p_117079_) {
        return this.usesInnerModel(p_117079_) ? this.innerModel : this.outerModel;
    }

    private boolean usesInnerModel(EquipmentSlot p_117129_) {
        return p_117129_ == EquipmentSlot.LEGS;
    }

    protected net.minecraft.client.model.Model getArmorModelHook(T entity, ItemStack itemStack, EquipmentSlot slot, A model) {
        return net.minecraftforge.client.ForgeHooksClient.getArmorModel(entity, itemStack, slot, model);
    }

    public ResourceLocation getArmorResource(Entity entity, ItemStack stack, EquipmentSlot slot, @Nullable String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        if (item instanceof GlowingArmor glowingArmor) {
            ResourceLocation glowTexture = glowingArmor.getGlowTexture(stack, entity, slot);
            if (glowTexture == null) return new ResourceLocation("marvel:textures/empty.png");

            String texture = glowTexture.getPath();
            String domain = glowTexture.getNamespace();
            String s1 = String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_%d%s_glow.png", domain, texture, (usesInnerModel(slot) ? 2 : 1), type == null ? "" : String.format(java.util.Locale.ROOT, "_%s", type));

            ResourceLocation resourcelocation = GLOW_ARMOR_LOCATION_CACHE.get(s1);

            if (resourcelocation == null) {
                resourcelocation = new ResourceLocation(s1);
                GLOW_ARMOR_LOCATION_CACHE.put(s1, resourcelocation);
            }

            return resourcelocation;
        }
        return new ResourceLocation("marvel:textures/empty.png");
    }
}
