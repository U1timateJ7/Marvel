
package com.ulto.marvel.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import java.util.List;

import com.ulto.marvel.procedures.IronManMark1BodyTickEventProcedure;
import com.ulto.marvel.init.MarvelModTabs;

public abstract class IronManMark1Item extends ArmorItem {
	public IronManMark1Item(EquipmentSlot slot, Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			@Override
			public int getEnchantmentValue() {
				return 9;
			}

			@Override
			public SoundEvent getEquipSound() {
				return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
			}

			@Override
			public Ingredient getRepairIngredient() {
				return Ingredient.of(new ItemStack(Items.IRON_INGOT));
			}

			@Override
			public String getName() {
				return "iron_man_mark_1";
			}

			@Override
			public float getToughness() {
				return 0f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.1f;
			}
		}, slot, properties);
	}

	public static class Helmet extends IronManMark1Item {
		public Helmet() {
			super(EquipmentSlot.HEAD, new Item.Properties().tab(MarvelModTabs.TAB_IRON_MAN_ARMOR));
			setRegistryName("iron_man_mark_1_helmet");
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
		public Chestplate() {
			super(EquipmentSlot.CHEST, new Item.Properties().tab(MarvelModTabs.TAB_IRON_MAN_ARMOR));
			setRegistryName("iron_man_mark_1_chestplate");
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
		public Leggings() {
			super(EquipmentSlot.LEGS, new Item.Properties().tab(MarvelModTabs.TAB_IRON_MAN_ARMOR));
			setRegistryName("iron_man_mark_1_leggings");
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
		public Boots() {
			super(EquipmentSlot.FEET, new Item.Properties().tab(MarvelModTabs.TAB_IRON_MAN_ARMOR));
			setRegistryName("iron_man_mark_1_boots");
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
