package net.tintankgames.marvel.world.entity;

import com.google.common.collect.Streams;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.ai.control.SentryMoveControl;
import net.tintankgames.marvel.world.entity.ai.goal.SentryFollowOwnerGoal;
import net.tintankgames.marvel.world.entity.projectile.Repulsor;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.SentryIronManSuitItem;
import net.tintankgames.marvel.world.item.VeronicaSuit;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

public class IronManSentry extends TamableAnimal implements RangedAttackMob, NeutralMob {
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_FIRING_REPULSOR = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_VERONICA = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_FROM_VERONICA = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_HAS_TARGET = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_FLYING_O = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_FLYING = SynchedEntityData.defineId(IronManSentry.class, EntityDataSerializers.BOOLEAN);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    @Nullable
    private UUID persistentAngerTarget;
    private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.0, true) {
        @Override
        public boolean canUse() {
            return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) >= 0.0F;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
        }
    };
    private final RangedAttackGoal rangedAttackGoal = new RangedAttackGoal(this, 1.0, 20, 16) {
        @Override
        public boolean canUse() {
            return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) >= 0.5F;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) >= 0.5F;
        }
    };
    private final WaterAvoidingRandomStrollGoal walkingGoal = new WaterAvoidingRandomStrollGoal(this, 1.0) {
        @Override
        public boolean canUse() {
            return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
        }
    };
    private final WaterAvoidingRandomFlyingGoal flyingGoal = new WaterAvoidingRandomFlyingGoal(this, 1.0) {
        @Override
        public boolean canUse() {
            return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
        }
    };
    private final GroundPathNavigation walkingNavigation;
    private final FlyingPathNavigation flyingNavigation;

    public IronManSentry(EntityType<IronManSentry> type, Level level) {
        super(type, level);
        this.setTame(false, false);
        Arrays.fill(armorDropChances, 0.0F);
        Arrays.fill(handDropChances, 0.0F);
        this.bodyArmorDropChance = 0.0F;
        walkingNavigation = new GroundPathNavigation(this, level);
        walkingNavigation.setCanOpenDoors(true);
        walkingNavigation.setCanPassDoors(true);
        flyingNavigation = new FlyingPathNavigation(this, level);
        flyingNavigation.setCanOpenDoors(true);
        flyingNavigation.setCanFloat(true);
        flyingNavigation.setCanPassDoors(true);
        reassessAttackGoals();
        this.moveControl = new SentryMoveControl(this, 90, true);
        this.navigation = this.walkingNavigation;
        this.goalSelector.addGoal(8, this.walkingGoal);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && !flyingToVeronica() && !fromVeronica();
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && !flyingToVeronica() && !fromVeronica();
            }
        });
        this.goalSelector.addGoal(6, new SentryFollowOwnerGoal(this, 1.0, 10.0F, 2.0F, false) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && !flyingToVeronica() && !fromVeronica();
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F && !flyingToVeronica() && !fromVeronica();
            }
        });
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        }.setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Mob.class, false, mob -> mob instanceof Enemy) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true) {
            @Override
            public boolean canUse() {
                return super.canUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }

            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 0.0F;
            }
        });
    }

    protected void reassessAttackGoals() {
        this.goalSelector.removeGoal(this.meleeAttackGoal);
        this.goalSelector.removeGoal(this.rangedAttackGoal);
        if (Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_24_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR))) {
            this.goalSelector.addGoal(5, this.meleeAttackGoal);
        } else {
            this.goalSelector.addGoal(5, this.rangedAttackGoal);
        }
    }

    protected void reassessMovementGoals() {
        if (this.entityData.get(DATA_FLYING_O) != getIsFlying()) {
            this.goalSelector.removeGoal(this.walkingGoal);
            this.goalSelector.removeGoal(this.flyingGoal);
            if (getIsFlying()) {
                this.navigation = this.flyingNavigation;
                this.goalSelector.addGoal(8, this.flyingGoal);
            } else {
                this.navigation = this.walkingNavigation;
                this.goalSelector.addGoal(8, this.walkingGoal);
                this.setNoGravity(false);
            }
        }
    }

    @Override
    protected float getFlyingSpeed() {
        return 0.075F * (Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR)) ? 2.0F : 1.0F) * (getOwner() != null && getOwner().isSprinting() ? 2.0F : 1.0F);
    }

    @Override
    public boolean isAngryAtAllPlayers(Level level) {
        return false;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.FLYING_SPEED, 0.4F).add(Attributes.MAX_HEALTH, 20.0).add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_REMAINING_ANGER_TIME, 0);
        builder.define(DATA_FIRING_REPULSOR, false);
        builder.define(DATA_VERONICA, false);
        builder.define(DATA_FROM_VERONICA, false);
        builder.define(DATA_HAS_TARGET, false);
        builder.define(DATA_FLYING_O, false);
        builder.define(DATA_FLYING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        this.addPersistentAngerSaveData(tag);
        tag.putBoolean("veronica", flyingToVeronica());
        tag.putBoolean("from_veronica", fromVeronica());
        tag.putBoolean("has_target", hasTarget());
        tag.putBoolean("flying_o", this.entityData.get(DATA_FLYING_O));
        tag.putBoolean("flying", getIsFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.readPersistentAngerSaveData(this.level(), tag);
        setFlyingToVeronica(tag.getBoolean("veronica"));
        setFromVeronica(tag.getBoolean("from_veronica"));
        setHasTarget(tag.getBoolean("has_target"));
        reassessAttackGoals();
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int remainingPersistentAngerTime) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, remainingPersistentAngerTime);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (getOwner() == player) {
            if (player.isSecondaryUseActive()) {
                this.setOrderedToSit(!this.isOrderedToSit());
                this.jumping = false;
                this.navigation.stop();
                this.setTarget(null);
                return InteractionResult.SUCCESS_NO_ITEM_USED;
            } else {
                if (player.isHolding(MarvelItems.VERONICA_REMOTE.get()) && EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) > 2.0 && player.getData(MarvelAttachmentTypes.VERONICA).enabled()) {
                    if (level().dimension() == Level.OVERWORLD) {
                        setFlyingToVeronica(true);
                    } else {
                        if (player instanceof ServerPlayer serverPlayer) serverPlayer.sendSystemMessage(Component.translatable("item.marvel.veronica_remote.fail.not_in_overworld").withStyle(ChatFormatting.RED), true);
                    }
                } else {
                    for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD}) {
                        if (!getItemBySlot(slot).isEmpty()) {
                            if (!player.addItem(player.getItemBySlot(slot).copy())) {
                                player.drop(player.getItemBySlot(slot).copy(), true);
                            }
                            player.setItemSlot(slot, getItemBySlot(slot).copy());
                        }
                    }
                    if (!level().isClientSide) level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_HELMET_CLOSE.get(), SoundSource.PLAYERS);
                    discard();
                }
                if (player.isHolding(MarvelItems.VERONICA_REMOTE.get()) && !player.getData(MarvelAttachmentTypes.VERONICA).enabled() && player instanceof ServerPlayer serverPlayer) {
                    serverPlayer.sendSystemMessage(Component.translatable("item.marvel.veronica_remote.fail").withStyle(ChatFormatting.RED), true);
                }
                return InteractionResult.sidedSuccess(level().isClientSide());
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
            }

            return super.hurt(source, amount);
        }
    }

    @Override
    public boolean wantsToAttack(LivingEntity enemy, LivingEntity owner) {
        if (enemy instanceof ArmorStand) {
            return false;
        } else if (enemy instanceof IronManSentry sentry) {
            return !sentry.isTame() || sentry.getOwner() != owner;
        } else {
            return switch (enemy) {
                case Player player when owner instanceof Player player1 && !player1.canHarmPlayer(player) -> false;
                case AbstractHorse abstracthorse when abstracthorse.isTamed() -> false;
                case TamableAnimal tamableanimal when tamableanimal.isTame() -> false;
                default -> true;
            };
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
        return null;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source.is(DamageTypeTags.IS_FALL) || super.isInvulnerableTo(source);
    }

    @Override
    public void performRangedAttack(LivingEntity living, float power) {
        Repulsor repulsor = new Repulsor(this.level(), this);
        double d0 = living.getX() - this.getX();
        double d1 = living.getY(0.5) - repulsor.getY();
        double d2 = living.getZ() - this.getZ();
        repulsor.shoot(d0, d1, d2, 1.6F, 6.0F);
        this.level().addFreshEntity(repulsor);
        this.playSound(MarvelSoundEvents.IRON_MAN_REPULSOR_SHOOT.get());
        if ((getOwner() instanceof Player player && !player.isCreative()) || !(getOwner() instanceof Player)) this.getArmorSlots().forEach(armor -> EnergySuitItem.removeEnergy(armor, 0.5F));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        if (mobSpawnType != MobSpawnType.TRIGGERED) {
            setItemSlot(EquipmentSlot.FEET, MarvelItems.IRON_MAN_MARK_11_BOOTS.toStack());
            setItemSlot(EquipmentSlot.LEGS, MarvelItems.IRON_MAN_MARK_11_LEGGINGS.toStack());
            setItemSlot(EquipmentSlot.CHEST, MarvelItems.IRON_MAN_MARK_11_CHESTPLATE.toStack());
            setItemSlot(EquipmentSlot.HEAD, MarvelItems.IRON_MAN_MARK_11_HELMET.toStack());
        }
        return super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
    }

    @Override
    protected Component getTypeName() {
        if (getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof SentryIronManSuitItem item) return item.sentryName();
        return super.getTypeName();
    }

    public boolean flyingToVeronica() {
        return this.entityData.get(DATA_VERONICA);
    }

    public void setFlyingToVeronica(boolean flyingToVeronica) {
        this.entityData.set(DATA_VERONICA, flyingToVeronica);
    }

    public boolean fromVeronica() {
        return this.entityData.get(DATA_FROM_VERONICA);
    }

    public void setFromVeronica(boolean fromVeronica) {
        this.entityData.set(DATA_FROM_VERONICA, fromVeronica);
    }

    public boolean hasTarget() {
        return this.entityData.get(DATA_HAS_TARGET);
    }

    public void setHasTarget(boolean hasTarget) {
        this.entityData.set(DATA_HAS_TARGET, hasTarget);
    }

    public boolean firingRepulsor() {
        return this.entityData.get(DATA_FIRING_REPULSOR);
    }

    public boolean isFlying() {
        return ((fromVeronica() || flyingToVeronica()) || getIsFlying()) && !onGround();
    }

    public boolean getIsFlying() {
        return this.entityData.get(DATA_FLYING);
    }

    public void setFlying(boolean flying) {
        this.entityData.set(DATA_FLYING_O, getIsFlying());
        this.entityData.set(DATA_FLYING, flying);
        reassessMovementGoals();
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {
        if (!isFlying()) super.updateWalkAnimation(p_268283_);
    }

    @Override
    public void tick() {
        super.tick();
        if (isFlying()) {
            walkAnimation.update(0, 1.0F);
        }
        if (this.level().isClientSide() && getOwner() != null) {
            getData(MarvelAttachmentTypes.TURRET_EQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.WAR_MACHINE_ARMOR)) && hasTarget(), tickCount);
            getData(MarvelAttachmentTypes.TURRET_UNEQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.WAR_MACHINE_ARMOR)) && !hasTarget(), tickCount);
            getData(MarvelAttachmentTypes.DRILL_EQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR)) && hasTarget(), tickCount);
            getData(MarvelAttachmentTypes.DRILL_UNEQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR)) && !hasTarget(), tickCount);
            getData(MarvelAttachmentTypes.RIGHT_BLADE_EQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR)) && hasTarget() && ((getOwner() instanceof Player player && player.getMainArm() == HumanoidArm.RIGHT) || getOwner() == null), tickCount);
            getData(MarvelAttachmentTypes.RIGHT_BLADE_UNEQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR)) && !hasTarget() && ((getOwner() instanceof Player player && player.getMainArm() == HumanoidArm.RIGHT) || getOwner() == null), tickCount);
            getData(MarvelAttachmentTypes.LEFT_BLADE_EQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR)) && hasTarget() && (getOwner() instanceof Player player && player.getMainArm() == HumanoidArm.LEFT), tickCount);
            getData(MarvelAttachmentTypes.LEFT_BLADE_UNEQUIP_ANIMATION_STATE).animateWhen(Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR)) && !hasTarget() && (getOwner() instanceof Player player && player.getMainArm() == HumanoidArm.LEFT), tickCount);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        updateSwingTime();
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (EnergySuitItem.getEnergy(getItemBySlot(EquipmentSlot.CHEST)) <= 5.0F && getOwner() instanceof ServerPlayer player && player.getData(MarvelAttachmentTypes.VERONICA).enabled()) setFlyingToVeronica(true);
        if (fromVeronica() && onGround()) setFromVeronica(false);
        if (flyingToVeronica()) {
            setDeltaMovement(getDeltaMovement().x, 1.0, getDeltaMovement().z);
            this.hasImpulse = true;
            this.setOnGround(false);
            if (getOwner() instanceof ServerPlayer player && getY() > player.getY() + 128) {
                player.getData(MarvelAttachmentTypes.VERONICA).addSuit(new VeronicaData.Suit(new ArrayList<>((Collection<ItemStack>) getArmorSlots()), getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof VeronicaSuit suit ? suit.markNumber() : -1, player.getData(MarvelAttachmentTypes.VERONICA).nextId()));
                player.sendSystemMessage(Component.translatable("entity.marvel.iron_man_sentry.deployed", getName()).withStyle(ChatFormatting.GREEN), true);
                discard();
            }
        }
        if (level() instanceof ServerLevel serverLevel) {
            if (isFlying()) {
                Vec3 movement = getDeltaMovement();
                movement = new Vec3(Math.clamp(movement.x, -1.0F, 1.0F), Math.clamp(movement.y, -1.0F, 1.0F), Math.clamp(movement.z, -1.0F, 1.0F));
                getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FLYING, isFlying());
                getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.DELTA_MOVEMENT, movement);
                Vec3 flamePlacement = position().add(movement.multiply(-1.5, -1, -1.5)).add(0, movement.horizontalDistance() * 1.4, 0);
                serverLevel.sendParticles(MarvelParticleTypes.IRON_MAN_FLAME.get(), flamePlacement.x(), flamePlacement.y(), flamePlacement.z(), 4, 0.1, 0, 0.1, 0);
            } else {
                getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.FLYING);
                getItemBySlot(EquipmentSlot.CHEST).remove(MarvelDataComponents.DELTA_MOVEMENT);
            }
        }
        setHasTarget(getTarget() != null);
        try {
            this.entityData.set(DATA_FIRING_REPULSOR, hasTarget() && rangedAttackGoal.canContinueToUse() && goalSelector.getAvailableGoals().stream().noneMatch(goal -> goal.getGoal() == meleeAttackGoal));
        } catch (Exception e) {
            this.entityData.set(DATA_FIRING_REPULSOR, false);
        }
        if ((getOwner() instanceof Player player && !player.isCreative()) || !(getOwner() instanceof Player)) {
            for (ItemStack stack : getArmorSlots()) {
                if (EnergySuitItem.getEnergy(stack) > 0.0F) EnergySuitItem.removeEnergy(stack, 2.0F / 60.0F / 2.0F / 20.0F);
            }
        }
        setFlying(getOwner() instanceof Player player && player.getAbilities().flying);
        setLeftHanded(getOwner() instanceof Player player && player.getMainArm() == HumanoidArm.LEFT);
        if (Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_25_ARMOR))) {
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(8.0);
        } else if (Streams.stream(getArmorSlots()).allMatch(armor -> armor.is(MarvelItems.Tags.IRON_MAN_MARK_30_ARMOR) || armor.is(MarvelItems.Tags.IRON_MAN_MARK_33_ARMOR))) {
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(9.0);
        } else {
            getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0);
        }
        this.removeAllEffects();
    }

    @Override
    protected void hurtArmor(DamageSource source, float amount) {
        this.doHurtEquipment(source, amount, EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD);
    }

    protected void doHurtEquipment(DamageSource source, float f, EquipmentSlot... slots) {
        if (!(f <= 0.0F)) {
            int i = (int)Math.max(1.0F, f / 4.0F);

            for (EquipmentSlot slot : slots) {
                ItemStack itemstack = this.getItemBySlot(slot);
                if (itemstack.getItem() instanceof ArmorItem && itemstack.canBeHurtBy(source)) {
                    if (itemstack.getDamageValue() + i >= itemstack.getMaxDamage()) {
                        for (ItemStack armor : getArmorSlots()) {
                            spawnAtLocation(armor.copy());
                            armor.shrink(armor.getCount());
                        }
                        kill();
                        i = itemstack.getMaxDamage() - itemstack.getDamageValue() - 1;
                    }
                    if (i > 0) itemstack.hurtAndBreak(i, this, slot);
                }
            }
        }
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int i, boolean bl) {
        for (ItemStack stack : getArmorSlots()) {
            ItemStack armor = stack.copy();
            armor.setDamageValue(Math.max(armor.getDamageValue(), random.nextInt(armor.getMaxDamage() / 8) + (armor.getMaxDamage() - armor.getMaxDamage() / 4)));
            spawnAtLocation(armor);
        }
    }

    @Override
    public void onEquipItem(EquipmentSlot p_238393_, ItemStack p_238394_, ItemStack p_238395_) {
        super.onEquipItem(p_238393_, p_238394_, p_238395_);
        if (!level().isClientSide) {
            reassessAttackGoals();
        }
    }
}
