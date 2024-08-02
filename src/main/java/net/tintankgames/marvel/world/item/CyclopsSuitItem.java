package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;

import java.util.List;

public class CyclopsSuitItem extends LeatherSuitItem {
    public CyclopsSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.CYCLOPS_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), type == Type.HELMET ? properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.OPTIC_BLAST.get())).component(MarvelDataComponents.OPTIC_BLAST_MODE, OpticBlastMode.NARROW) : properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.OPTIC_BLAST.get())));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.cyclopsSuit(type);
    }
}
