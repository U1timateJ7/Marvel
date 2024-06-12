package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class WolverineSuitItem extends LeatherSuitItem {
    public WolverineSuitItem(Type type, Properties properties) {
        super(type, false, MarvelItems.Tags.WOLVERINE_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.ADAMANTIUM_CLAWS.get())));
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                if (living.tickCount % 10 == 0) {
                    living.heal(1.0F);
                }
            }
        }
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.wolverineSuit(type);
    }
}
