package net.tintankgames.marvel.world.item;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class ShoulderTurretItem extends ProjectileWeaponItem {
    public ShoulderTurretItem(Properties properties) {
        super(properties.stacksTo(1));
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return ARROW_OR_FIREWORK;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 8;
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
        return 1;
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (p_41406_.getType() != EntityType.PLAYER) p_41404_.shrink(1);
    }

    @Override
    public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) {
        return true;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 0.2F || player.isCreative()) {
            if (!player.getProjectile(stack).isEmpty()) {
                player.startUsingItem(hand);
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.CROSSBOW_LOADING_END.value(), SoundSource.PLAYERS);
                return InteractionResultHolder.consume(stack);
            } else {
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.DISPENSER_FAIL, SoundSource.PLAYERS);
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity living, int ticksLeft) {
        super.releaseUsing(stack, level, living, ticksLeft);
        if (living instanceof Player player) {
            if (!level.isClientSide && (EnergySuitItem.getEnergy(player.getItemBySlot(EquipmentSlot.CHEST)) >= 0.2F || player.isCreative())) {
                performShooting(level, player, living.swingingArm, stack, player.getProjectile(stack).is(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F, 1.0F, null);
                if (!player.isCreative()) {
                    player.getCooldowns().addCooldown(this, 5);
                    player.getInventory().armor.forEach(stack1 -> EnergySuitItem.removeEnergy(stack1, 0.2F));
                }
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    public void performShooting(Level level, LivingEntity living, InteractionHand hand, ItemStack stack, float power, float f, @Nullable LivingEntity entity) {
        if (level instanceof ServerLevel serverlevel) {
            if (living instanceof Player player && EventHooks.onArrowLoose(stack, living.level(), player, 1, true) < 0) return;
            ItemStack ammo = living.getProjectile(stack);
            if (!ammo.isEmpty()) {
                this.shoot(serverlevel, living, hand, stack, draw(stack, ammo, living), power, f, living instanceof Player, entity);
                if (living instanceof ServerPlayer serverplayer) {
                    CriteriaTriggers.SHOT_CROSSBOW.trigger(serverplayer, stack);
                    serverplayer.awardStat(Stats.ITEM_USED.get(stack.getItem()));
                }
            }
        }
    }

    @Override
    protected void shootProjectile(LivingEntity living, Projectile projectile, int i, float power, float spread, float f4, @Nullable LivingEntity entity) {
        Vector3f vector3f;
        if (entity != null) {
            double d0 = entity.getX() - living.getX();
            double d1 = entity.getZ() - living.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            double d3 = entity.getY(0.3333333333333333) - projectile.getY() + d2 * 0.2F;
            vector3f = getProjectileShotVector(living, new Vec3(d0, d3, d1), f4);
        } else {
            Vec3 vec3 = living.getUpVector(1.0F);
            Quaternionf quaternionf = new Quaternionf().setAngleAxis(f4 * (float) (Math.PI / 180.0), vec3.x, vec3.y, vec3.z);
            Vec3 vec31 = living.getViewVector(1.0F);
            vector3f = vec31.toVector3f().rotate(quaternionf);
        }

        projectile.shoot(vector3f.x(), vector3f.y(), vector3f.z(), power, spread);
        float f = getShotPitch(living.getRandom(), i);
        living.level().playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.CROSSBOW_SHOOT, living.getSoundSource(), 1.0F, f);
    }

    private static Vector3f getProjectileShotVector(LivingEntity living, Vec3 vec, float f) {
        Vector3f vector3f = vec.toVector3f().normalize();
        Vector3f vector3f1 = new Vector3f(vector3f).cross(new Vector3f(0.0F, 1.0F, 0.0F));
        if ((double)vector3f1.lengthSquared() <= 1.0E-7) {
            Vec3 vec3 = living.getUpVector(1.0F);
            vector3f1 = new Vector3f(vector3f).cross(vec3.toVector3f());
        }

        Vector3f vector3f2 = new Vector3f(vector3f).rotateAxis((float) (Math.PI / 2), vector3f1.x, vector3f1.y, vector3f1.z);
        return new Vector3f(vector3f).rotateAxis(f * (float) (Math.PI / 180.0), vector3f2.x, vector3f2.y, vector3f2.z);
    }

    private static float getShotPitch(RandomSource randomSource, int i) {
        return i == 0 ? 1.0F : getRandomShotPitch((i & 1) == 1, randomSource);
    }

    private static float getRandomShotPitch(boolean bl, RandomSource randomSource) {
        float f = bl ? 0.63F : 0.43F;
        return 1.0F / (randomSource.nextFloat() * 0.5F + 1.8F) + f;
    }

    @Override
    protected Projectile createProjectile(Level level, LivingEntity living, ItemStack stack, ItemStack ammo, boolean bl) {
        if (ammo.is(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(level, ammo, living, living.getX(), living.getEyeY() - 0.15F, living.getZ(), true);
        } else {
            Projectile projectile = super.createProjectile(level, living, stack, ammo, bl);
            if (projectile instanceof AbstractArrow abstractarrow) {
                abstractarrow.setSoundEvent(SoundEvents.CROSSBOW_HIT);
            }

            return projectile;
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity living) {
        return 72000;
    }
}
