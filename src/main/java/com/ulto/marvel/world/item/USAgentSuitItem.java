
package com.ulto.marvel.world.item;

import com.ulto.marvel.client.model.ModelCostume;
import com.ulto.marvel.client.model.ModelHelmet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.Collections;
import java.util.Map;

public abstract class USAgentSuitItem extends ArmorItem {
	public USAgentSuitItem(EquipmentSlot slot, Properties properties) {
		super(MarvelModArmorMaterials.REINFORCED_LEATHER, slot, properties);
	}

	public static class Helmet extends USAgentSuitItem {
		public Helmet(Properties properties) {
			super(EquipmentSlot.HEAD, properties);
		}

		public void initializeClient(java.util.function.Consumer<IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				public HumanoidModel<?> getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
					HumanoidModel<?> armorModel = new HumanoidModel<>(new ModelPart(Collections.emptyList(),
							Map.of("head", new ModelHelmet<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelHelmet.LAYER_LOCATION)).Head,
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

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/us_agent_layer_1.png";
		}
	}

	public static class Chestplate extends USAgentSuitItem {
		public Chestplate(Properties properties) {
			super(EquipmentSlot.CHEST, properties);
		}

		public void initializeClient(java.util.function.Consumer<IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).Body, "left_arm",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).LeftArm, "right_arm",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).RightArm, "head",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
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

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/us_agent_layer_1.png";
		}
	}

	public static class Leggings extends USAgentSuitItem {
		public Leggings(Properties properties) {
			super(EquipmentSlot.LEGS, properties);
		}

		public void initializeClient(java.util.function.Consumer<IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).LeftLeg, "right_leg",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).RightLeg, "head",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/us_agent_layer_2.png";
		}
	}

	public static class Boots extends USAgentSuitItem {
		public Boots(Properties properties) {
			super(EquipmentSlot.FEET, properties);
		}

		public void initializeClient(java.util.function.Consumer<IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).LeftBoot, "right_leg",
							new ModelCostume(Minecraft.getInstance().getEntityModels().bakeLayer(ModelCostume.LAYER_LOCATION)).RightBoot, "head",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "body",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_arm",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_arm",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					armorModel.crouching = living.isShiftKeyDown();
					armorModel.riding = defaultModel.riding;
					armorModel.young = living.isBaby();
					return armorModel;
				}
			});
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/us_agent_layer_1.png";
		}
	}
}
