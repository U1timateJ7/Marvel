package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public class LeatherSuitItem extends SuitItem {
    public LeatherSuitItem(Type type, TagKey<Item> suitPieces, Properties properties) {
        this(type, suitPieces, List.of(), properties);
    }

    public LeatherSuitItem(Type type, TagKey<Item> suitPieces, List<MobEffectInstance> effects, Properties properties) {
        super(MarvelArmorMaterials.REINFORCED_LEATHER, type, suitPieces, effects, properties);
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.suit(type);
    }
}
