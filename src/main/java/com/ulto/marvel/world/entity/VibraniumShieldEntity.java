
package com.ulto.marvel.world.entity;

import com.ulto.marvel.sounds.MarvelModSounds;
import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class VibraniumShieldEntity extends AbstractArrow implements ItemSupplier {
	private static final EntityDataAccessor<ItemStack> DATA_SHIELD_ITEM_ID = SynchedEntityData.defineId(VibraniumShieldEntity.class, EntityDataSerializers.ITEM_STACK);
	private boolean dealtDamage;

	public VibraniumShieldEntity(EntityType<? extends VibraniumShieldEntity> type, Level world) {
		super(type, world);
	}

	public VibraniumShieldEntity(Level world, double x, double y, double z, ItemStack stack) {
		super(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), x, y, z, world);
		setShieldItem(stack.copy());
	}

	public VibraniumShieldEntity(Level world, LivingEntity entity, ItemStack stack) {
		super(MarvelModEntityTypes.VIBRANIUM_SHIELD.get(), entity, world);
		setShieldItem(stack.copy());
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public boolean isInGround() {
		return this.inGroundTime > 0;
	}

	@Override
	public void tick() {
		if (this.inGroundTime > 0) {
			this.dealtDamage = true;
		}

		Entity entity = this.getOwner();
		if ((this.dealtDamage || this.isNoPhysics()) && entity != null) {
			if (!this.isAcceptibleReturnOwner()) {
				if (!this.level.isClientSide && this.pickup == AbstractArrow.Pickup.ALLOWED) {
					this.spawnAtLocation(this.getPickupItem(), 0.1F);
				}

				this.discard();
			} else {
				this.setNoPhysics(true);
				Vec3 vec3 = entity.getEyePosition().subtract(this.position());
				this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.045D, this.getZ());
				if (this.level.isClientSide) {
					this.yOld = this.getY();
				}

				this.setDeltaMovement(this.getDeltaMovement().scale(0.95D).add(vec3.normalize().scale(0.15)));
			}
		}

		super.tick();
	}

	private boolean isAcceptibleReturnOwner() {
		Entity entity = this.getOwner();
		if (entity != null && entity.isAlive()) {
			return !(entity instanceof ServerPlayer) || !entity.isSpectator();
		} else {
			return false;
		}
	}

	public ItemStack getItem() {
		return getShieldItem();
	}

	protected ItemStack getPickupItem() {
		return getShieldItem();
	}

	public ItemStack getShieldItem() {
		return this.entityData.get(DATA_SHIELD_ITEM_ID).copy();
	}

	public void setShieldItem(ItemStack stack) {
		this.entityData.set(DATA_SHIELD_ITEM_ID, stack);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SHIELD_ITEM_ID, new ItemStack(MarvelModItems.VIBRANIUM_SHIELD.get()));
	}

	@Nullable
	protected EntityHitResult findHitEntity(Vec3 p_37575_, Vec3 p_37576_) {
		return this.dealtDamage ? null : super.findHitEntity(p_37575_, p_37576_);
	}

	protected void onHitEntity(EntityHitResult p_37573_) {
		Entity entity = p_37573_.getEntity();
		float f = 8.0F;
		if (entity instanceof LivingEntity) {
			LivingEntity livingentity = (LivingEntity)entity;
			f += EnchantmentHelper.getDamageBonus(getShieldItem(), livingentity.getMobType());
		}

		Entity entity1 = this.getOwner();
		DamageSource damagesource = DamageSource.trident(this, (Entity)(entity1 == null ? this : entity1));
		this.dealtDamage = true;
		SoundEvent soundevent = MarvelModSounds.get("item.vibranium_shield.hit");
		if (entity.hurt(damagesource, f)) {
			if (entity.getType() == EntityType.ENDERMAN) {
				return;
			}

			if (entity instanceof LivingEntity) {
				LivingEntity livingentity1 = (LivingEntity)entity;
				if (entity1 instanceof LivingEntity) {
					EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
					EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
				}

				this.doPostHurtEffects(livingentity1);
			}
		}

		this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
		float f1 = 1.0F;
		if (this.level instanceof ServerLevel && this.level.isThundering() && this.isChanneling()) {
			BlockPos blockpos = entity.blockPosition();
			if (this.level.canSeeSky(blockpos)) {
				LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(this.level);
				lightningbolt.moveTo(Vec3.atBottomCenterOf(blockpos));
				lightningbolt.setCause(entity1 instanceof ServerPlayer ? (ServerPlayer)entity1 : null);
				this.level.addFreshEntity(lightningbolt);
				soundevent = SoundEvents.TRIDENT_THUNDER;
				f1 = 5.0F;
			}
		}

		this.playSound(soundevent, f1, 1.0F);
	}

	public boolean isChanneling() {
		return EnchantmentHelper.hasChanneling(getShieldItem());
	}

	protected boolean tryPickup(Player p_150196_) {
		return super.tryPickup(p_150196_) || this.isNoPhysics() && this.ownedBy(p_150196_) && p_150196_.getInventory().add(this.getPickupItem());
	}

	protected SoundEvent getDefaultHitGroundSoundEvent() {
		return MarvelModSounds.get("item.vibranium_shield.hit");
	}

	public void playerTouch(Player p_37580_) {
		if (this.ownedBy(p_37580_) || this.getOwner() == null) {
			super.playerTouch(p_37580_);
		}

	}

	public void readAdditionalSaveData(CompoundTag p_37578_) {
		super.readAdditionalSaveData(p_37578_);
		if (p_37578_.contains("Shield", 10)) {
			setShieldItem(ItemStack.of(p_37578_.getCompound("Shield")));
		}

		this.dealtDamage = p_37578_.getBoolean("DealtDamage");
	}

	public void addAdditionalSaveData(CompoundTag p_37582_) {
		super.addAdditionalSaveData(p_37582_);
		p_37582_.put("Shield", getShieldItem().save(new CompoundTag()));
		p_37582_.putBoolean("DealtDamage", this.dealtDamage);
	}

	public void tickDespawn() {
		if (this.pickup != Pickup.ALLOWED) {
			super.tickDespawn();
		}

	}

	protected float getWaterInertia() {
		return 0.99F;
	}

	public boolean shouldRender(double p_37588_, double p_37589_, double p_37590_) {
		return true;
	}
}
