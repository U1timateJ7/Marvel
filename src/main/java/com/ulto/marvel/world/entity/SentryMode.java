
package com.ulto.marvel.world.entity;

import com.ulto.marvel.common.MarvelMod;
import com.ulto.marvel.procedures.SentryModeEntityDiesProcedure;
import com.ulto.marvel.procedures.SentryModeSuitUpProcedure;
import com.ulto.marvel.procedures.SentryTeleportProcedure;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class SentryMode extends TamableAnimal implements RangedAttackMob {
	public static final EntityDataAccessor<String> DATA_SUIT_ID = SynchedEntityData.defineId(SentryMode.class, EntityDataSerializers.STRING);

	public SentryMode(PlayMessages.SpawnEntity packet, Level world) {
		this(MarvelModEntityTypes.SENTRY_MODE.get(), world);
	}

	public SentryMode(EntityType<SentryMode> type, Level world) {
		super(type, world);
		xpReward = 0;
		setNoAi(false);
		setPersistenceRequired();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_SUIT_ID, "none");
	}

	public String getSuit() {
		return entityData.get(DATA_SUIT_ID);
	}

	public void setSuit(String name) {
		entityData.set(DATA_SUIT_ID, name);
		setItemSlot(EquipmentSlot.HEAD, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_helmet")).getDefaultInstance());
		setItemSlot(EquipmentSlot.CHEST, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_chestplate")).getDefaultInstance());
		setItemSlot(EquipmentSlot.LEGS, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_leggings")).getDefaultInstance());
		setItemSlot(EquipmentSlot.FEET, ForgeRegistries.ITEMS.getValue(new ResourceLocation(MarvelMod.MOD_ID, name + "_boots")).getDefaultInstance());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_21819_) {
		super.addAdditionalSaveData(p_21819_);
		p_21819_.putString("Suit", getSuit());
	}

	@Override
	protected Component getTypeName() {
		return new TranslatableComponent("suit.marvel." + getSuit());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_21815_) {
		super.readAdditionalSaveData(p_21815_);
		if (p_21815_.contains("Suit", Tag.TAG_STRING)) setSuit(p_21815_.getString("Suit"));
	}

	@Override
	public Packet<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(1, new FollowOwnerGoal(this, 1, (float) 10, (float) 2, false) {
			@Override
			public boolean canUse() {
				double x = SentryMode.this.getX();
				double y = SentryMode.this.getY();
				double z = SentryMode.this.getZ();
				Entity entity = SentryMode.this;
				Level world = SentryMode.this.level;
				return super.canUse() && SentryTeleportProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				Entity entity = SentryMode.this;
				return super.canContinueToUse() && SentryTeleportProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(6, new FloatGoal(this));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 20, 10) {
			@Override
			public boolean canContinueToUse() {
				return this.canUse();
			}
		});
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.item.break"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.anvil.destroy"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source == DamageSource.FALL || (source.getEntity() != null && source.getEntity() instanceof SentryMode))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;

		SentryModeEntityDiesProcedure.execute(world, x, y, z, entity);
	}

	@Override
	public InteractionResult mobInteract(Player sourceentity, InteractionHand hand) {
		double x = this.getX();
		double y = this.getY();
		double z = this.getZ();
		Entity entity = this;
		Level world = this.level;

		SentryModeSuitUpProcedure.execute(world, x, y, z, entity, sourceentity);
		return InteractionResult.sidedSuccess(level.isClientSide());
	}

	@Override
	public void performRangedAttack(LivingEntity target, float flval) {
		RepulsorEntity.shoot(this, target);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel serverWorld, AgeableMob ageable) {
		SentryMode retval = MarvelModEntityTypes.SENTRY_MODE.get().create(serverWorld);
		retval.finalizeSpawn(serverWorld, serverWorld.getCurrentDifficultyAt(retval.blockPosition()), MobSpawnType.BREEDING, null, null);
		return retval;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return List.of().contains(stack);
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.3);
		builder = builder.add(Attributes.MAX_HEALTH, 25);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		return builder;
	}
}
