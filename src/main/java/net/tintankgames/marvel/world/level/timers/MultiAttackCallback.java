package net.tintankgames.marvel.world.level.timers;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.timers.TimerCallback;
import net.minecraft.world.level.timers.TimerQueue;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.common.ToolActions;
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.event.EventHooks;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;

import java.util.UUID;

public class MultiAttackCallback implements TimerCallback<MinecraftServer> {
    final UUID source;
    final UUID target;
    final ResourceKey<Level> dimension;
    final boolean swingOffhand;
    final boolean bypassInvulnerability;

    public MultiAttackCallback(UUID source, UUID target, ResourceKey<Level> dimension, boolean swingOffhand, boolean bypassInvulnerability) {
        this.source = source;
        this.target = target;
        this.dimension = dimension;
        this.swingOffhand = swingOffhand;
        this.bypassInvulnerability = bypassInvulnerability;
    }

    public void handle(MinecraftServer p_82172_, TimerQueue<MinecraftServer> p_82173_, long p_82174_) {
        Player sourceEntity = p_82172_.getPlayerList().getPlayer(source);
        Entity targetEntity = p_82172_.getLevel(dimension).getEntity(target);
        if (sourceEntity != null && targetEntity != null && sourceEntity.isAlive() && targetEntity.isAlive()) {
            if (bypassInvulnerability) attack(sourceEntity, targetEntity);
            else sourceEntity.attack(targetEntity);
            sourceEntity.swing(swingOffhand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND, true);
        }
    }

    public void attack(Player player, Entity target) {
        if (!CommonHooks.onPlayerAttackTarget(player, target)) return;
        if (target.isAttackable()) {
            if (!target.skipAttackInteraction(player)) {
                float f = (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                float f1 = EnchantmentHelper.getDamageBonus(player.getMainHandItem(), target.getType());
                float f2 = player.getAttackStrengthScale(0.5F);
                f *= 0.2F + f2 * f2 * 0.8F;
                f1 *= f2;
                if (target.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE) && target instanceof Projectile projectile) {
                    projectile.deflect(ProjectileDeflection.AIM_DEFLECT, player, player, true);
                } else {
                    if (f > 0.0F || f1 > 0.0F) {
                        boolean flag = f2 > 0.9F;
                        boolean flag1 = false;
                        float i = (float)player.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                        i += EnchantmentHelper.getKnockbackBonus(player);
                        if (player.isSprinting() && flag) {
                            player.level()
                                    .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, player.getSoundSource(), 1.0F, 1.0F);
                            i++;
                            flag1 = true;
                        }

                        f += player.getItemInHand(InteractionHand.MAIN_HAND).getItem().getAttackDamageBonus(player, f);
                        boolean flag2 = flag
                                && player.fallDistance > 0.0F
                                && !player.onGround()
                                && !player.onClimbable()
                                && !player.isInWater()
                                && !player.hasEffect(MobEffects.BLINDNESS)
                                && !player.isPassenger()
                                && target instanceof LivingEntity
                                && !player.isSprinting();
                        var critEvent = CommonHooks.fireCriticalHit(player, target, flag2, flag2 ? 1.5F : 1.0F);
                        flag2 = critEvent.isCriticalHit();
                        if (flag2) {
                            f *= critEvent.getDamageMultiplier();
                        }

                        f += f1;
                        boolean flag3 = false;
                        double d0 = player.walkDist - player.walkDistO;
                        if (flag && !flag2 && !flag1 && player.onGround() && d0 < (double)player.getSpeed()) {
                            ItemStack itemstack = player.getItemInHand(InteractionHand.MAIN_HAND);
                            flag3 = itemstack.canPerformAction(ToolActions.SWORD_SWEEP);
                        }

                        float f4 = 0.0F;
                        boolean flag4 = false;
                        int j = EnchantmentHelper.getFireAspect(player);
                        if (target instanceof LivingEntity) {
                            f4 = ((LivingEntity)target).getHealth();
                            if (j > 0 && !target.isOnFire()) {
                                flag4 = true;
                                target.igniteForSeconds(1);
                            }
                        }

                        Vec3 vec3 = target.getDeltaMovement();
                        boolean flag5 = target.hurt(player.damageSources().source(MarvelDamageTypes.QUICKSILVER_ATTACK, player), f);
                        if (flag5) {
                            if (i > 0) {
                                if (target instanceof LivingEntity) {
                                    ((LivingEntity)target)
                                            .knockback(
                                                    i * 0.5F,
                                                    Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)),
                                                    -Mth.cos(player.getYRot() * (float) (Math.PI / 180.0))
                                            );
                                } else {
                                    target.push(
                                            -Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)) * i * 0.5F,
                                            0.1,
                                            Mth.cos(player.getYRot() * (float) (Math.PI / 180.0)) * i * 0.5F
                                    );
                                }

                                player.setDeltaMovement(player.getDeltaMovement().multiply(0.6, 1.0, 0.6));
                                player.setSprinting(false);
                            }

                            if (flag3) {
                                float f3 = 1.0F + EnchantmentHelper.getSweepingDamageRatio(player) * f;

                                for (LivingEntity livingentity : player.level()
                                        .getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0, 0.25, 1.0))) {
                                    double entityReachSq = Mth.square(player.entityInteractionRange()); // Use entity reach instead of constant 9.0. Vanilla uses bottom center-to-center checks here, so don't update player to use canReach, since it uses closest-corner checks.
                                    if (livingentity != player
                                            && livingentity != target
                                            && !player.isAlliedTo(livingentity)
                                            && (!(livingentity instanceof ArmorStand) || !((ArmorStand)livingentity).isMarker())
                                            && player.distanceToSqr(livingentity) < entityReachSq) {
                                        livingentity.knockback(
                                                0.4F,
                                                Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)),
                                                -Mth.cos(player.getYRot() * (float) (Math.PI / 180.0))
                                        );
                                        livingentity.hurt(player.damageSources().playerAttack(player), f3);
                                    }
                                }

                                player.level()
                                        .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(), 1.0F, 1.0F);
                                player.sweepAttack();
                            }

                            if (target instanceof ServerPlayer && target.hurtMarked) {
                                ((ServerPlayer)target).connection.send(new ClientboundSetEntityMotionPacket(target));
                                target.hurtMarked = false;
                                target.setDeltaMovement(vec3);
                            }

                            if (flag2) {
                                player.level()
                                        .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                                player.crit(target);
                            }

                            if (!flag2 && !flag3) {
                                if (flag) {
                                    player.level()
                                            .playSound(
                                                    null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, player.getSoundSource(), 1.0F, 1.0F
                                            );
                                } else {
                                    player.level()
                                            .playSound(
                                                    null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, player.getSoundSource(), 1.0F, 1.0F
                                            );
                                }
                            }

                            if (f1 > 0.0F) {
                                player.magicCrit(target);
                            }

                            player.setLastHurtMob(target);
                            if (target instanceof LivingEntity) {
                                EnchantmentHelper.doPostHurtEffects((LivingEntity)target, player);
                            }

                            EnchantmentHelper.doPostDamageEffects(player, target);
                            ItemStack itemstack1 = player.getMainHandItem();
                            Entity entity = target;
                            if (target instanceof PartEntity<?>) {
                                entity = ((PartEntity<?>) target).getParent();
                            }

                            if (!player.level().isClientSide && !itemstack1.isEmpty() && entity instanceof LivingEntity) {
                                ItemStack copy = itemstack1.copy();
                                itemstack1.hurtEnemy((LivingEntity)entity, player);
                                if (itemstack1.isEmpty()) {
                                    EventHooks.onPlayerDestroyItem(player, copy, InteractionHand.MAIN_HAND);
                                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                                }
                            }

                            if (target instanceof LivingEntity) {
                                float f5 = f4 - ((LivingEntity)target).getHealth();
                                player.awardStat(Stats.DAMAGE_DEALT, Math.round(f5 * 10.0F));
                                if (j > 0) {
                                    target.igniteForSeconds(j * 4);
                                }

                                if (player.level() instanceof ServerLevel && f5 > 2.0F) {
                                    int k = (int)((double)f5 * 0.5);
                                    ((ServerLevel)player.level())
                                            .sendParticles(
                                                    ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY(0.5), target.getZ(), k, 0.1, 0.0, 0.1, 0.2
                                            );
                                }
                            }

                            player.causeFoodExhaustion(0.1F);
                        } else {
                            player.level()
                                    .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, player.getSoundSource(), 1.0F, 1.0F);
                            if (flag4) {
                                target.clearFire();
                            }
                        }
                    }
                }
                player.resetAttackStrengthTicker();
            }
        }
    }

    public static class Serializer extends TimerCallback.Serializer<MinecraftServer, MultiAttackCallback> {
        public Serializer() {
            super(MarvelSuperheroes.id("multi_attack"), MultiAttackCallback.class);
        }

        public void serialize(CompoundTag tag, MultiAttackCallback callback) {
            tag.putUUID("source", callback.source);
            tag.putUUID("target", callback.target);
            tag.putString("dimension", callback.dimension.location().toString());
            tag.putBoolean("swing_offhand", callback.swingOffhand);
            tag.putBoolean("bypass_invulnerability", callback.bypassInvulnerability);
        }

        public MultiAttackCallback deserialize(CompoundTag tag) {
            UUID source = tag.getUUID("source");
            UUID target = tag.getUUID("target");
            ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(tag.getString("dimension")));
            boolean swingOffhand = tag.getBoolean("swing_offhand");
            boolean bypassInvulnerability = tag.getBoolean("bypass_invulnerability");
            return new MultiAttackCallback(source, target, dimension, swingOffhand, bypassInvulnerability);
        }
    }
}
