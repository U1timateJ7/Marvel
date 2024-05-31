package com.ulto.marvel.world.item;

import com.ulto.marvel.client.model.MarvelModModels;
import com.ulto.marvel.client.model.WolverineHelmetModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.Map;

public class WolverineHelmetItem extends SuitItem {
	public WolverineHelmetItem(Item.Properties properties) {
		super(MarvelModArmorMaterials.REINFORCED_LEATHER, "wolverine", EquipmentSlot.HEAD, properties);
	}

	public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Override
			public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
				HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("head",
						new WolverineHelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.WOLVERINE_HELMET)).mask,
						"hat", new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
						new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm",
						new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
						new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg",
						new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
						new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
				armorModel.crouching = living.isShiftKeyDown();
				armorModel.riding = defaultModel.riding;
				armorModel.young = living.isBaby();
				return armorModel;
			}
		});
	}
}
