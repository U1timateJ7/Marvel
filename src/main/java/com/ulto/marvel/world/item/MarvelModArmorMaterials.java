package com.ulto.marvel.world.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ForgeRegistries;

public class MarvelModArmorMaterials {
    public static final ArmorMaterial REINFORCED_LEATHER = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{2, 6, 8, 2}[slot.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return 9;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(MarvelModItems.REINFORCED_LEATHER.get()));
        }

        @Override
        public String getName() {
            return "reinforced_leather";
        }

        @Override
        public float getToughness() {
            return 1f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0f;
        }
    };
    public static final ArmorMaterial IRON_MAN_MARK_1 = new ArmorMaterial() {
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
    };
    public static final ArmorMaterial IRON_MAN_IRON = new ArmorMaterial() {
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
            return Ingredient.of(new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_iron";
        }

        @Override
        public float getToughness() {
            return 1f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_IRON_PLUS = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{2, 6, 8, 2}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_iron_plus";
        }

        @Override
        public float getToughness() {
            return 1f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_MARK_7 = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{2, 6, 8, 2}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_mark_7";
        }

        @Override
        public float getToughness() {
            return 2f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_3 = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_diamond_minus";
        }

        @Override
        public float getToughness() {
            return 1f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_MARK_17 = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_mark_17";
        }

        @Override
        public float getToughness() {
            return 2f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_DIAMOND = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 33;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_diamond";
        }

        @Override
        public float getToughness() {
            return 2f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_DIAMOND_PLUS = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 35;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_diamond_plus";
        }

        @Override
        public float getToughness() {
            return 3f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial IRON_MAN_NETHERITE = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getIndex()] * 37;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
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
            return Ingredient.of(new ItemStack(MarvelModItems.GOLD_TITANIUM_ALLOY.get()), new ItemStack(MarvelModItems.TITANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "iron_man_netherite";
        }

        @Override
        public float getToughness() {
            return 3f;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.1f;
        }
    };
    public static final ArmorMaterial VIBRANIUM = new ArmorMaterial() {
        @Override
        public int getDurabilityForSlot(EquipmentSlot slot) {
            return 0;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlot slot) {
            return new int[]{3, 6, 8, 3}[slot.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return 0;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(new ItemStack(MarvelModItems.VIBRANIUM_INGOT.get()));
        }

        @Override
        public String getName() {
            return "vibranium";
        }

        @Override
        public float getToughness() {
            return 3f;
        }

        @Override
        public float getKnockbackResistance() {
            return 5f;
        }
    };
}
