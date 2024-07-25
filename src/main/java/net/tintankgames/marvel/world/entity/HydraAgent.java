package net.tintankgames.marvel.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.RangedCrossbowAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.providers.VanillaEnchantmentProviders;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.tintankgames.marvel.core.registries.MarvelRegistries;
import net.tintankgames.marvel.network.syncher.MarvelEntityDataSerializers;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import javax.annotation.Nullable;
import java.util.Optional;

public class HydraAgent extends Monster implements VariantHolder<Holder<HydraAgentVariant>>, CrossbowAttackMob {
    private static final EntityDataAccessor<Holder<HydraAgentVariant>> VARIANT = SynchedEntityData.defineId(HydraAgent.class, MarvelEntityDataSerializers.HYDRA_AGENT_VARIANT.get());
    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(HydraAgent.class, EntityDataSerializers.BOOLEAN);
    
    protected HydraAgent(EntityType<? extends HydraAgent> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        goalSelector.addGoal(0, new FloatGoal(this));
        goalSelector.addGoal(3, new RangedCrossbowAttackGoal<>(this, 1.0, 8.0F));
        goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 15.0F, 1.0F));
        goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 15.0F));
        targetSelector.addGoal(1, new HurtByTargetGoal(this, HydraAgent.class, BaronZemo.class).setAlertOthers());
        targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.MAX_HEALTH, 20.0).add(Attributes.ARMOR, 4.0).add(Attributes.ATTACK_DAMAGE, 3.0).add(Attributes.FOLLOW_RANGE, 32.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        RegistryAccess registryaccess = registryAccess();
        Registry<HydraAgentVariant> registry = registryaccess.registryOrThrow(MarvelRegistries.HYDRA_AGENT_VARIANT);
        builder.define(VARIANT, registry.getHolder(HydraAgentVariants.DEFAULT).or(registry::getAny).orElseThrow());
        builder.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    public boolean canFireProjectileWeapon(ProjectileWeaponItem item) {
        return item instanceof CrossbowItem;
    }

    @Override
    public void setVariant(Holder<HydraAgentVariant> holder) {
        entityData.set(VARIANT, holder);
    }

    @Override
    public Holder<HydraAgentVariant> getVariant() {
        return entityData.get(VARIANT);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        getVariant().unwrapKey().ifPresent(key -> tag.putString("variant", key.location().toString()));
        
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        Optional.ofNullable(ResourceLocation.tryParse(tag.getString("variant"))).map(location -> ResourceKey.create(MarvelRegistries.HYDRA_AGENT_VARIANT, location)).flatMap(key -> registryAccess().registryOrThrow(MarvelRegistries.HYDRA_AGENT_VARIANT).getHolder(key)).ifPresent(this::setVariant);
        setCanPickUpLoot(true);
    }

    @Override
    public float getWalkTargetValue(BlockPos pos, LevelReader levelReader) {
        return 0.0F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData) {
        Holder<Biome> holder = levelAccessor.getBiome(blockPosition());
        Holder<HydraAgentVariant> agentVariant;
        if (spawnGroupData instanceof AgentSpawnGroupData agentSpawnGroupData) {
            agentVariant = agentSpawnGroupData.type;
        } else {
            agentVariant = HydraAgentVariants.getSpawnVariant(registryAccess(), holder);
            spawnGroupData = new AgentSpawnGroupData(agentVariant);
        }
        RandomSource randomSource = levelAccessor.getRandom();
        populateDefaultEquipmentSlots(randomSource, difficultyInstance);
        populateDefaultEquipmentEnchantments(levelAccessor, randomSource, difficultyInstance);

        setVariant(agentVariant);
        return super.finalizeSpawn(levelAccessor, difficultyInstance, mobSpawnType, spawnGroupData);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource randomSource, DifficultyInstance difficultyInstance) {
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
    }

    @Override
    protected void enchantSpawnedWeapon(ServerLevelAccessor levelAccessor, RandomSource randomSource, DifficultyInstance difficultyInstance) {
        super.enchantSpawnedWeapon(levelAccessor, randomSource, difficultyInstance);
        if (randomSource.nextInt(300) == 0) {
            ItemStack itemstack = this.getMainHandItem();
            if (itemstack.is(Items.CROSSBOW)) {
                EnchantmentHelper.enchantItemFromProvider(itemstack, levelAccessor.registryAccess(), VanillaEnchantmentProviders.PILLAGER_SPAWN_CROSSBOW, difficultyInstance, randomSource);
            }
        }
    }

    public static boolean checkHydraAgentSpawnRules(EntityType<HydraAgent> type, LevelAccessor accessor, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return accessor.getBlockState(pos.below()).is(MarvelBlocks.Tags.HYDRA_AGENT_SPAWNABLE_ON) && accessor.getDifficulty() != Difficulty.PEACEFUL && checkMobSpawnRules(type, accessor, spawnType, pos, randomSource);
    }

    public ArmPose getArmPose() {
        if (isChargingCrossbow()) {
            return ArmPose.CROSSBOW_CHARGE;
        } else if (isHolding(stack -> stack.getItem() instanceof CrossbowItem)) {
            return ArmPose.CROSSBOW_HOLD;
        } else {
            return isAggressive() ? ArmPose.ATTACKING : ArmPose.NEUTRAL;
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
        performCrossbowAttack(this, 1.6F);
    }

    public static class AgentSpawnGroupData implements SpawnGroupData {
        public final Holder<HydraAgentVariant> type;

        public AgentSpawnGroupData(Holder<HydraAgentVariant> holder) {
            type = holder;
        }
    }

    public enum ArmPose {
        NEUTRAL,
        ATTACKING,
        CROSSBOW_HOLD,
        CROSSBOW_CHARGE
    }
}
