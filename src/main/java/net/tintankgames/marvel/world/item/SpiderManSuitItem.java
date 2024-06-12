package net.tintankgames.marvel.world.item;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class SpiderManSuitItem extends LeatherSuitItem {
    public SpiderManSuitItem(Type type, Properties properties) {
        super(type, false, MarvelItems.Tags.SPIDER_MAN_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.WEB_SHOOTER.get())));
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SPIDER_SENSE)) {
                living.getItemBySlot(EquipmentSlot.CHEST).update(MarvelDataComponents.SPIDER_SENSE, 1, timer -> timer - 1);
                if (living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SPIDER_SENSE, 0) <= 0) {
                    living.getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.SPIDER_SENSE);
                }
            }
        }
    }
}
