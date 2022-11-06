package com.ulto.marvel.world.item;

import com.ulto.marvel.world.level.block.MarvelModBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MarvelModTiers {
    public static final Tier BLADE = new ForgeTier(1, 0, 4f, 6f, 0, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.EMPTY);
    public static final Tier DRILL = new ForgeTier(2, 0, 12f, 3f, 0, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.EMPTY);
    public static final Tier NANO_TOOL = new ForgeTier(2, 0, 8f, 6f, 0, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.EMPTY);
    public static final Tier CLAWS = new ForgeTier(2, 0, 8f, 8f, 0, BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.EMPTY);
    public static final Tier UPGRADED_NANO_TOOL = new ForgeTier(3, 0, 9f, 8f, 0, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.EMPTY);
    public static final Tier VIBRANIUM = new ForgeTier(5, 0, 12f, 4f, 0, MarvelModBlocks.Tags.NEEDS_VIBRANIUM_TOOL, () -> Ingredient.of(MarvelModItems.VIBRANIUM_INGOT.get()));
}
