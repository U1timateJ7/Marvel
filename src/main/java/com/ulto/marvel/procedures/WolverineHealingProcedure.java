package com.ulto.marvel.procedures;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

@Mod.EventBusSubscriber
public class WolverineHealingProcedure {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Entity entity = event.player;
            execute(event, entity.level, entity);
        }
    }

    public static void execute(LevelAccessor world, Entity entity) {
        execute(null, world, entity);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity) {
        if (entity == null)
            return;
        if ((entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.CHEST) : ItemStack.EMPTY).is(MarvelModItems.WOLVERINE_SUIT_CHESTPLATE.get())
                && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.LEGS) : ItemStack.EMPTY).is(MarvelModItems.WOLVERINE_SUIT_LEGGINGS.get())
                && (entity instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.FEET) : ItemStack.EMPTY).is(MarvelModItems.WOLVERINE_SUIT_BOOTS.get())) {
            if (entity instanceof LivingEntity _entity)
                _entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 10, 1, (false), (false)));
            if (entity instanceof LivingEntity _entity)
                _entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 3, (false), (false)));
            if (entity instanceof LivingEntity _entity)
                _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 10, 0, (false), (false)));
        } else {
            if (entity instanceof Player player) {
                player.inventoryMenu.getItems().forEach(stack -> {
                    if (stack.is(MarvelModItems.ADAMANTIUM_CLAWS.get())) {
                        stack.shrink(stack.getCount());
                    }
                });
            }
        }
    }
}
