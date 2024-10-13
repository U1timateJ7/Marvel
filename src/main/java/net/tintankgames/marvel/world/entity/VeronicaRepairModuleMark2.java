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
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.particles.MarvelParticleTypes;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.UUID;

public class VeronicaRepairModuleMark2 extends Entity implements OwnableEntity {
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(VeronicaRepairModuleMark2.class, EntityDataSerializers.OPTIONAL_UUID);

    public VeronicaRepairModuleMark2(EntityType<?> type, Level level) {
        super(type, level);
    }

    public VeronicaRepairModuleMark2(Player owner, Level level) {
        this(MarvelEntityTypes.VERONICA_REPAIR_MODULE_MARK_2.get(), level);
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
            player.getData(MarvelAttachmentTypes.VERONICA).forEach(suit -> suit.armor().forEach(piece -> piece.setDamageValue(piece.getDamageValue() - (piece.getMaxDamage() / 3))));
            player.sendSystemMessage(Component.translatable("entity.marvel.veronica_repair_module_mark_2.deployed").withStyle(ChatFormatting.GREEN), true);
            discard();
        }
    }
}
