package net.tintankgames.marvel.world.item;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.client.model.MarvelModels;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@EventBusSubscriber
public class ThorSuitItem extends SuitItem {
    public static final UUID THOR_UUID = UUID.fromString("7F13E047-8356-4941-84AF-BD7DB5A5562F");
    private static final AttributeModifier creativeFlightModifier = new AttributeModifier(THOR_UUID, "Thor creative flight modifier", 1, AttributeModifier.Operation.ADD_VALUE);

    public ThorSuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.UPGRADED_REINFORCED_LEATHER, type, MarvelItems.Tags.THOR_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0)) : List.of(), properties);
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.thorSuit(type);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Runnable onBroken) {
        return 0;
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.HEAD).is(MarvelItems.Tags.THOR_ARMOR) && player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.THOR_ARMOR) && player.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.THOR_ARMOR) && player.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.THOR_ARMOR)) {
                player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).addOrUpdateTransientModifier(creativeFlightModifier);
            } else {
                player.getAttribute(NeoForgeMod.CREATIVE_FLIGHT).removeModifier(creativeFlightModifier.id());
            }
        }
    }
}
