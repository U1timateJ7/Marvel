
package com.ulto.marvel.world.item;

import com.ulto.marvel.client.model.MarvelModModels;
import com.ulto.marvel.client.model.CostumeModel;
import com.ulto.marvel.client.model.HelmetModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SuitItem extends ArmorItem {
	protected final String textureName;
	protected final String subtitle;

	public SuitItem(String textureName, EquipmentSlot slot, Properties properties) {
		this(MarvelModArmorMaterials.REINFORCED_LEATHER, textureName, "", slot, properties);
	}

	public SuitItem(String textureName, String subtitle, EquipmentSlot slot, Properties properties) {
		this(MarvelModArmorMaterials.REINFORCED_LEATHER, textureName, subtitle, slot, properties);
	}

	public SuitItem(ArmorMaterial armorMaterial, String textureName, EquipmentSlot slot, Properties properties) {
		this(armorMaterial, textureName, "", slot, properties);
	}

	public SuitItem(ArmorMaterial armorMaterial, String textureName, String subtitle, EquipmentSlot slot, Properties properties) {
		super(armorMaterial, slot, properties);
		this.textureName = textureName;
		this.subtitle = subtitle;
	}

	public void initializeClient(java.util.function.Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Override
			@OnlyIn(Dist.CLIENT)
			public HumanoidModel<?> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
				CostumeModel<?> costume = new CostumeModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME));
				HumanoidModel<?> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(), Map.of(
						"body", costume.Body,
						"left_arm", costume.LeftArm,
						"right_arm", costume.RightArm,
						"head", new HelmetModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.HELMET)).Head,
						"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()),
						"right_leg", slot == EquipmentSlot.FEET ? costume.RightBoot : costume.RightLeg,
						"left_leg", slot == EquipmentSlot.FEET ? costume.LeftBoot : costume.LeftLeg)));
				armorModel.crouching = living.isShiftKeyDown();
				armorModel.riding = defaultModel.riding;
				armorModel.young = living.isBaby();
				return armorModel;
			}
		});
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		if (!subtitle.isBlank()) list.add(new TextComponent(subtitle));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "marvel:textures/suit/" + textureName + (slot == EquipmentSlot.HEAD ? "_helmet" :  "_layer_" + (slot == EquipmentSlot.LEGS ? "2" : "1")) + ".png";
	}
}
