
package com.ulto.marvel.world.item;

import com.ulto.marvel.client.model.MarvelModModels;
import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.client.model.CostumeModel;
import com.ulto.marvel.client.model.HelmetModel;
import com.ulto.marvel.procedures.IronManBodyTickEventProcedure;
import com.ulto.marvel.procedures.IronManFallDamageProcedure;
import com.ulto.marvel.procedures.IronManWaterBreathingProcedure;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
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

public abstract class IronManMark42Item extends IronManSuitItem {
	public IronManMark42Item(EquipmentSlot slot, Item.Properties properties) {
		super(MarvelModArmorMaterials.IRON_MAN_3, slot, properties);
	}

	@Override
	public ResourceLocation getGlowTexture(ItemStack stack, @Nullable Entity entity, @Nullable EquipmentSlot slot) {
		return getBattery(stack) == 0 ? null : new ResourceLocation(MarvelMod.MOD_ID, "mark_42");
	}

	public static class Helmet extends IronManMark42Item {
		public Helmet(Properties properties) {
			super(EquipmentSlot.HEAD, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(),
							Map.of("head", new HelmetModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.HELMET)).Head,
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
			components.add(new TextComponent(stack.getOrCreateTag().getBoolean("Open") ? "Mark XLII - Open" : "Mark XLII"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return stack.getOrCreateTag().getBoolean("Open") ? "marvel:textures/mark42_helmet_open.png" : "marvel:textures/mark42_helmet" + (type == null ? "" : "_" + type) + ".png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			super.onArmorTick(itemstack, world, entity);
			IronManWaterBreathingProcedure.execute(entity);
		}
	}

	public static class Chestplate extends IronManMark42Item {
		public Chestplate(Properties properties) {
			super(EquipmentSlot.CHEST, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("body",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).Body, "left_arm",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).LeftArm, "right_arm",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).RightArm, "head",
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
		public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
			super.appendHoverText(stack, level, components, flag);
			components.add(new TextComponent("Mark XLII"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/mark42_layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			super.onArmorTick(itemstack, world, entity);
			IronManBodyTickEventProcedure.execute(world, entity);
		}
	}

	public static class Leggings extends IronManMark42Item {
		public Leggings(Properties properties) {
			super(EquipmentSlot.LEGS, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).LeftLeg, "right_leg",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).RightLeg, "head",
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
		public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
			super.appendHoverText(stack, level, components, flag);
			components.add(new TextComponent("Mark XLII"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/mark42_layer_2.png";
		}
	}

	public static class Boots extends IronManMark42Item {
		public Boots(Properties properties) {
			super(EquipmentSlot.FEET, properties);
		}

		public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.IItemRenderProperties> consumer) {
			consumer.accept(new IItemRenderProperties() {
				@Override
				@OnlyIn(Dist.CLIENT)
				public HumanoidModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel defaultModel) {
					HumanoidModel armorModel = new HumanoidModel(new ModelPart(Collections.emptyList(), Map.of("left_leg",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).LeftBoot, "right_leg",
							new CostumeModel(Minecraft.getInstance().getEntityModels().bakeLayer(MarvelModModels.COSTUME)).RightBoot, "head",
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
		public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
			super.appendHoverText(stack, level, components, flag);
			components.add(new TextComponent("Mark XLII"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/mark42_layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			super.onArmorTick(itemstack, world, entity);
			IronManFallDamageProcedure.execute(entity);
		}
	}
}
