package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.client.model.MarvelModels;

public class CaptainMarvel838SuitItem extends CaptainMarvelSuitItem {
    public CaptainMarvel838SuitItem(Type type, Properties properties) {
        super(type, properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.suit(type);
    }
}
