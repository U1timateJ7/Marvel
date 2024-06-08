package net.tintankgames.marvel.world.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum MarvelTiers implements Tier {
    VIBRANIUM(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 4063, 9.0F, 4.0F, 7, () -> Ingredient.of(MarvelItems.VIBRANIUM_INGOT)),
    ADAMANTIUM_CLAWS(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 0, 9.0F, 4.0F, 0, () -> Ingredient.EMPTY);

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    MarvelTiers(TagKey<Block> incorrectBlocksForDrops, int durability, float speed, float damageBonus, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops;
        this.uses = durability;
        this.speed = speed;
        this.damage = damageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
