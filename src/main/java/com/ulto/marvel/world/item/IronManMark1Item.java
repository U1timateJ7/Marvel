
package com.ulto.marvel.world.item;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.procedures.IronManMark1BodyTickEventProcedure;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class IronManMark1Item extends ArmorItem implements GlowingArmor {
	public IronManMark1Item(EquipmentSlot slot, Item.Properties properties) {
		super(MarvelModArmorMaterials.IRON_MAN_MARK_1, slot, properties);
	}

	@Override
	protected String getOrCreateDescriptionId() {
		if (this.descriptionId == null) {
			this.descriptionId = Util.makeDescriptionId("item", new ResourceLocation(MarvelMod.MOD_ID, "iron_man_" + getArmorSuffix(getSlot())));
		}

		return this.descriptionId;
	}

	private String getArmorSuffix(EquipmentSlot slot) {
		return switch (slot) {
			case FEET -> "boots";
			case LEGS -> "leggings";
			case CHEST -> "chestplate";
			case HEAD -> "helmet";
			default -> "";
		};
	}

	@Override
	public ResourceLocation getGlowTexture(ItemStack stack, @Nullable Entity entity, @Nullable EquipmentSlot slot) {
		return new ResourceLocation(MarvelMod.MOD_ID, "mark_1");
	}

	public static class Helmet extends IronManMark1Item {
		public Helmet(Properties properties) {
			super(EquipmentSlot.HEAD, properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("Mark I"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/models/armor/mark1_layer_1.png";
		}
	}

	public static class Chestplate extends IronManMark1Item {
		public Chestplate(Properties properties) {
			super(EquipmentSlot.CHEST, properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("Mark I"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/models/armor/mark1_layer_1.png";
		}

		@Override
		public void onArmorTick(ItemStack itemstack, Level world, Player entity) {
			IronManMark1BodyTickEventProcedure.execute(world, entity);
		}
	}

	public static class Leggings extends IronManMark1Item {
		public Leggings(Properties properties) {
			super(EquipmentSlot.LEGS, properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("Mark I"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/models/armor/mark1_layer_2.png";
		}
	}

	public static class Boots extends IronManMark1Item {
		public Boots(Properties properties) {
			super(EquipmentSlot.FEET, properties);
		}

		@Override
		public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
			super.appendHoverText(itemstack, world, list, flag);
			list.add(new TextComponent("Mark I"));
		}

		@Override
		public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
			return "marvel:textures/models/armor/mark1_layer_1.png";
		}
	}
}
