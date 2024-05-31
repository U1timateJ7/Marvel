
package com.ulto.marvel.world.item;

import com.ulto.marvel.client.model.MarvelModModels;
import com.ulto.marvel.client.model.CostumeModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.IItemRenderProperties;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collections;
import java.util.Map;

public class IronSpiderStorageItem extends ArmorItem {
	public IronSpiderStorageItem(Item.Properties properties) {
		super(new ArmorMaterial() {
			@Override
			public int getDurabilityForSlot(EquipmentSlot slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 0;
			}

			@Override
			public int getDefenseForSlot(EquipmentSlot slot) {
				return new int[]{3, 6, 1, 3}[slot.getIndex()];
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
				return Ingredient.EMPTY;
			}

			@Override
			public String getName() {
				return "iron_spider_storage";
			}

			@Override
			public float getToughness() {
				return 1f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0f;
			}
		}, EquipmentSlot.CHEST, properties);
	}

	@Override
	public ItemStack getDefaultInstance() {
		ItemStack arcReactor = new ItemStack(this);
		ListTag armorList = new ListTag();
		armorList.addTag(0, MarvelModItems.IRON_SPIDER_SUIT_BOOTS.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(1, MarvelModItems.IRON_SPIDER_SUIT_LEGGINGS.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(2, MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(3, MarvelModItems.IRON_SPIDER_SUIT_HELMET.get().getDefaultInstance().save(new CompoundTag()));
		arcReactor.getOrCreateTag().put("ArmorItems", armorList);
		return arcReactor;
	}

	@Override
	public void fillItemCategory(CreativeModeTab p_41391_, NonNullList<ItemStack> p_41392_) {
		if (this.allowdedIn(p_41391_)) {
			p_41392_.add(getDefaultInstance());
		}
	}

	@Override
	public void onCraftedBy(ItemStack stack, Level p_41448_, Player p_41449_) {
		ListTag armorList = new ListTag();
		armorList.addTag(0, MarvelModItems.IRON_SPIDER_SUIT_BOOTS.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(1, MarvelModItems.IRON_SPIDER_SUIT_LEGGINGS.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(2, MarvelModItems.IRON_SPIDER_SUIT_CHESTPLATE.get().getDefaultInstance().save(new CompoundTag()));
		armorList.addTag(3, MarvelModItems.IRON_SPIDER_SUIT_HELMET.get().getDefaultInstance().save(new CompoundTag()));
		stack.getOrCreateTag().put("ArmorItems", armorList);
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
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
		return "marvel:textures/iron_spider_storage.png";
	}
}
