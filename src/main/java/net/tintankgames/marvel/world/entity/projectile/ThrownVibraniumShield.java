package net.tintankgames.marvel.world.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.damagesources.MarvelDamageTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;

import org.jetbrains.annotations.Nullable;

public class ThrownVibraniumShield extends AbstractArrow {
    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownVibraniumShield.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> ID_ITEM = SynchedEntityData.defineId(ThrownVibraniumShield.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Boolean> ID_FROM_WINTER_SOLDIER = SynchedEntityData.defineId(ThrownVibraniumShield.class, EntityDataSerializers.BOOLEAN);
    private boolean dealtDamage;

    public ThrownVibraniumShield(EntityType<? extends ThrownVibraniumShield> p_37561_, Level p_37562_) {
        super(p_37561_, p_37562_);
    }

    public ThrownVibraniumShield(Level p_37569_, LivingEntity p_37570_, ItemStack p_37571_) {
        super(MarvelEntityTypes.VIBRANIUM_SHIELD.get(), p_37570_, p_37569_, p_37571_, null);
        this.entityData.set(ID_FOIL, p_37571_.hasFoil());
        this.entityData.set(ID_ITEM, p_37571_);
    }

    public ThrownVibraniumShield(Level p_338686_, double p_338771_, double p_338674_, double p_338477_, ItemStack p_338255_) {
        super(MarvelEntityTypes.VIBRANIUM_SHIELD.get(), p_338771_, p_338674_, p_338477_, p_338686_, p_338255_, null);
        this.entityData.set(ID_FOIL, p_338255_.hasFoil());
        this.entityData.set(ID_ITEM, p_338255_);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder p_326249_) {
        super.defineSynchedData(p_326249_);
        p_326249_.define(ID_FOIL, false);
        p_326249_.define(ID_ITEM, MarvelItems.VIBRANIUM_SHIELD.toStack());
        p_326249_.define(ID_FROM_WINTER_SOLDIER, false);
    }

    @Override
    public ItemStack getPickupItemStackOrigin() {
        return this.entityData.get(ID_ITEM);
    }

    @Override
    protected void setPickupItemStack(ItemStack p_331486_) {
        this.entityData.set(ID_ITEM, p_331486_);
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        this.lastState = this.level().getBlockState(blockHitResult.getBlockPos());
        BlockState blockstate = this.level().getBlockState(blockHitResult.getBlockPos());
        blockstate.onProjectileHit(this.level(), blockstate, blockHitResult, this);
        Vec3 vec3 = blockHitResult.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        Vec3 vec31 = vec3.normalize().scale(0.05F);
        this.setPosRaw(this.getX() - vec31.x, this.getY() - vec31.y, this.getZ() - vec31.z);
        this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        setDeltaMovement(getDeltaMovement().multiply(-1, -1, -1));
        if (isFromWinterSoldier()) {
            spawnAtLocation(getPickupItem());
            discard();
        }
    }

    public boolean returningToOwner() {
        return this.dealtDamage;
    }

    @Override
    public void tick() {
        if (this.inGroundTime > 0 || getY() <= level().dimensionType().minY() - 48) {
            this.dealtDamage = true;
        }

        Entity entity = this.getOwner();
        if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
            if (!this.isAcceptibleReturnOwner()) {
                if (!this.level().isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
                    this.spawnAtLocation(this.getPickupItem(), 0.1F);
                }

                this.discard();
            } else {
                this.setNoPhysics(true);
                Vec3 vec3 = entity.position().subtract(this.position());
                this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.045, this.getZ());
                if (this.level().isClientSide) {
                    this.yOld = this.getY();
                }

                this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(0.25)));
            }
        }

        super.tick();
    }

    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        return entity != null && entity.isAlive() && (!(entity instanceof ServerPlayer) || !entity.isSpectator());
    }

    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }

    public boolean isFromWinterSoldier() {
        return this.entityData.get(ID_FROM_WINTER_SOLDIER);
    }

    public void setFromWinterSoldier(boolean fromWinterSoldier) {
        this.entityData.set(ID_FROM_WINTER_SOLDIER, fromWinterSoldier);
    }

    public ItemStack getItem() {
        return this.entityData.get(ID_ITEM).copy();
    }

    @Nullable
    @Override
    protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
        return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
    }

    @Override
    protected void onHitEntity(EntityHitResult p_37573_) {
        Entity entity = p_37573_.getEntity();
        float f = (float) getBaseDamage();
        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().source(entity1 == null ? MarvelDamageTypes.VIBRANIUM_SHIELD_DISPENSER : MarvelDamageTypes.VIBRANIUM_SHIELD, this, entity1 == null ? this : entity1);
        if (this.level() instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, getWeaponItem(), entity, damagesource, f);
        }
        this.dealtDamage = true;
        SoundEvent soundevent = MarvelSoundEvents.VIBRANIUM_SHIELD_HIT.get();
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }
            if (this.level() instanceof ServerLevel serverlevel1) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel1, entity, damagesource, this.getWeaponItem());
            }
            if (entity instanceof LivingEntity livingentity) {
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
            }
        }
        setDeltaMovement(getDeltaMovement().multiply(-1, -1, -1));
        if (isFromWinterSoldier()) {
            if (entity.isAlive() && entity instanceof Player player) {
                if (!player.isCreative()) {
                    pickup = Pickup.ALLOWED;
                    tryPickup(player);
                }
            } else {
                spawnAtLocation(getPickupItem());
            }
            discard();
        } else {
            this.playSound(soundevent, 1.0F, 1.0F);
        }
    }

    @Override
    public ItemStack getWeaponItem() {
        return getPickupItemStackOrigin();
    }

    @Override
    protected boolean tryPickup(Player p_150196_) {
        if (super.tryPickup(p_150196_) || this.isNoPhysics() && this.ownedBy(p_150196_) && p_150196_.getInventory().add(this.getPickupItem())) {
            playSound(MarvelSoundEvents.VIBRANIUM_SHIELD_CATCH.get());
            return true;
        }
        return false;
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return MarvelItems.VIBRANIUM_SHIELD.toStack();
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return MarvelSoundEvents.VIBRANIUM_SHIELD_HIT.get();
    }

    @Override
    public void playerTouch(Player p_37580_) {
        if (this.ownedBy(p_37580_) || this.getOwner() == null) {
            super.playerTouch(p_37580_);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_37578_) {
        super.readAdditionalSaveData(p_37578_);
        this.dealtDamage = p_37578_.getBoolean("DealtDamage");
        this.entityData.set(ID_ITEM, ItemStack.parse(registryAccess(), p_37578_.getCompound("item")).orElse(MarvelItems.VIBRANIUM_SHIELD.toStack()));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_37582_) {
        super.addAdditionalSaveData(p_37582_);
        p_37582_.putBoolean("DealtDamage", this.dealtDamage);
        p_37582_.put("item", this.entityData.get(ID_ITEM).save(registryAccess()));
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
        return true;
    }
}
