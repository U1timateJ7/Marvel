package net.tintankgames.marvel.world.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.core.particles.EmissiveDustParticleOptions;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;

import java.util.function.Predicate;

public class UnibeamItem extends SuitPowerItem {
    public UnibeamItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 7.5F || player.isCreative()) {
            player.startUsingItem(hand);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_UNIBEAM_CHARGE.get(), SoundSource.PLAYERS);
            return InteractionResultHolder.consume(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int ticksLeft) {
        super.releaseUsing(stack, level, living, ticksLeft);
        int i = this.getUseDuration(stack, living) - ticksLeft;
        if (living instanceof Player player && i >= 20) {
            if (!level.isClientSide && (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 7.5F || player.isCreative())) {
                HitResult hit = getHitResult(player, entity1 -> entity1 instanceof LivingEntity, 100f, 0f);
                if (hit.getType() != HitResult.Type.MISS) {
                    level.explode(player, hit.getLocation().x(), hit.getLocation().y(), hit.getLocation().z(), 3.0F * (player.getItemBySlot(EquipmentSlot.CHEST).is(MarvelItems.Tags.IRON_MAN_MARK_17_ARMOR) ? 2.0F : 1.0F), Level.ExplosionInteraction.MOB);
                }
                for (double d = 0; d < Math.abs(player.position().add(0, 1.25, 0).distanceTo(hit.getLocation())); d += 0.1) {
                    Vec3 vec3 = player.position().add(0, 1.25, 0).add(player.getViewVector(0.0F).scale(d));
                    if (level instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(new EmissiveDustParticleOptions(0xAEF7FC, 1.0f), vec3.x(), vec3.y(), vec3.z(), 1, 0, 0, 0, 0);
                    }
                }
                level.playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_UNIBEAM_SHOOT.get(), SoundSource.PLAYERS);
                if (!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 100);
                    player.getInventory().armor.forEach(stack1 -> EnergySuitItem.removeEnergy(stack1, 7.5F));
                }
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
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
