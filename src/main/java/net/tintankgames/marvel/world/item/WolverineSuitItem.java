package net.tintankgames.marvel.world.item;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;

@EventBusSubscriber
public class WolverineSuitItem extends LeatherSuitItem {
    public WolverineSuitItem(Type type, Properties properties) {
        super(type, MarvelItems.Tags.WOLVERINE_ARMOR, type == Type.CHESTPLATE ? List.of(effect(MobEffects.JUMP, 1), effect(MobEffects.MOVEMENT_SPEED, 0), effect(MobEffects.DAMAGE_RESISTANCE, 0)) : List.of(), properties.component(MarvelDataComponents.POWER_ITEMS, List.of(MarvelItems.ADAMANTIUM_CLAWS.get())));
    }

    @SubscribeEvent
    public static void livingTick(EntityTickEvent.Post event) {
        if (event.getEntity() instanceof LivingEntity living) {
            if (living instanceof ServerPlayer serverPlayer && serverPlayer.getHealth() < serverPlayer.getData(MarvelAttachmentTypes.HEALING_FACTOR_TRACKER)) {
                serverPlayer.setData(MarvelAttachmentTypes.HEALING_FACTOR_TRACKER, serverPlayer.getHealth());
            }
            if (living.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.LEGS).is(MarvelItems.Tags.WOLVERINE_ARMOR) && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.WOLVERINE_ARMOR)) {
                if (living.tickCount % 10 == 0) {
                    living.heal(1.0F);
                    if (living instanceof ServerPlayer serverPlayer && serverPlayer.getHealth() >= 20.0F && serverPlayer.getData(MarvelAttachmentTypes.HEALING_FACTOR_TRACKER) <= 1.0F) {
                        AdvancementHolder advancementHolder = serverPlayer.server.getAdvancements().get(MarvelSuperheroes.id("canadian_healthcare"));
                        if (advancementHolder != null) serverPlayer.getAdvancements().award(advancementHolder, "heal_from_one");
                        serverPlayer.setData(MarvelAttachmentTypes.HEALING_FACTOR_TRACKER, 20.0F);
                    }
                }
            }
        }
    }

    @Override
    protected ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.wolverineSuit(type);
    }
}
