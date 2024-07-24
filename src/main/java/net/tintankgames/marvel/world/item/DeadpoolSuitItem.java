package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.EffectCure;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class DeadpoolSuitItem extends LeatherSuitItem {
    public static final EffectCure DEADPOOL_CURE = EffectCure.get("deadpool");

    public DeadpoolSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.DEADPOOL_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_RESISTANCE, 0)) : List.of(), properties);
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.DEADPOOL_ARMOR) && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.DEADPOOL_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
                if (living.tickCount % 10 == 0) {
                    living.heal(1.0F);
                }
                living.removeEffectsCuredBy(DEADPOOL_CURE);
            }
            if (living instanceof ServerPlayer player && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.DEADPOOL_ARMOR)) {
                player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.HAS_KATANA_IN_INVENTORY, player.getInventory().contains(stack -> stack.is(MarvelItems.KATANAS)));
            }
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.deadpoolSuit(type);
    }
}
