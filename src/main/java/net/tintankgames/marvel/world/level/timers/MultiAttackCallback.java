package net.tintankgames.marvel.world.level.timers;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.UUID;

public class MultiAttackCallback implements TimerCallback<MinecraftServer> {
    final UUID source;
    final UUID target;
    final float damage;
    final ResourceKey<DamageType> damageType;
    final ResourceKey<Level> dimension;
    final boolean swingOffhand;

    public MultiAttackCallback(UUID source, UUID target, float damage, ResourceKey<DamageType> damageType, ResourceKey<Level> dimension, boolean swingOffhand) {
        this.source = source;
        this.target = target;
        this.damage = damage;
        this.damageType = damageType;
        this.dimension = dimension;
        this.swingOffhand = swingOffhand;
    }

    public void handle(MinecraftServer p_82172_, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        Player sourceEntity = p_82172_.getPlayerList().getPlayer(source);
        Entity targetEntity = p_82172_.getLevel(dimension).getEntity(target);
        if (sourceEntity != null && targetEntity != null) {
            targetEntity.hurt(targetEntity.damageSources().source(damageType, sourceEntity), damage);
            sourceEntity.swing(swingOffhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, true);
        }
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, MultiAttackCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("multi_attack"), MultiAttackCallback.class);
        }

        public void serialize(CompoundTag tag, MultiAttackCallback callback) {
            tag.putUUID("source", callback.source);
            tag.putUUID("target", callback.target);
            tag.putFloat("damage", callback.damage);
            tag.putString("damage_type", callback.damageType.location().toString());
            tag.putString("dimension", callback.dimension.location().toString());
            tag.putBoolean("swing_offhand", callback.swingOffhand);
        }

        public MultiAttackCallback deserialize(CompoundTag tag) {
            UUID source = tag.getUUID("source");
            UUID target = tag.getUUID("target");
            float damage = tag.getFloat("damage");
            ResourceKey<DamageType> damageType = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(tag.getString("damage_type")));
            ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("dimension")));
            boolean swingOffhand = tag.getBoolean("swing_offhand");
            return new MultiAttackCallback(source, target, damage, damageType, dimension, swingOffhand);
        }
    }
}
