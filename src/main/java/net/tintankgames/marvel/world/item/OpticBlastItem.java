package net.tintankgames.marvel.world.item;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.tintankgames.marvel.MarvelConfig;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.item.component.OpticBlastMode;
import net.tintankgames.marvel.world.level.MarvelGameRules;
import net.tintankgames.marvel.world.level.OpticBlastExplosionDamageCalculator;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class OpticBlastItem extends SuitPowerItem {
    public OpticBlastItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (player.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.OPTIC_BLAST_MODE, OpticBlastMode.NARROW) == OpticBlastMode.NARROW) {
            if (!level.isClientSide()) level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.CYCLOPS_OPTIC_BLAST.get(), SoundSource.PLAYERS);
            HitResult hit = getHitResult(player, entity1 -> entity1 instanceof LivingEntity, 100f, 0f);
            if (hit instanceof EntityHitResult entityHitResult && entityHitResult.getEntity() instanceof LivingEntity living) {
                living.knockback(0.5, player.getX() - living.getX(), player.getZ() - living.getZ());
                living.hurt(living.damageSources().source(MarvelConfig.goofyOpticBlast ? MarvelDamageTypes.OPTIC_BLAST_GOOFY : MarvelDamageTypes.OPTIC_BLAST, player), !MarvelConfig.goofyOpticBlast ? 6.0F : 20.0F);
                if (!level.isClientSide) {
                    level.explode(player, player.damageSources().source(MarvelConfig.goofyOpticBlast ? MarvelDamageTypes.OPTIC_BLAST_GOOFY : MarvelDamageTypes.OPTIC_BLAST, player), new OpticBlastExplosionDamageCalculator(), hit.getLocation().x(), hit.getLocation().y(), hit.getLocation().z(), 0, false, Level.ExplosionInteraction.TNT, ParticleTypes.EXPLOSION, ParticleTypes.EXPLOSION_EMITTER, MarvelSoundEvents.CYCLOPS_OPTIC_BLAST_EXPLOSION);
                }
            }
            if (hit instanceof BlockHitResult blockHitResult && blockHitResult.getType() != HitResult.Type.MISS && !level.isClientSide) {
                level.explode(player, player.damageSources().source(MarvelConfig.goofyOpticBlast ? MarvelDamageTypes.OPTIC_BLAST_GOOFY : MarvelDamageTypes.OPTIC_BLAST, player), new OpticBlastExplosionDamageCalculator(), hit.getLocation().x(), hit.getLocation().y(), hit.getLocation().z(), level.getGameRules().getBoolean(MarvelGameRules.RULE_SUPERPOWERGRIEFING) ? 1 : 0, false, Level.ExplosionInteraction.TNT, ParticleTypes.EXPLOSION, ParticleTypes.EXPLOSION_EMITTER, MarvelSoundEvents.CYCLOPS_OPTIC_BLAST_EXPLOSION);
            }
            for (double d = 0; d < Math.abs(player.getEyePosition().distanceTo(hit.getLocation())); d += 0.1) {
                Vec3 vec3 = player.getEyePosition().add(player.getViewVector(0.0F).scale(d));
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(new EmissiveDustParticleOptions(new Vector3f(0.98823529f, 0.01176471f, 0.09019608f), 0.5f), vec3.x(), vec3.y(), vec3.z(), 1, 0, 0, 0, 0);
                }
            }
            if (!player.onGround()) {
                player.addDeltaMovement(player.getViewVector(0.0F).reverse());
                player.fallDistance /= 4;
            }
            if (!MarvelConfig.goofyOpticBlast) player.getCooldowns().addCooldown(this, 15);
        } else if (player.getItemBySlot(EquipmentSlot.HEAD).getOrDefault(MarvelDataComponents.OPTIC_BLAST_MODE, OpticBlastMode.NARROW) == OpticBlastMode.WIDE) {
            if (!level.isClientSide()) level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.CYCLOPS_OPTIC_BLAST_BIG.get(), SoundSource.PLAYERS);
            for (float f = -45 * Mth.DEG_TO_RAD; f < 45 * Mth.DEG_TO_RAD; f += 0.025F) {
                for (LivingEntity living : getEntitiesInSight(player, 15f, f)) {
                    living.knockback(1.0, player.getX() - living.getX(), player.getZ() - living.getZ());
                    living.hurt(living.damageSources().source(MarvelConfig.goofyOpticBlast ? MarvelDamageTypes.OPTIC_BLAST_GOOFY : MarvelDamageTypes.OPTIC_BLAST, player), !MarvelConfig.goofyOpticBlast ? 4.0F : 20.0F);
                }
                for (double d = 0; d < 15; d += 0.1) {
                    Vec3 vec3 = player.getEyePosition().add(player.getViewVector(0.0F).yRot(f).scale(d));
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(new EmissiveDustParticleOptions(new Vector3f(0.98823529f, 0.01176471f, 0.09019608f), 0.5f), vec3.x(), vec3.y(), vec3.z(), 1, 0, 0, 0, 0);
                    }
                }
            }
            if (!player.onGround()) {
                player.addDeltaMovement(player.getViewVector(0.0F).reverse());
                player.fallDistance /= 8;
            }
            if (!MarvelConfig.goofyOpticBlast) player.getCooldowns().addCooldown(this, 50);
        }
        return InteractionResultHolder.consume(itemstack);
    }

    private static HitResult getHitResult(Entity entity, Predicate<Entity> predicate, double d, float rotation) {
        Vec3 vec33 = entity.getEyePosition().add(entity.getViewVector(0.0F).yRot(rotation).scale(d));
        HitResult hitResult = entity.level().clip(new ClipContext(entity.getEyePosition(), vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        if (hitResult.getType() != HitResult.Type.MISS) {
            vec33 = hitResult.getLocation();
        }

        HitResult hitResult2 = ProjectileUtil.getEntityHitResult(entity.level(), entity, entity.getEyePosition(), vec33, entity.getBoundingBox().expandTowards(entity.getViewVector(0.0F).scale(d)).inflate(1.0), predicate);
        if (hitResult2 != null) {
            hitResult = hitResult2;
        }

        return hitResult;
    }

    private static List<LivingEntity> getEntitiesInSight(Entity entity, double d, float rotation) {
        Vec3 vec33 = entity.getEyePosition().add(entity.getViewVector(0.0F).yRot(rotation).scale(d));
        HitResult hitResult = entity.level().clip(new ClipContext(entity.getEyePosition(), vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        if (hitResult.getType() != HitResult.Type.MISS) {
            vec33 = hitResult.getLocation();
        }
        return getEntities(entity.level(), entity, entity.getEyePosition(), vec33, entity.getBoundingBox().expandTowards(entity.getViewVector(0.0F).scale(d)).inflate(1.0), entity1 -> entity1 instanceof LivingEntity).stream().map(entity1 -> (LivingEntity) entity1).toList();
    }

    public static List<Entity> getEntities(Level p_150176_, Entity p_150177_, Vec3 p_150178_, Vec3 p_150179_, AABB p_150180_, Predicate<Entity> p_150181_) {
        double d0 = Double.MAX_VALUE;
        List<Entity> entities = new ArrayList<>();

        for (Entity entity : p_150176_.getEntities(p_150177_, p_150180_, p_150181_)) {
            AABB aabb = entity.getBoundingBox().inflate(0.3);
            Optional<Vec3> optional = aabb.clip(p_150178_, p_150179_);
            if (optional.isPresent()) {
                double d1 = p_150178_.distanceToSqr(optional.get());
                if (d1 < d0) {
                    entities.add(entity);
                    d0 = d1;
                }
            }
        }

        return entities;
    }
}
