package net.tintankgames.marvel.world.entity;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class VeronicaRepairModuleMark1 extends Entity implements OwnableEntity {
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(VeronicaRepairModuleMark1.class, EntityDataSerializers.OPTIONAL_UUID);

    public VeronicaRepairModuleMark1(EntityType<?> type, Level level) {
        super(type, level);
    }

    public VeronicaRepairModuleMark1(Player owner, Level level) {
        this(MarvelEntityTypes.VERONICA_REPAIR_MODULE_MARK_1.get(), level);
        setOwnerUUID(owner.getUUID());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_OWNERUUID_ID, Optional.empty());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        UUID uuid;
        if (tag.hasUUID("Owner")) {
            uuid = tag.getUUID("Owner");
        } else {
            String s = tag.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        this.setOwnerUUID(uuid);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        if (this.getOwnerUUID() != null) {
            tag.putUUID("Owner", this.getOwnerUUID());
        }
    }

    @Nullable
    @Override
    public UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse(null);
    }

    public void setOwnerUUID(@Nullable UUID p_21817_) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(p_21817_));
    }

    @Override
    public void tick() {
        super.tick();
        this.setPos(position().add(0, 1, 0));
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(MarvelParticleTypes.IRON_MAN_FLAME.get(), xo, yo, zo, 4, 0.15, 0, 0.15, 0);
        }
        if (getOwner() instanceof ServerPlayer player && getY() > player.getY() + 128) {
            List<Integer> suits = player.getData(MarvelAttachmentTypes.VERONICA).getSuits().stream().filter(suit -> durabilityWeight(suit.armor()) < 1.0F).sorted((suit1, suit2) -> Float.compare(durabilityWeight(suit2.armor()), durabilityWeight(suit1.armor()))).limit(8).map(VeronicaData.Suit::id).toList();
            suits.forEach(suit -> player.getData(MarvelAttachmentTypes.VERONICA).getSuit(suit).armor().forEach(piece -> piece.setDamageValue(piece.getDamageValue() - (piece.getMaxDamage() / 3))));
            player.sendSystemMessage(Component.translatable("entity.marvel.veronica_repair_module_mark_1.deployed", suits.size()).withStyle(ChatFormatting.GREEN), true);
            discard();
        }
    }

    private float durabilityWeight(List<ItemStack> stacks) {
        int damage = 0;
        int durability = 0;
        for (ItemStack stack : stacks) {
            damage += stack.getDamageValue();
            durability += stack.getMaxDamage();
        }
        return (float) damage / durability;
    }
}
