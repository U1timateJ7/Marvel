package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.component.Size;

import java.util.List;

public class WaspSuitItem extends SuitItem {
    public WaspSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.UPGRADED_REINFORCED_LEATHER, type, MarvelItems.Tags.WASP_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.SHRINK.get(), MarvelItems.STING.get())));
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.waspSuit(type);
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        stack.set(MarvelDataComponents.SIZE, Size.NORMAL);
        return super.onEntityItemUpdate(stack, entity);
    }
}
