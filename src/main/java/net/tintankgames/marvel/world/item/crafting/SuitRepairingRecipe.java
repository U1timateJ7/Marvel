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

public class SuitRepairingRecipe implements Recipe<Container> {
    final NonNullList<Ingredient> ingredients;
    final Ingredient suit;
    final float repairPercent;

    public SuitRepairingRecipe(Ingredient suit, NonNullList<Ingredient> ingredients, float repairPercent) {
        this.suit = suit;
        this.ingredients = ingredients;
        this.repairPercent = repairPercent;
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

        return i == this.getIngredients().size() && stackedcontents.canCraft(this, null);
    }

    @Override
    public ItemStack assemble(Container p_44001_, HolderLookup.Provider p_336092_) {
        ItemStack result = p_44001_.getItem(0).copy();
        if (this.suit.test(result)) {
            result.setDamageValue((int) (result.getDamageValue() - (result.getMaxDamage() * repairPercent)));
            return result;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int p_43999_, int p_44000_) {
        return p_43999_ * p_44000_ >= this.ingredients.size();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider p_336125_) {
        return this.suit.getItems()[0];
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return MarvelRecipeSerializers.SUIT_REPAIRING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return MarvelRecipeTypes.SUIT_REPAIRING.get();
    }

    @Override
    public ItemStack getToastSymbol() {
        return MarvelBlocks.SUIT_TABLE.toStack();
    }

    public Ingredient getSuit() {
        return suit;
    }

    public static class Serializer implements RecipeSerializer<SuitRepairingRecipe> {
        private static final MapCodec<SuitRepairingRecipe> CODEC = RecordCodecBuilder.mapCodec(
                p_340779_ -> p_340779_.group(
                        Ingredient.CODEC_NONEMPTY.fieldOf("suit").forGetter(recipe -> recipe.suit),
                        Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").flatXmap(ingredientList -> {Ingredient[] array = ingredientList.toArray(Ingredient[]::new);if (array.length == 0) {return DataResult.error(() -> "No ingredients for upgrade recipe");} else {return array.length > 4 ? DataResult.error(() -> "Too many ingredients for upgrade recipe. The maximum is: %s".formatted(4)) : DataResult.success(NonNullList.of(Ingredient.EMPTY, array));}}, DataResult::success).forGetter(p_300975_ -> p_300975_.ingredients),
                        Codec.floatRange(0.0F, 1.0F).fieldOf("repair_percent").forGetter(recipe -> recipe.repairPercent))
                        .apply(p_340779_, SuitRepairingRecipe::new));
        public static final StreamCodec<RegistryFriendlyByteBuf, SuitRepairingRecipe> STREAM_CODEC = StreamCodec.of(
                Serializer::toNetwork, Serializer::fromNetwork
        );

        @Override
        public MapCodec<SuitRepairingRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, SuitRepairingRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static SuitRepairingRecipe fromNetwork(RegistryFriendlyByteBuf buf) {
            Ingredient suit = Ingredient.CONTENTS_STREAM_CODEC.decode(buf);
            int j = buf.readVarInt();
            NonNullList<Ingredient> ingredients = NonNullList.withSize(j, Ingredient.EMPTY);
            ingredients.replaceAll(p_319735_ -> Ingredient.CONTENTS_STREAM_CODEC.decode(buf));
            float repairPercent = ByteBufCodecs.FLOAT.decode(buf);
            return new SuitRepairingRecipe(suit, ingredients, repairPercent);
        }

        private static void toNetwork(RegistryFriendlyByteBuf buf, SuitRepairingRecipe recipe) {
            Ingredient.CONTENTS_STREAM_CODEC.encode(buf, recipe.suit);
            buf.writeVarInt(recipe.ingredients.size());
            for (Ingredient ingredient : recipe.ingredients) {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buf, ingredient);
            }
            ByteBufCodecs.FLOAT.encode(buf, recipe.repairPercent);
        }
    }
}
