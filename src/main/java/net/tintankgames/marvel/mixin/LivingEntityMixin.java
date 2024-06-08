package net.tintankgames.marvel.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getMainHandItem();
    @Shadow public abstract ItemStack getOffhandItem();

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At("HEAD"), method = "isDamageSourceBlocked", cancellable = true)
    private void shieldBlock(DamageSource p_21276_, CallbackInfoReturnable<Boolean> cir) {
        if (p_21276_.is(DamageTypeTags.IS_PROJECTILE)) {
            if (processHand(this.getMainHandItem(), p_21276_.getDirectEntity()) || processHand(getOffhandItem(), p_21276_.getDirectEntity())) {
                Vec3 vec32 = p_21276_.getSourcePosition();
                if (vec32 != null) {
                    Vec3 vec3 = this.calculateViewVector(0.0F, this.getYHeadRot());
                    Vec3 vec31 = vec32.vectorTo(this.position());
                    vec31 = new Vec3(vec31.x, 0.0, vec31.z).normalize();
                    cir.setReturnValue(vec31.dot(vec3) < 0.0);
                }
            }
        }
    }

    private static boolean processHand(ItemStack stack, Entity source) {
        if (stack.getItem() instanceof VibraniumShieldItem) {
            if (!stack.isDamageableItem()) {
                return true;
            } else {
                return source.getType().is(MarvelEntityTypes.Tags.BLOCKED_BY_VIBRANIUM_SHIELD);
            }
        }
        return false;
    }
}
