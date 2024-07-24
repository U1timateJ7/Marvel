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
import net.minecraft.world.damagesource.DamageSource;
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
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.entity.PartEntity;
import net.neoforged.neoforge.event.EventHooks;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.mixin.LivingEntityAccessor;
import net.tintankgames.marvel.mixin.PlayerAccessor;
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
                float f = player.isAutoSpinAttack() ? ((LivingEntityAccessor) player).getAutoSpinAttackDmg() : (float)player.getAttributeValue(Attributes.ATTACK_DAMAGE);
                ItemStack itemstack = player.getWeaponItem();
                DamageSource damagesource = player.damageSources().source(MarvelDamageTypes.QUICKSILVER_ATTACK, player);
                float f1 = ((PlayerAccessor) player).invokeGetEnchantedDamage(target, f, damagesource) - f;
                float f2 = player.getAttackStrengthScale(0.5F);
                f *= 0.2F + f2 * f2 * 0.8F;
                f1 *= f2;
                if (target.getType().is(EntityTypeTags.REDIRECTABLE_PROJECTILE)
                        && target instanceof Projectile projectile
                        && projectile.deflect(ProjectileDeflection.AIM_DEFLECT, player, player, true)) {
                    player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, player.getSoundSource());
                    return;
                }

                if (f > 0.0F || f1 > 0.0F) {
                    boolean flag4 = f2 > 0.9F;
                    boolean flag;
                    if (player.isSprinting() && flag4) {
                        player.level()
                                .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_KNOCKBACK, player.getSoundSource(), 1.0F, 1.0F);
                        flag = true;
                    } else {
                        flag = false;
                    }

                    f += itemstack.getItem().getAttackDamageBonus(target, f, damagesource);
                    boolean flag1 = flag4
                            && player.fallDistance > 0.0F
                            && !player.onGround()
                            && !player.onClimbable()
                            && !player.isInWater()
                            && !player.hasEffect(MobEffects.BLINDNESS)
                            && !player.isPassenger()
                            && target instanceof LivingEntity
                            && !player.isSprinting();
                    var critEvent = CommonHooks.fireCriticalHit(player, target, flag1, flag1 ? 1.5F : 1.0F);
                    flag1 = critEvent.isCriticalHit();
                    if (flag1) {
                        f *= critEvent.getDamageMultiplier();
                    }

                    float f3 = f + f1;
                    boolean flag2 = false;
                    double d0 = player.walkDist - player.walkDistO;
                    if (flag4 && !flag1 && !flag && player.onGround() && d0 < (double)player.getSpeed()) {
                        ItemStack itemstack1 = player.getItemInHand(InteractionHand.MAIN_HAND);
                        flag2 = itemstack1.canPerformAction(ItemAbilities.SWORD_SWEEP);
                    }

                    float f6 = 0.0F;
                    if (target instanceof LivingEntity livingentity) {
                        f6 = livingentity.getHealth();
                    }

                    Vec3 vec3 = target.getDeltaMovement();
                    boolean flag3 = target.hurt(damagesource, f3);
                    if (flag3) {
                        float f4 = ((LivingEntityAccessor) player).invokeGetKnockback(target, damagesource) + (flag ? 1.0F : 0.0F);
                        if (f4 > 0.0F) {
                            if (target instanceof LivingEntity livingentity1) {
                                livingentity1.knockback(
                                        f4 * 0.5F,
                                        Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)),
                                        -Mth.cos(player.getYRot() * (float) (Math.PI / 180.0))
                                );
                            } else {
                                target.push(
                                        -Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)) * f4 * 0.5F,
                                        0.1,
                                        Mth.cos(player.getYRot() * (float) (Math.PI / 180.0)) * f4 * 0.5F
                                );
                            }

                            player.setDeltaMovement(player.getDeltaMovement().multiply(0.6, 1.0, 0.6));
                            player.setSprinting(false);
                        }

                        if (flag2) {
                            float f7 = 1.0F + (float)player.getAttributeValue(Attributes.SWEEPING_DAMAGE_RATIO) * f;

                            for (LivingEntity livingentity2 : player.level()
                                    .getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(1.0, 0.25, 1.0))) {
                                double entityReachSq = Mth.square(player.entityInteractionRange());
                                if (livingentity2 != player
                                        && livingentity2 != target
                                        && !player.isAlliedTo(livingentity2)
                                        && (!(livingentity2 instanceof ArmorStand) || !((ArmorStand)livingentity2).isMarker())
                                        && player.distanceToSqr(livingentity2) < entityReachSq) {
                                    float f5 = ((PlayerAccessor) player).invokeGetEnchantedDamage(livingentity2, f7, damagesource) * f2;
                                    livingentity2.knockback(
                                            0.4F,
                                            Mth.sin(player.getYRot() * (float) (Math.PI / 180.0)),
                                            -Mth.cos(player.getYRot() * (float) (Math.PI / 180.0))
                                    );
                                    livingentity2.hurt(damagesource, f5);
                                    if (player.level() instanceof ServerLevel serverlevel) {
                                        EnchantmentHelper.doPostAttackEffects(serverlevel, livingentity2, damagesource);
                                    }
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

                        if (flag1) {
                            player.level()
                                    .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_CRIT, player.getSoundSource(), 1.0F, 1.0F);
                            player.crit(target);
                        }

                        if (!flag1 && !flag2) {
                            if (flag4) {
                                player.level()
                                        .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, player.getSoundSource(), 1.0F, 1.0F);
                            } else {
                                player.level()
                                        .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_WEAK, player.getSoundSource(), 1.0F, 1.0F);
                            }
                        }

                        if (f1 > 0.0F) {
                            player.magicCrit(target);
                        }

                        player.setLastHurtMob(target);
                        Entity entity = target;
                        if (target instanceof PartEntity<?>) {
                            entity = ((PartEntity<?>) target).getParent();
                        }

                        boolean flag5 = false;
                        ItemStack copy = itemstack.copy();
                        if (player.level() instanceof ServerLevel serverlevel1) {
                            if (entity instanceof LivingEntity livingentity3) {
                                flag5 = itemstack.hurtEnemy(livingentity3, player);
                            }

                            EnchantmentHelper.doPostAttackEffects(serverlevel1, target, damagesource);
                        }

                        if (!player.level().isClientSide && !itemstack.isEmpty() && entity instanceof LivingEntity) {
                            if (flag5) {
                                itemstack.postHurtEnemy((LivingEntity)entity, player);
                            }

                            if (itemstack.isEmpty()) {
                                EventHooks.onPlayerDestroyItem(player, copy, itemstack == player.getMainHandItem() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND);
                                if (itemstack == player.getMainHandItem()) {
                                    player.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                                } else {
                                    player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                                }
                            }
                        }

                        if (target instanceof LivingEntity) {
                            float f8 = f6 - ((LivingEntity)target).getHealth();
                            player.awardStat(Stats.DAMAGE_DEALT, Math.round(f8 * 10.0F));
                            if (player.level() instanceof ServerLevel && f8 > 2.0F) {
                                int i = (int)((double)f8 * 0.5);
                                ((ServerLevel)player.level())
                                        .sendParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getY(0.5), target.getZ(), i, 0.1, 0.0, 0.1, 0.2);
                            }
                        }

                        player.causeFoodExhaustion(0.1F);
                    } else {
                        player.level()
                                .playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_NODAMAGE, player.getSoundSource(), 1.0F, 1.0F);
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
            ResourceKey<Level> dimension = ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse(tag.getString("dimension")));
            boolean swingOffhand = tag.getBoolean("swing_offhand");
            boolean bypassInvulnerability = tag.getBoolean("bypass_invulnerability");
            return new MultiAttackCallback(source, target, dimension, swingOffhand, bypassInvulnerability);
        }
    }
}
