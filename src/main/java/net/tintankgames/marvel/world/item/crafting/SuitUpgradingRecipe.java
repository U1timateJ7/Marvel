package net.tintankgames.marvel.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitUpgradingRecipe implements Recipe<Container> {
    final String group;
    final SuitUpgradingBookCategory category;
    final ItemStack result;
    final NonNullList<Ingredient> ingredients;
    final Ingredient suit;
    final boolean consumeSuit;

    public SuitUpgradingRecipe(String group, SuitUpgradingBookCategory category, Ingredient suit, ItemStack result, NonNullList<Ingredient> ingredients, boolean consumeSuit) {
        this.group = group;
        this.category = category;
        this.suit = suit;
        this.result = result;
        this.ingredients = ingredients;
        this.consumeSuit = consumeSuit;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients1 = NonNullList.withSize(ingredients.size() + 1, Ingredient.EMPTY);
        ingredients1.set(0, suit);
        for (int i = 1; i < ingredients.size() + 1; i++) {
            ingredients1.set(i, ingredients.get(i - 1));
        }
        return ingredients1;
    }

    @Override
    public boolean matches(Container p_44262_, Level p_44003_) {
        StackedContents stackedcontents = new StackedContents();
        int i = 0;

        for (int j = 0; j < p_44262_.getContainerSize(); j++) {
            ItemStack itemstack = p_44262_.getItem(j);
            if (!itemstack.isEmpty()) {
                i++;
                stackedcontents.accountStack(itemstack, 1);
            }
        }

        return i == this.getIngredients().size() && /*this.suit.test(p_44262_.getItem(0)) && */stackedcontents.canCraft(this, null);
    }

    @Override
    public ItemStack assemble(Container p_44001_, HolderLookup.Provider p_336092_) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return p_43999_ * p_44000_ >= this.ingredients.size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return this.result;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.SUIT_UPGRADING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MarvelRecipeTypes.SUIT_UPGRADING.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return MarvelBlocks.SUIT_TABLE.toStack();
    }

    public Ingredient getSuit() {
        return suit;
    }

    public boolean consumesSuit() {
        return consumeSuit;
    }

    @Override
    public String getGroup() {
        return group;
    }

    public SuitUpgradingBookCategory category() {
        return this.category;
    }

    public static class Serializer implements RecipeSerializer<SuitUpgradingRecipe> {
        private static final MapCodec<SuitUpgradingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340779_ -> p_340779_.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(p_300975_ -> p_300975_.group),
                        SuitUpgradingBookCategory.CODEC.optionalFieldOf("category", SuitUpgradingBookCategory.SUITS).forGetter(p_300975_ -> p_300975_.category),
                        Ingredient.CODEC_NONEMPTY.fieldOf("suit").forGetter(p_300975_ -> p_300975_.suit),
                        ItemStack.STRICT_SINGLE_ITEM_CODEC.fieldOf("results").forGetter(p_301142_ -> p_301142_.result),
                        Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").flatXmap(ingredientList -> {Ingredient[] array = ingredientList.toArray(Ingredient[]::new);if (array.length == 0) {return DataResult.error(() -> "No ingredients for upgrade recipe");} else {return array.length > 9 ? DataResult.error(() -> "Too many ingredients for upgrade recipe. The maximum is: %s".formatted(9)) : DataResult.success(NonNullList.of(Ingredient.EMPTY, array));}}, DataResult::success).forGetter(p_300975_ -> p_300975_.ingredients),
                        Codec.BOOL.fieldOf("consume_suit").forGetter(recipe -> recipe.consumeSuit))
                        .apply(p_340779_, SuitUpgradingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, SuitUpgradingRecipe> STREAM_CODEC = StreamCodec.of(
                SuitUpgradingRecipe.Serializer::toNetwork, SuitUpgradingRecipe.Serializer::fromNetwork
        );

        @Override
        public MapCodec<SuitUpgradingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SuitUpgradingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SuitUpgradingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            String group = ByteBufCodecs.STRING_UTF8.decode(buf);
            SuitUpgradingBookCategory category = SuitUpgradingBookCategory.STREAM_CODEC.decode(buf);
            Ingredient suit = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            int j = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(j, Ingredient.EMPTY);
            ingredients.replaceAll(p_319735_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            ItemStack result = ItemStack.STREAM_CODEC.decode(buf);
            boolean consumeSuit = ByteBufCodecs.BOOL.decode(buf);
            return new SuitUpgradingRecipe(group, category, suit, result, ingredients, consumeSuit);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, SuitUpgradingRecipe recipe) {
            ByteBufCodecs.STRING_UTF8.encode(buf, recipe.group);
            SuitUpgradingBookCategory.STREAM_CODEC.encode(buf, recipe.category);
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.suit);
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            ItemStack.STREAM_CODEC.encode(buf, recipe.result);
            ByteBufCodecs.BOOL.encode(buf, recipe.consumeSuit);
        }
    }
}
