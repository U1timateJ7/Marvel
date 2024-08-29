package net.tintankgames.marvel.world.entity;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.FireworkExplosion;
import net.minecraft.world.item.component.Fireworks;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.ThrownVibraniumShield;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.VibraniumShieldItem;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class WinterSoldier extends Monster implements CrossbowAttackMob {
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(WinterSoldier.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(WinterSoldier.class, EntityDataSerializers.BOOLEAN);
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS);
    private final RangedCrossbowAttackGoal<WinterSoldier> crossbowGoal = new RangedCrossbowAttackGoal<>(this, 1.0, 8.0F);
    private final RangedAttackGoal shieldGoal = new RangedAttackGoal(this, 1.0, 20, 16.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2, false) {
        @Override
        public void stop() {
            super.stop();
            WinterSoldier.this.setAggressive(false);
        }

        @Override
        public void start() {
            super.start();
            WinterSoldier.this.setAggressive(true);
        }
    };

    protected WinterSoldier(EntityType<? extends WinterSoldier> type, Level level) {
        super(type, level);
        xpReward = 300;
        bossEvent.setVisible(false);
        reassessWeaponGoal();
        setPersistenceRequired();
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return false;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6) {
            @Override
            public boolean canUse() {
                return super.canUse() && isActivated();
            }
        });
        goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        targetSelector.addGoal(1, new HurtByTargetGoal(this, HydraAgent.class, WinterSoldier.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public void reassessWeaponGoal() {
        if (!level().isClientSide) {
            goalSelector.removeGoal(meleeGoal);
            goalSelector.removeGoal(crossbowGoal);
            goalSelector.removeGoal(shieldGoal);
            ItemStack itemstack = getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof CrossbowItem));
            if (getOffhandItem().getItem() instanceof VibraniumShieldItem) {
                goalSelector.addGoal(3, shieldGoal);
            } else if (itemstack.getItem() instanceof CrossbowItem) {
                goalSelector.addGoal(3, crossbowGoal);
            } else {
                goalSelector.addGoal(3, meleeGoal);
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ACTIVATED, false);
        builder.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem item) {
        return item instanceof CrossbowItem;
    }

    @Override
    public ItemStack getProjectile(ItemStack p_33038_) {
        return p_33038_.getItem() instanceof ProjectileWeaponItem weaponItem && weaponItem.getSupportedHeldProjectiles().test(new ItemStack(Items.FIREWORK_ROCKET)) ? createFireworks() : super.getProjectile(p_33038_);
    }

    private ItemStack createFireworks() {
        ItemStack stack = new ItemStack(Items.FIREWORK_ROCKET);
        stack.set(DataComponents.FIREWORKS, new Fireworks(2, List.of(new FireworkExplosion(FireworkExplosion.Shape.BURST, IntList.of(DyeColor.YELLOW.getFireworkColor(), DyeColor.ORANGE.getFireworkColor()), IntList.of(DyeColor.LIGHT_GRAY.getFireworkColor(), DyeColor.GRAY.getFireworkColor()), false, false))));
        return stack;
    }

    public boolean isActivated() {
        return entityData.get(ACTIVATED);
    }

    public void setActivated(boolean activated) {
        entityData.set(ACTIVATED, activated);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 200.0).add(Attributes.ARMOR, 15.0).add(Attributes.ARMOR_TOUGHNESS, 1.0).add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        Arrays.asList(EquipmentSlot.values()).forEach(slot -> setDropChance(slot, 0.0F));
        populateDefaultEquipmentSlots(levelAccessor.getRandom(), difficultyInstance);
        reassessWeaponGoal();
        return super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource p_217055_, DifficultyInstance p_217056_) {
        setItemSlot(EquipmentSlot.MAINHAND, MarvelItems.WINTER_SOLDIER_KNIFE.toStack());
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (isAlive() && isActivated()) {
            if (tickCount % 200 == 0) {
                setItemSlot(EquipmentSlot.MAINHAND, tickCount % 400 == 0 ? MarvelItems.WINTER_SOLDIER_KNIFE.toStack() : MarvelItems.TESSERACT_CROSSBOW.toStack());
            }
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_31474_) {
        super.readAdditionalSaveData(p_31474_);
        if (hasCustomName()) {
            bossEvent.setName(getDisplayName());
        }
        setActivated(p_31474_.getBoolean("activated"));
        reassessWeaponGoal();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putBoolean("activated", isActivated());
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!level().isClientSide) {
            reassessWeaponGoal();
        }
    }

    @Override
    public void setCustomName(@Nullable Component component) {
        super.setCustomName(component);
        bossEvent.setName(getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
        bossEvent.setVisible(isActivated());
        bossEvent.setProgress(getHealth() / getMaxHealth());
    }

    @Override
    public void die(DamageSource p_21014_) {
        super.die(p_21014_);
        bossEvent.setProgress(0);
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        if (living instanceof ServerPlayer) {
            if (!isActivated()) {
                level().playSound(null, getX(), getY(), getZ(), MarvelSoundEvents.WINTER_SOLDIER_TARGET.get(), SoundSource.MASTER, 3.0F, 1.0F);
            }
            setActivated(true);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        bossEvent.removeAllPlayers();
    }

    @Override
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    public ArmPose getArmPose() {
        if (isChargingCrossbow() && isHolding(stack -> stack.getItem() instanceof CrossbowItem)) {
            return ArmPose.CROSSBOW_CHARGE;
        } else if (isHolding(stack -> stack.getItem() instanceof CrossbowItem) && !isHolding(stack -> stack.getItem() instanceof VibraniumShieldItem)) {
            return ArmPose.CROSSBOW_HOLD;
        } else {
            return ArmPose.NEUTRAL;
        }
    }

    public boolean isChargingCrossbow() {
        return entityData.get(IS_CHARGING_CROSSBOW);
    }

    @Override
    public void setChargingCrossbow(boolean charging) {
        entityData.set(IS_CHARGING_CROSSBOW, charging);
    }

    @Override
    public void onCrossbowAttackPerformed() {
        noActionTime = 0;
    }

    @Override
    public void performRangedAttack(LivingEntity living, float f) {
        if (getOffhandItem().getItem() instanceof VibraniumShieldItem) {
            ThrownVibraniumShield thrownVibraniumShield = new ThrownVibraniumShield(this.level(), this, getOffhandItem().copy());
            thrownVibraniumShield.setBaseDamage((getOffhandItem().get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1) / 2.0);
            double d0 = living.getX() - this.getX();
            double d1 = living.getY() - thrownVibraniumShield.getY();
            double d2 = living.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            thrownVibraniumShield.setFromWinterSoldier(true);
            thrownVibraniumShield.shoot(d0, d1 + d3 * 0.2F, d2, 1.6F, (float)(14 - this.level().getDifficulty().getId() * 4));
            this.playSound(MarvelSoundEvents.VIBRANIUM_SHIELD_THROW.get(), 1.0F, 1.0F);
            this.level().addFreshEntity(thrownVibraniumShield);
            setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
        } else if (getMainHandItem().getItem() instanceof CrossbowItem) {
            performCrossbowAttack(this, 1.6F);
        }
    }

    @Override
    public boolean hurt(DamageSource p_21016_, float p_21017_) {
        if (p_21016_.getDirectEntity() instanceof ThrownVibraniumShield shield && shield.getOwner() instanceof ServerPlayer player) {
            setItemInHand(InteractionHand.OFF_HAND, shield.getItem().copy());
            shield.discard();
            setTarget(player);
            return false;
        }
        return super.hurt(p_21016_, p_21017_);
    }

    public enum ArmPose {
        NEUTRAL,
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE
    }
}
