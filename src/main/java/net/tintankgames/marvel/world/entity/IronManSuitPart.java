package net.tintankgames.marvel.world.entity;

import com.google.common.collect.Streams;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.SuitParts;
import net.tintankgames.marvel.world.level.MarvelTicketTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class IronManSuitPart extends TamableAnimal {
    private static final EntityDataAccessor<Boolean> DATA_VERONICA = SynchedEntityData.defineId(IronManSuitPart.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<ItemStack> DATA_PIECE = SynchedEntityData.defineId(IronManSuitPart.class, EntityDataSerializers.ITEM_STACK);
    private static final EntityDataAccessor<Integer> DATA_DELAY = SynchedEntityData.defineId(IronManSuitPart.class, EntityDataSerializers.INT);
    private long ticketTimer = 0L;

    public IronManSuitPart(EntityType<IronManSuitPart> type, Level level) {
        super(type, level);
        this.setTame(false, false);
        Arrays.fill(armorDropChances, 0.0F);
        Arrays.fill(handDropChances, 0.0F);
        this.bodyArmorDropChance = 0.0F;
        this.noPhysics = true;
        this.setNoGravity(true);
    }

    @Override
    protected float getFlyingSpeed() {
        return 0.125F * (getOwner() != null && getOwner().isSprinting() ? 2.0F : 1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.3F).add(Attributes.FLYING_SPEED, 0.4F).add(Attributes.MAX_HEALTH, 20.0).add(Attributes.ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_VERONICA, false);
        ItemStack stack = MarvelItems.IRON_MAN_MARK_42_HELMET.toStack();
        stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, new SuitParts(List.of(false, true)));
        builder.define(DATA_PIECE, stack);
        builder.define(DATA_DELAY, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("veronica", flyingToVeronica());
        tag.put("piece", getPiece().saveOptional(registryAccess()));
        tag.putInt("delay", getDelay());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setFlyingToVeronica(tag.getBoolean("veronica"));
        setPiece(ItemStack.parseOptional(registryAccess(), tag.getCompound("piece")));
        setDelay(tag.getInt("delay"));
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
        return !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    protected void updateWalkAnimation(float p_268283_) {

    }

    @Override
    public void tick() {
        int i = SectionPos.blockToSectionCoord(this.position().x());
        int j = SectionPos.blockToSectionCoord(this.position().z());
        super.tick();

        if (this.isAlive()) {
            BlockPos blockpos = BlockPos.containing(this.position());
            if ((--this.ticketTimer <= 0L || i != SectionPos.blockToSectionCoord(blockpos.getX()) || j != SectionPos.blockToSectionCoord(blockpos.getZ()))) {
                this.ticketTimer = registerAndUpdateTicket();
            }
        }

        walkAnimation.update(0, 1.0F);
    }

    public long registerAndUpdateTicket() {
        if (level() instanceof ServerLevel serverlevel) {
            serverlevel.resetEmptyTime();
            return placeTicket(serverlevel, chunkPosition()) - 1L;
        } else {
            return 0L;
        }
    }

    public static long placeTicket(ServerLevel level, ChunkPos chunkPos) {
        level.getChunkSource().addRegionTicket(MarvelTicketTypes.SUIT_PART, chunkPos, 2, chunkPos);
        return MarvelTicketTypes.SUIT_PART.timeout();
    }

    public boolean flyingToVeronica() {
        return this.entityData.get(DATA_VERONICA);
    }

    public void setFlyingToVeronica(boolean flyingToVeronica) {
        this.entityData.set(DATA_VERONICA, flyingToVeronica);
    }

    public ItemStack getPiece() {
        return this.entityData.get(DATA_PIECE);
    }

    public void setPiece(ItemStack piece) {
        this.entityData.set(DATA_PIECE, piece);
    }

    public int getDelay() {
        return this.entityData.get(DATA_DELAY);
    }

    public void setDelay(int delay) {
        this.entityData.set(DATA_DELAY, delay);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        if (getDelay() > 0) {
            setDelay(getDelay() - 1);
        }
        if (flyingToVeronica()) {
            setDeltaMovement(getDeltaMovement().x, 1.0, getDeltaMovement().z);
            this.hasImpulse = true;
            this.setOnGround(false);
            if (getOwner() instanceof ServerPlayer player && getY() > player.getY() + 128) {
                player.getData(MarvelAttachmentTypes.VERONICA).addSuitPart(this);
                player.sendSystemMessage(Component.translatable("entity.marvel.iron_man_sentry.deployed", getName()).withStyle(ChatFormatting.GREEN), true);
                discard();
            }
        }
        Entity entity = this.getOwner();
        if (entity != null && !flyingToVeronica() && getDelay() <= 0) {
            Vec3 vec3 = entity.position().subtract(this.position());
            this.setPosRaw(this.getX(), this.getY() + vec3.y * 0.045, this.getZ());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.95).add(vec3.normalize().scale(distanceTo(entity) > 64 ? 0.4 : 0.2)));
        }
        if (level() instanceof ServerLevel serverLevel) {
            Vec3 movement = getDeltaMovement();
            movement = new Vec3(Math.clamp(movement.x, -1.0F, 1.0F), Math.clamp(movement.y, -1.0F, 1.0F), Math.clamp(movement.z, -1.0F, 1.0F));
            getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.FLYING, true);
            getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.DELTA_MOVEMENT, movement);
            Vec3 flamePlacement = position().add(movement.multiply(-1.5, -1, -1.5)).add(0, movement.horizontalDistance() * 1.4, 0);
            serverLevel.sendParticles(MarvelParticleTypes.IRON_MAN_FLAME.get(), flamePlacement.x(), flamePlacement.y(), flamePlacement.z(), 4, 0.1, 0, 0.1, 0);
        }
        if ((getOwner() instanceof Player player && !player.isCreative()) || !(getOwner() instanceof Player)) {
            for (ItemStack stack : getArmorSlots()) {
                if (EnergySuitItem.getEnergy(stack) > 0.0F) EnergySuitItem.removeEnergy(stack, 2.0F / 60.0F / 2.0F / 20.0F * 2.0F);
            }
        }
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

    public ArmorItem.Type getArmorType() {
        return ((ArmorItem) getPiece().getItem()).getType();
    }

    @Override
    public void playerTouch(Player entity) {
        if (getOwner() == entity && entity instanceof ServerPlayer player) {
            if (player.getItemBySlot(getArmorType().getSlot()).getItem() == getPiece().getItem()) {
                SuitParts parts = player.getItemBySlot(getArmorType().getSlot()).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(getArmorType(), false));
                SuitParts suitParts = getPiece().getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(getArmorType(), false));
                SuitParts newParts = SuitParts.defaultParts(getArmorType(), false);
                boolean hasPartOpen = false;
                int count = 0;
                for (int i = 0; i < parts.parts().size(); i++) {
                    if (parts.parts().get(i)) {
                        newParts.parts().set(i, true);
                        count++;
                    }
                }
                for (int i = 0; i < suitParts.parts().size(); i++) {
                    hasPartOpen = !parts.parts().get(i) && suitParts.parts().get(i);
                    if (hasPartOpen) {
                        newParts.parts().set(i, true);
                        MarvelSuperheroes.LOGGER.info("{} #{}", getArmorType().getName(), i);
                        break;
                    }
                }
                if (hasPartOpen) {
                    player.getItemBySlot(getArmorType().getSlot()).set(MarvelDataComponents.SUIT_PARTS, newParts);
                    float energy = ((player.getItemBySlot(getArmorType().getSlot()).getOrDefault(MarvelDataComponents.ENERGY, 0.0F) * count) + getPiece().getOrDefault(MarvelDataComponents.ENERGY, 0.0F)) / (count + 1);
                    player.getItemBySlot(getArmorType().getSlot()).set(MarvelDataComponents.ENERGY, energy);
                    discard();
                }
            } else if (player.getItemBySlot(getArmorType().getSlot()).isEmpty()) {
                player.setItemSlot(getArmorType().getSlot(), getPiece().copy());
                player.setData(MarvelAttachmentTypes.SUMMONED_SUIT, false);
                discard();
            } else {
                player.addItem(player.getItemBySlot(getArmorType().getSlot()).copy());
                player.setItemSlot(getArmorType().getSlot(), getPiece().copy());
                player.setData(MarvelAttachmentTypes.SUMMONED_SUIT, false);
                discard();
            }
            level().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.IRON_MAN_SUIT_PART_EQUIP.get(), SoundSource.PLAYERS);
        }
    }

    @Override
    protected void hurtArmor(DamageSource source, float amount) {
        this.doHurtEquipment(source, amount, EquipmentSlot.FEET, EquipmentSlot.LEGS, EquipmentSlot.CHEST, EquipmentSlot.HEAD);
    }
}
