package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class WolverineSuitItem extends LeatherSuitItem {
    private static final AttributeModifier clawsDamageModifier = new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Claws attack damage", 7, AttributeModifier.Operation.ADD_VALUE);
    private static final AttributeModifier clawsSpeedModifier = new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Claws attack speed", -2.2, AttributeModifier.Operation.ADD_VALUE);

    public WolverineSuitItem(Type type, Properties properties) {
        super(type, false, MarvelItems.Tags.WOLVERINE_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 0), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), type == Type.CHESTPLATE ? properties.component(MarvelDataComponents.CLAWS_OUT, false) : properties);
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                if (living.tickCount % 10 == 0) {
                    living.heal(1.0F);
                }
            }
            if (living.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.CLAWS_OUT, false) && !living.hasItemInSlot(EquipmentSlot.MAINHAND)) {
                if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null && !living.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(clawsDamageModifier)) living.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(clawsDamageModifier);
                if (living.getAttribute(Attributes.ATTACK_SPEED) != null && !living.getAttribute(Attributes.ATTACK_SPEED).hasModifier(clawsSpeedModifier)) living.getAttribute(Attributes.ATTACK_SPEED).addTransientModifier(clawsSpeedModifier);
            } else {
                if (living.getAttribute(Attributes.ATTACK_DAMAGE) != null && living.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(clawsDamageModifier)) living.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(clawsDamageModifier);
                if (living.getAttribute(Attributes.ATTACK_SPEED) != null && living.getAttribute(Attributes.ATTACK_SPEED).hasModifier(clawsSpeedModifier)) living.getAttribute(Attributes.ATTACK_SPEED).removeModifier(clawsSpeedModifier);
            }
        }
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.wolverineSuit(type);
    }
}
