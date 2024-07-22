package net.tintankgames.marvel.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Unit;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.level.timers.MultiAttackCallback;

public class KatanasItem extends SwordItem {
    public KatanasItem(Properties properties) {
        super(MarvelTiers.KATANAS, properties.attributes(SwordItem.createAttributes(MarvelTiers.KATANAS, 3, -2.4F)));
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return itemStack.has(MarvelDataComponents.FAKE) ? 1 : super.getEntityLifespan(itemStack, level);
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return stack.has(MarvelDataComponents.IN_HAND) || stack.has(MarvelDataComponents.FAKE) ? BuiltInRegistries.ITEM.getKey(this).withPath(id -> id.replace("katanas", "katana")).toLanguageKey("item") : super.getDescriptionId(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, level, entity, slot, selected);
        if (entity instanceof ServerPlayer player) {
            if (slot == player.getInventory().selected) {
                ItemStack offhand = player.getOffhandItem().copy();
                if (!offhand.isEmpty() && !offhand.is(this)) {
                    player.getOffhandItem().setCount(offhand.getMaxStackSize());
                    if (!player.getInventory().add(offhand)) {
                        player.drop(player.getOffhandItem().copy(), true);
                    }
                }
                if (player.getMainHandItem().is(this) && player.getMainHandItem().has(MarvelDataComponents.FAKE) && player.getOffhandItem().is(this) && !player.getOffhandItem().has(MarvelDataComponents.FAKE)) {
                    player.getMainHandItem().remove(MarvelDataComponents.FAKE);
                    player.getMainHandItem().set(DataComponents.ATTRIBUTE_MODIFIERS, SwordItem.createAttributes(MarvelTiers.KATANAS, 3, -2.4F));
                    player.getOffhandItem().set(MarvelDataComponents.FAKE, Unit.INSTANCE);
                    player.getOffhandItem().set(DataComponents.ATTRIBUTE_MODIFIERS, SwordItem.createAttributes(MarvelTiers.KATANAS, 1, -2.4F));
                }
                ItemStack offhandItem = stack.copy();
                offhandItem.set(MarvelDataComponents.FAKE, Unit.INSTANCE);
                offhandItem.remove(MarvelDataComponents.IN_HAND);
                offhandItem.set(DataComponents.ATTRIBUTE_MODIFIERS, SwordItem.createAttributes(MarvelTiers.KATANAS, 1, -2.4F));
                player.setItemInHand(InteractionHand.OFF_HAND, offhandItem);
                stack.set(MarvelDataComponents.IN_HAND, Unit.INSTANCE);
            } else {
                stack.remove(MarvelDataComponents.IN_HAND);
            }
            if (stack.has(MarvelDataComponents.FAKE) && !player.getMainHandItem().is(this)) {
                stack.shrink(1);
            }
            if (stack.has(MarvelDataComponents.FAKE) && player.getMainHandItem().is(this) && player.getOffhandItem().is(this) && player.getOffhandItem() != stack) {
                stack.shrink(1);
            }
        }
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity source) {
        super.postHurtEnemy(stack, target, source);
        if (source instanceof ServerPlayer player) {
            if (!player.getCooldowns().isOnCooldown(this)) {
                TimerQueue<MinecraftServer> timerQueue = player.getServer().getWorldData().overworldData().getScheduledEvents();
                long i = player.serverLevel().getGameTime();
                timerQueue.schedule(source.getStringUUID() + "_katana_attack", i + 11, new MultiAttackCallback(player.getUUID(), target.getUUID(), 6, DamageTypes.PLAYER_ATTACK, target.level().dimension(), true));
            }
            player.getCooldowns().addCooldown(this, 12);
        }
    }
}
