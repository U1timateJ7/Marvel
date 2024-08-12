package net.tintankgames.marvel.world.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;

import java.util.function.Predicate;

public class PhotonBlastItem extends SuitPowerItem {
    public PhotonBlastItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (!level.isClientSide) level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.CAPTAIN_MARVEL_PHOTON_BLAST.get(), SoundSource.PLAYERS);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity living, ItemStack stack, int ticksLeft) {
        super.onUseTick(level, living, stack, ticksLeft);
        if (living instanceof Player player) {
            if (!level.isClientSide) {
                HitResult hit = getHitResult(player, entity1 -> entity1 instanceof LivingEntity, 100f, 0f);
                if (hit instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity living1) {
                    living1.knockback(0.5, player.getX() - living1.getX(), player.getZ() - living1.getZ());
                    living1.hurt(player.damageSources().source(MarvelDamageTypes.PHOTON_BLAST, player), 5);
                }
                for (double d = 0; d < Math.abs(player.position().add(0, 1.25, 0).distanceTo(hit.getLocation())); d += 0.1) {
                    Vec3 vec3 = player.position().add(0, 1.25, 0).add(player.getViewVector(0.0F).scale(d));
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(new EmissiveDustParticleOptions(SimpleWeightedRandomList.<Integer>builder().add(0xFFBD42, 6).add(0xFFE242, 3).add(0x80B9E5, 1).build(), 0.75f, 0.1F), vec3.x(), vec3.y(), vec3.z(), 1, 0, 0, 0, 0);
                    }
                }
                int ticksUsed = getUseDuration(stack, living) - ticksLeft;
                if (ticksUsed >= 52 && (ticksUsed - 52) % 32 == 0) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.CAPTAIN_MARVEL_PHOTON_BLAST_LOOP.get(), SoundSource.PLAYERS);
                }
            }
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int ticksLeft) {
        super.releaseUsing(stack, level, living, ticksLeft);
        if (living instanceof Player player) {
            player.awardStat(Stats.ITEM_USED.get(this));
            if (!player.isCreative()) {
                player.getCooldowns().addCooldown(this, Math.min(30, (getUseDuration(stack, living) - ticksLeft) / 5));
            }
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_41452_) {
        return UseAnim.CROSSBOW;
    }

    private static HitResult getHitResult(Entity entity, Predicate<Entity> predicate, double d, float rotation) {
        Vec3 vec33 = entity.position().add(0, 1.25, 0).add(entity.getViewVector(0.0F).yRot(rotation).scale(d));
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

    @Override
    public int getUseDuration(ItemStack p_43419_, LivingEntity living) {
        return 72000;
    }
}
