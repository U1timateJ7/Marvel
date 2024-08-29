package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.tintankgames.marvel.client.model.MarvelModels;

import java.util.List;

public class WinterSoldierSuitItem extends SuitItem {
    public WinterSoldierSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.REINFORCED_LEATHER_NO_HELMET, type, MarvelItems.Tags.WINTER_SOLDIER_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties);
    }

    @Override
    public boolean needsHelmet() {
        return false;
    }

    @Override
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.winterSoldierSuit(type);
    }
}
