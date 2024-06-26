package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class SpiderManSuitItem extends LeatherSuitItem {
    public static final UUID SPIDER_MAN_UUID = UUID.fromString("7F13E047-8356-4941-84AF-BD7DB5A5562F");
    private static final AttributeModifier safeFallDistanceModifier = new AttributeModifier(SPIDER_MAN_UUID, "Spider-Man safe fall distance modifier", 3, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier fallDamageMultiplierModifier = new AttributeModifier(SPIDER_MAN_UUID, "Spider-Man fall damage multiplier modifier", -0.5, AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    public SpiderManSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.SPIDER_MAN_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 2), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_BOOST, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.WEB_SHOOTER.get())));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.spiderManSuit(type);
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
            if (living.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.SPIDER_MAN_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SPIDER_MAN_ARMOR)) {
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).addOrUpdateTransientModifier(safeFallDistanceModifier);
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).addOrUpdateTransientModifier(fallDamageMultiplierModifier);
            } else {
                living.getAttribute(Attributes.SAFE_FALL_DISTANCE).removeModifier(safeFallDistanceModifier.id());
                living.getAttribute(Attributes.FALL_DAMAGE_MULTIPLIER).removeModifier(fallDamageMultiplierModifier.id());
            }
        }
    }
}
