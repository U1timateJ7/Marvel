package net.tintankgames.marvel.world.item.crafting;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitVariantRecipe implements Recipe<SingleRecipeInput> {
    final ItemStack result;
    final Ingredient suit;

    public SuitVariantRecipe(Ingredient suit, ItemStack result) {
        this.suit = suit;
        this.result = result;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.of(suit);
    }

    @Override
    public boolean matches(SingleRecipeInput p_44262_, Level p_44003_) {
        return suit.test(p_44262_.getItem(0));
    }

    @Override
    public ItemStack assemble(SingleRecipeInput p_44001_, HolderLookup.Provider p_336092_) {
        ItemStack stack = p_44001_.getItem(0).transmuteCopy(this.result.getItem(), this.result.getCount());
        stack.applyComponents(this.result.getComponentsPatch());
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return true;
    }

    public Ingredient getSuit() {
        return suit;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.SUIT_VARIANT.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MarvelRecipeTypes.SUIT_VARIANT.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return MarvelBlocks.SUIT_TABLE.toStack();
    }

    public static class Serializer implements RecipeSerializer<SuitVariantRecipe> {
        private static final MapCodec<SuitVariantRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340779_ -> p_340779_.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("suit").forGetter(p_300975_ -> p_300975_.suit),
                        ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("results").forGetter(p_301142_ -> p_301142_.result))
                        .apply(p_340779_, SuitVariantRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, SuitVariantRecipe> STREAM_CODEC = StreamCodec.of(
                SuitVariantRecipe.Serializer::toNetwork, SuitVariantRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<SuitVariantRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SuitVariantRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SuitVariantRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            Ingredient suit = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            return new SuitVariantRecipe(suit, result);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, SuitVariantRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.suit);
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
        }
    }
}
