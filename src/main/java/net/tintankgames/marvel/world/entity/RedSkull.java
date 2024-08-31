package net.tintankgames.marvel.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.InfinityStone;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.core.particles.SpaceStoneParticleOptions;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.entity.projectile.TesseractCharge;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import org.jetbrains.annotations.Nullable;

public class RedSkull extends Monster implements RangedAttackMob {
    private static final EntityDataAccessor<Boolean> ACTIVATED = SynchedEntityData.defineId(RedSkull.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ATTACK_TIMER = SynchedEntityData.defineId(RedSkull.class, EntityDataSerializers.INT);
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS);
    private final RangedAttackGoal rangedGoal = new RangedAttackGoal(this, 1.0, 30, 16.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.5, false) {
        @Override
        public void stop() {
            super.stop();
            RedSkull.this.setAggressive(false);
        }

        @Override
        public void start() {
            super.start();
            RedSkull.this.setAggressive(true);
        }

        @Override
        protected void checkAndPerformAttack(LivingEntity p_25557_) {
            if (this.canPerformAttack(p_25557_)) {
                this.resetAttackCooldown();
                this.mob.swing(InteractionHand.MAIN_HAND);
                this.mob.doHurtTarget(p_25557_);
                RedSkull.this.setAttackTimer(200);
                RedSkull.this.reassessWeaponGoal();
            }
        }
    };

    protected RedSkull(EntityType<? extends RedSkull> type, Level level) {
        super(type, level);
        xpReward = 250;
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
        targetSelector.addGoal(1, new HurtByTargetGoal(this, HydraAgent.class, RedSkull.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public void reassessWeaponGoal() {
        if (!level().isClientSide) {
            goalSelector.removeGoal(rangedGoal);
            goalSelector.removeGoal(meleeGoal);
            if (getAttackTimer() <= 0) {
                goalSelector.addGoal(3, meleeGoal);
            } else {
                goalSelector.addGoal(3, rangedGoal);
            }
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(ACTIVATED, false);
        builder.define(ATTACK_TIMER, 200);
    }

    public boolean isActivated() {
        return entityData.get(ACTIVATED);
    }

    public void setActivated(boolean activated) {
        entityData.set(ACTIVATED, activated);
    }

    public int getAttackTimer() {
        return entityData.get(ATTACK_TIMER);
    }

    public void setAttackTimer(int attackTimer) {
        entityData.set(ATTACK_TIMER, attackTimer);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 250.0).add(Attributes.ARMOR, 15.0).add(Attributes.ARMOR_TOUGHNESS, 1.0).add(Attributes.ATTACK_DAMAGE, 7.0).add(Attributes.ATTACK_KNOCKBACK, 4.0).add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomSource = levelAccessor.getRandom();
        populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        reassessWeaponGoal();
        return super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        setItemSlot(EquipmentSlot.MAINHAND, MarvelItems.TESSERACT.toStack());
        setGuaranteedDrop(EquipmentSlot.MAINHAND);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_31474_) {
        super.readAdditionalSaveData(p_31474_);
        if (hasCustomName()) {
            bossEvent.setName(getDisplayName());
        }
        setActivated(p_31474_.getBoolean("activated"));
        setAttackTimer(p_31474_.getInt("attack_timer"));
        reassessWeaponGoal();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_) {
        super.addAdditionalSaveData(p_21484_);
        p_21484_.putBoolean("activated", isActivated());
        p_21484_.putInt("attack_timer", getAttackTimer());
    }

    protected boolean teleport() {
        if (!this.level().isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5) * 16.0;
            double d1 = this.getY() + (double)(this.random.nextInt(2));
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5) * 16.0;
            return this.teleport(d0, d1, d2);
        } else {
            return false;
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource p_345073_) {
        if (p_345073_.is(DamageTypeTags.IS_PROJECTILE)) {
            for (int i = 0; i < 64; i++) {
                if (this.teleport()) {
                    return true;
                }
            }
        }
        return super.isInvulnerableTo(p_345073_);
    }

    private boolean teleport(double p_32544_, double p_32545_, double p_32546_) {
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_32544_, p_32545_, p_32546_);

        while (blockpos$mutableblockpos.getY() > this.level().getMinBuildHeight() && !this.level().getBlockState(blockpos$mutableblockpos).blocksMotion()) {
            blockpos$mutableblockpos.move(Direction.DOWN);
        }

        BlockState blockstate = this.level().getBlockState(blockpos$mutableblockpos);
        boolean flag = blockstate.blocksMotion();
        boolean flag1 = blockstate.is(MarvelBlocks.Tags.HYDRA_AGENT_SPAWNABLE_ON) || getSpawnType() != MobSpawnType.STRUCTURE;
        if (flag && flag1) {
            Vec3 vec3 = this.position();
            boolean flag2 = this.randomTeleport(blockpos$mutableblockpos.getX() + 0.5, blockpos$mutableblockpos.getY() + 1, blockpos$mutableblockpos.getZ() + 0.5, true);
            if (flag2) {
                this.level().gameEvent(GameEvent.TELEPORT, vec3, GameEvent.Context.of(this));
                if (!this.isSilent()) {
                    this.level().playSound(null, this.xo, this.yo, this.zo, MarvelSoundEvents.TESSERACT_TELEPORT.get(), this.getSoundSource(), 0.6F, 1.0F);
                    this.playSound(MarvelSoundEvents.TESSERACT_TELEPORT.get(), 0.6F, 1.0F);
                }
                if (level() instanceof ServerLevel level) {
                    for (ServerPlayer serverPlayer : level.players()) {
                        level.sendParticles(serverPlayer, new SpaceStoneParticleOptions(MarvelParticleTypes.REVERSE_SPACE_STONE_EMITTER.get(), 2, yHeadRot), true, getX(), getY(0.5), getZ(), 0, 0, 0, 0, 1);
                    }
                }
            }
            return flag2;
        } else {
            return false;
        }
    }

    @Override
    public void handleEntityEvent(byte p_20975_) {
        switch (p_20975_) {
            case 46:
                this.level().addParticle(new SpaceStoneParticleOptions(MarvelParticleTypes.SPACE_STONE_EMITTER.get(), 2, yHeadRot), xo, yo + getBbHeight() * 0.5, zo, 0, 0, 0);
                break;
            default:
                super.handleEntityEvent(p_20975_);
        }
    }

    @Override
    public void setItemSlot(EquipmentSlot slot, ItemStack stack) {
        super.setItemSlot(slot, stack);
        if (!level().isClientSide) {
            reassessWeaponGoal();
        }
    }

    @Override
    public void setCustomName(@Nullable Component p_31476_) {
        super.setCustomName(p_31476_);
        bossEvent.setName(getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
        bossEvent.setVisible(isActivated());
        bossEvent.setProgress(getHealth() / getMaxHealth());
        if (getServer().overworld().getData(MarvelAttachmentTypes.INFINITY_STONES).hasFoundStone(InfinityStone.SPACE)) {
            setDropChance(EquipmentSlot.MAINHAND, 0.0F);
        } else {
            setDropChance(EquipmentSlot.MAINHAND, 2.0F);
        }
        if (isActivated()) {
            if (getAttackTimer() >= 0) {
                setAttackTimer(getAttackTimer() - 1);
                if (getAttackTimer() == 0) reassessWeaponGoal();
            }
        }
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

    @Override
    public void performRangedAttack(LivingEntity living, float f) {
        TesseractCharge tesseractCharge = new TesseractCharge(this.level(), this);
        double d0 = living.getX() - this.getX();
        double d1 = living.getY(0.5) - tesseractCharge.getY();
        double d2 = living.getZ() - this.getZ();
        tesseractCharge.shoot(d0, d1, d2, 1.6F, 6.0F);
        this.level().addFreshEntity(tesseractCharge);
        this.swing(InteractionHand.MAIN_HAND);
    }
}
