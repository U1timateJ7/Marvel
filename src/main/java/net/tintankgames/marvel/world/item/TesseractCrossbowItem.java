package net.tintankgames.marvel.world.item;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class TesseractCrossbowItem extends CrossbowItem {
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;

    public TesseractCrossbowItem(Properties properties) {
        super(properties);
    }

    @Override
    protected void shootProjectile(LivingEntity p_40896_, Projectile p_332122_, int p_331865_, float p_40900_, float p_40902_, float p_40903_, @Nullable LivingEntity p_330303_) {
        Vector3f vector3f;
        if (p_330303_ != null) {
            double d0 = p_330303_.getX() - p_40896_.getX();
            double d1 = p_330303_.getZ() - p_40896_.getZ();
            double d2 = Math.sqrt(d0 * d0 + d1 * d1);
            double d3 = p_330303_.getY(0.3333333333333333) - p_332122_.getY() + d2 * 0.2F;
            vector3f = getProjectileShotVector(p_40896_, new Vec3(d0, d3, d1), p_40903_);
        } else {
            Vec3 vec3 = p_40896_.getUpVector(1.0F);
            Quaternionf quaternionf = new Quaternionf().setAngleAxis(p_40903_ * (float) (Math.PI / 180.0), vec3.x, vec3.y, vec3.z);
            Vec3 vec31 = p_40896_.getViewVector(1.0F);
            vector3f = vec31.toVector3f().rotate(quaternionf);
        }

        p_332122_.shoot(vector3f.x(), vector3f.y(), vector3f.z(), p_40900_, p_40902_);
        float f = getShotPitch(p_40896_.getRandom(), p_331865_);
        p_40896_.level().playSound(null, p_40896_.getX(), p_40896_.getY(), p_40896_.getZ(), MarvelSoundEvents.TESSERACT_CROSSBOW_SHOOT.get(), p_40896_.getSoundSource(), 1.0F, f);
    }

    @Override
    protected Projectile createProjectile(Level p_331583_, LivingEntity p_40863_, ItemStack p_40864_, ItemStack p_40865_, boolean p_40866_) {
        if (p_40865_.is(Items.FIREWORK_ROCKET)) {
            return new FireworkRocketEntity(p_331583_, p_40865_, p_40863_, p_40863_.getX(), p_40863_.getEyeY() - 0.15F, p_40863_.getZ(), true);
        } else {
            Projectile projectile = super.createProjectile(p_331583_, p_40863_, p_40864_, p_40865_, p_40866_);
            if (projectile instanceof AbstractArrow abstractarrow) {
                abstractarrow.setSoundEvent(MarvelSoundEvents.TESSERACT_CROSSBOW_HIT.get());
            }
            projectile.setData(MarvelAttachmentTypes.TESSERACT_CHARGED, true);

            return projectile;
        }
    }

    private static Vector3f getProjectileShotVector(LivingEntity p_331584_, Vec3 p_331590_, float p_331104_) {
        Vector3f vector3f = p_331590_.toVector3f().normalize();
        Vector3f vector3f1 = new Vector3f(vector3f).cross(new Vector3f(0.0F, 1.0F, 0.0F));
        if ((double)vector3f1.lengthSquared() <= 1.0E-7) {
            Vec3 vec3 = p_331584_.getUpVector(1.0F);
            vector3f1 = new Vector3f(vector3f).cross(vec3.toVector3f());
        }

        Vector3f vector3f2 = new Vector3f(vector3f).rotateAxis((float) (Math.PI / 2), vector3f1.x, vector3f1.y, vector3f1.z);
        return new Vector3f(vector3f).rotateAxis(p_331104_ * (float) (Math.PI / 180.0), vector3f2.x, vector3f2.y, vector3f2.z);
    }

    private static float getShotPitch(RandomSource p_331176_, int p_331542_) {
        return p_331542_ == 0 ? 1.0F : getRandomShotPitch((p_331542_ & 1) == 1, p_331176_);
    }

    private static float getRandomShotPitch(boolean p_220026_, RandomSource p_220027_) {
        float f = p_220026_ ? 0.63F : 0.43F;
        return 1.0F / (p_220027_.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static float getPowerForTime(int p_40854_, ItemStack p_40855_) {
        float f = (float)p_40854_ / (float)getChargeDuration(p_40855_);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    private static boolean tryLoadProjectiles(LivingEntity p_40860_, ItemStack p_40861_) {
        List<ItemStack> list = draw(p_40861_, p_40860_.getProjectile(p_40861_), p_40860_);
        if (!list.isEmpty()) {
            p_40861_.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.of(list));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void releaseUsing(ItemStack p_40875_, Level p_40876_, LivingEntity p_40877_, int p_40878_) {
        int i = this.getUseDuration(p_40875_) - p_40878_;
        float f = getPowerForTime(i, p_40875_);
        if (f >= 1.0F && !isCharged(p_40875_) && tryLoadProjectiles(p_40877_, p_40875_)) {
            p_40876_.playSound(
                    null,
                    p_40877_.getX(),
                    p_40877_.getY(),
                    p_40877_.getZ(),
                    MarvelSoundEvents.TESSERACT_CROSSBOW_LOADING_END.get(),
                    p_40877_.getSoundSource(),
                    1.0F,
                    1.0F / (p_40876_.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F
            );
        }
    }

    private static float getShootingPower(ChargedProjectiles p_330249_) {
        return p_330249_.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_40920_, Player p_40921_, InteractionHand p_40922_) {
        ItemStack itemstack = p_40921_.getItemInHand(p_40922_);
        ChargedProjectiles chargedprojectiles = itemstack.get(DataComponents.CHARGED_PROJECTILES);
        if (chargedprojectiles != null && !chargedprojectiles.isEmpty()) {
            this.performShooting(p_40920_, p_40921_, p_40922_, itemstack, getShootingPower(chargedprojectiles), 1.0F, null);
            return InteractionResultHolder.consume(itemstack);
        } else if (!p_40921_.getProjectile(itemstack).isEmpty()) {
            this.startSoundPlayed = false;
            this.midLoadSoundPlayed = false;
            p_40921_.startUsingItem(p_40922_);
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    @Override
    public void onUseTick(Level p_40910_, LivingEntity p_40911_, ItemStack p_40912_, int p_40913_) {
        if (!p_40910_.isClientSide) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, p_40912_);
            SoundEvent soundevent = getStartSound(i);
            SoundEvent soundevent1 = MarvelSoundEvents.TESSERACT_CROSSBOW_LOADING_MIDDLE.get();
            float f = (float)(p_40912_.getUseDuration() - p_40913_) / (float)getChargeDuration(p_40912_);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }

            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                p_40910_.playSound(null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                p_40910_.playSound(null, p_40911_.getX(), p_40911_.getY(), p_40911_.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    private SoundEvent getStartSound(int p_40852_) {
        return switch (p_40852_) {
            case 1 -> SoundEvents.CROSSBOW_QUICK_CHARGE_1;
            case 2 -> SoundEvents.CROSSBOW_QUICK_CHARGE_2;
            case 3 -> SoundEvents.CROSSBOW_QUICK_CHARGE_3;
            default -> MarvelSoundEvents.TESSERACT_CROSSBOW_LOADING_START.get();
        };
    }
}
