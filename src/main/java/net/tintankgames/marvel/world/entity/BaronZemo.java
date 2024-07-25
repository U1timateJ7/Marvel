package net.tintankgames.marvel.world.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;

public class BaronZemo extends Monster {
    private final ServerBossEvent bossEvent = new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS);

    protected BaronZemo(EntityType<? extends BaronZemo> type, Level level) {
        super(type, level);
        this.xpReward = 50;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0, true));
        goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        targetSelector.addGoal(1, new HurtByTargetGoal(this, HydraAgent.class, BaronZemo.class));
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 150.0).add(Attributes.ATTACK_DAMAGE, 1.0).add(Attributes.FOLLOW_RANGE, 32.0);
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
        populateDefaultEquipmentEnchantments(levelAccessor, randomSource, difficultyInstance);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
    }

    @Override
    protected void enchantSpawnedWeapon(ServerLevelAccessor levelAccessor, RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.enchantSpawnedWeapon(levelAccessor, randomSource, difficultyInstance);
        ItemStack itemstack = this.getMainHandItem();
        if (itemstack.is(ItemTags.SWORDS)) {
            itemstack.enchant(levelAccessor.holderOrThrow(Enchantments.SHARPNESS), 5);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundTag p_31474_) {
        super.readAdditionalSaveData(p_31474_);
        if (this.hasCustomName()) {
            this.bossEvent.setName(getDisplayName());
        }
    }

    @Override
    public void setCustomName(@Nullable Component p_31476_) {
        super.setCustomName(p_31476_);
        this.bossEvent.setName(getDisplayName());
    }

    @Override
    protected void customServerAiStep() {
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void setTarget(@Nullable LivingEntity living) {
        super.setTarget(living);
        if (living instanceof ServerPlayer player) {
            this.bossEvent.addPlayer(player);
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        this.bossEvent.removeAllPlayers();
    }
}
