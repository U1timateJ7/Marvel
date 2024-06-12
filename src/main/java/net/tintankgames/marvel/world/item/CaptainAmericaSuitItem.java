package net.tintankgames.marvel.world.item;

import net.minecraft.world.effect.MobEffects;

import java.util.List;

public class CaptainAmericaSuitItem extends LeatherSuitItem {
    public CaptainAmericaSuitItem(Type type, Properties properties) {
        super(type, false, MarvelItems.Tags.CAPTAIN_AMERICA_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties);
    }
}
