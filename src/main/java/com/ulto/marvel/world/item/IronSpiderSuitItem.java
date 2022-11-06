
package com.ulto.marvel.world.item;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.client.model.ModelCostume;
import com.ulto.marvel.client.model.ModelHelmet;
import com.ulto.marvel.client.model.ModelIronSpider;
import com.ulto.marvel.procedures.IronManWaterBreathingProcedure;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class IronSpiderSuitItem extends IronManSuitItem {
	public IronSpiderSuitItem(EquipmentSlot slot, Item.Properties properties) {
		super(MarvelModArmorMaterials.IRON_MAN_DIAMOND_PLUS, slot, properties);
	}

	@Override
	protected String getOrCreateDescriptionId() {
		if (this.descriptionId == null) {
			this.descriptionId = Util.makeDescriptionId("item", Registry.ITEM.getKey(this));
		}

		return this.descriptionId;
	}

	@Override
	public ResourceLocation getGlowTexture(ItemStack stack, @Nullable Entity entity, @Nullable EquipmentSlot slot) {
		return null;
	}

	@Override
	public boolean isNanotech() {
		return true;
	}

	public static class Helmet extends IronSpiderSuitItem {
		public Helmet(Properties properties) {
			super(EquipmentSlot.HEAD, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
							Map.of("head", new ModelHelmet(Minecraft.getInstance().getEntityModels().bakeLayer(ModelHelmet.LAYER_LOCATION)).Head,
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
		public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
			super.appendHoverText(stack, level, components, flag);
			if (stack.getOrCreateTag().getBoolean("Open")) components.add(new TextComponent("Open"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return stack.getOrCreateTag().getBoolean("Open") ? "marvel:textures/models/armor/empty_layer_1.png" : "marvel:textures/iron_spider_helmet.png";
		}

		@Override
		public ResourceLocation getGlowTexture(ItemStack stack, @Nullable Entity entity, @Nullable EquipmentSlot slot) {
			return getBattery(stack) == 0 ? null : new ResourceLocation(MarvelMod.MOD_ID, "iron_spider");
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			super.onArmorTick(itemstack, world, entity);
			IronManWaterBreathingProcedure.execute(entity);
		}
	}

	public static class Chestplate extends IronSpiderSuitItem {
		public Chestplate(Properties properties) {
			super(EquipmentSlot.CHEST, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armsExtendedModel;
					armsExtendedModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body",
							new ModelIronSpider(Minecraft.getInstance().getEntityModels().bakeLayer(ModelIronSpider.LAYER_LOCATION)).Body, "left_arm",
							new ModelIronSpider(Minecraft.getInstance().getEntityModels().bakeLayer(ModelIronSpider.LAYER_LOCATION)).LeftArm,
							"right_arm",
							new ModelIronSpider(Minecraft.getInstance().getEntityModels().bakeLayer(ModelIronSpider.LAYER_LOCATION)).RightArm, "head",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "hat",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "right_leg",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()), "left_leg",
							new ModelPart(Collections.emptyList(), Collections.emptyMap()))));
					armsExtendedModel.crouching = living.isShiftKeyDown();
					armsExtendedModel.riding = defaultModel.riding;
					armsExtendedModel.young = living.isBaby();
					return armsExtendedModel;
				}
			});
		}

		@Override
		public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
			super.appendHoverText(stack, level, components, flag);
			if (stack.getOrCreateTag().getBoolean("ArmsExtended")) components.add(new TextComponent("Arms Extended"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return stack.getOrCreateTag().getBoolean("ArmsExtended") ? "marvel:textures/iron_spider_arms.png" : "marvel:textures/iron_spider_layer_1.png";
		}
	}

	public static class Leggings extends IronSpiderSuitItem {
		public Leggings(Properties properties) {
			super(EquipmentSlot.LEGS, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
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
			return "marvel:textures/iron_spider_layer_2.png";
		}
	}

	public static class Boots extends IronSpiderSuitItem {
		public Boots(Properties properties) {
			super(EquipmentSlot.FEET, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
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
			return "marvel:textures/iron_spider_layer_1.png";
		}
	}
}
