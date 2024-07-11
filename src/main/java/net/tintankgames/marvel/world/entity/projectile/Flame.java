package net.tintankgames.marvel.world.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Flame extends AbstractArrow {
    public Flame(EntityType<? extends Flame> p_37561_, Level p_37562_) {
        super(p_37561_, p_37562_);
    }

    public Flame(Level p_37569_, LivingEntity p_37570_, ItemStack p_37571_, @Nullable ItemStack firedFrom) {
        super(MarvelEntityTypes.FLAME.get(), p_37570_, p_37569_, p_37571_, firedFrom);
    }

    public Flame(Level p_338686_, double p_338771_, double p_338674_, double p_338477_, ItemStack p_338255_, @Nullable ItemStack firedFrom) {
        super(MarvelEntityTypes.FLAME.get(), p_338771_, p_338674_, p_338477_, p_338686_, p_338255_, firedFrom);
    }

    @Override
    public void shoot(double p_36775_, double p_36776_, double p_36777_, float p_36778_, float p_36779_) {
        super.shoot(p_36775_, p_36776_, p_36777_, p_36778_, p_36779_);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        BlockState blockstate = this.level().getBlockState(blockHitResult.getBlockPos());
        blockstate.onProjectileHit(this.level(), blockstate, blockHitResult, this);
        if (!isRemoved()) {
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        if ((x == 0 && z == 0) || (x != 0 && y == 0 && z == 0) || (x == 0 && y == 0 && z != 0)) {
                            BlockPos pos = blockHitResult.getBlockPos().offset(x, y + 1, z);
                            BlockState blockstate1 = this.level().getBlockState(pos);
                            BlockState blockstate2 = this.level().getBlockState(pos.below());
                            if (blockstate1.canBeReplaced() && !blockstate2.canBeReplaced()) {
                                level().setBlockAndUpdate(pos, Objects.requireNonNull(Blocks.FIRE.getStateForPlacement(new BlockPlaceContext(level(), getOwner() instanceof Player player ? player : null, InteractionHand.MAIN_HAND, new ItemStack(Items.FLINT_AND_STEEL), new BlockHitResult(Vec3.atBottomCenterOf(pos), blockHitResult.getDirection(), pos, false)))));
                            }
                        }
                    }
                }
            }
        }
        discard();
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = 2;
        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().source(MarvelDamageTypes.FLAMETHROWER, this, entity1 == null ? this : entity1);
        if (getWeaponItem() != null && this.level() instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, getWeaponItem(), entity, damagesource, f);
        }
        if (!isRemoved()) {
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        BlockPos pos = entity.blockPosition().offset(x, y + 1, z);
                        BlockState blockstate1 = this.level().getBlockState(pos);
                        BlockState blockstate2 = this.level().getBlockState(pos.below());
                        if (blockstate1.canBeReplaced() && !blockstate2.canBeReplaced()) {
                            level().setBlockAndUpdate(pos, Objects.requireNonNull(Blocks.FIRE.getStateForPlacement(new BlockPlaceContext(level(), getOwner() instanceof Player player ? player : null, InteractionHand.MAIN_HAND, new ItemStack(Items.FLINT_AND_STEEL), new BlockHitResult(Vec3.atBottomCenterOf(pos), Direction.UP, pos, false)))));
                        }
                    }
                }
            }
        }
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            entity.igniteForSeconds(4);
            if (this.level() instanceof ServerLevel serverlevel1) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, entity, damagesource, this.getWeaponItem());
            }
            if (entity instanceof LivingEntity livingentity) {
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
            }
        }
        discard();
    }

    @Override
    public void tick() {
        super.tick();
        if (!isInWater()) {
            for (int j = 0; j < 4; j++) {
                this.level().addParticle(MarvelParticleTypes.EMISSIVE_FLAME.get(), this.getX(), this.getY(), this.getZ(), 0, 0, 0);
            }
        } else {
            discard();
        }
    }

    @Override
    protected boolean tryPickup(Player p_150196_) {
        return false;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean shouldRender(double p_20296_, double p_20297_, double p_20298_) {
        return false;
    }
}
