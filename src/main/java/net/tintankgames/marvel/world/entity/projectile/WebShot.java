package net.tintankgames.marvel.world.entity.projectile;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.attachment.EntityHolder;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;
import net.tintankgames.marvel.world.level.block.SpiderWebBlock;
import org.jetbrains.annotations.Nullable;

public class WebShot extends Projectile {
    public static final EntityDataAccessor<Boolean> IN_BLOCK = SynchedEntityData.defineId(WebShot.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Float> LENGTH = SynchedEntityData.defineId(WebShot.class, EntityDataSerializers.FLOAT);

    public WebShot(EntityType<? extends WebShot> entityType, Level level) {
        super(entityType, level);
        this.noCulling = true;
    }

    public WebShot(Level level, Player player) {
        this(MarvelEntityTypes.WEB_SHOT.get(), level);
        this.setOwner(player);
        this.setPos(player.getX(), player.getEyeY() - 0.1, player.getZ());
        this.setDeltaMovement(player.getViewVector(1.0F).scale(3.0));
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(IN_BLOCK, false);
        builder.define(LENGTH, 0.0F);
    }

    public boolean shouldRenderAtSqrDistance(double d) {
        return true;
    }

    public void lerpTo(double d, double e, double f, float g, float h, int i) {
    }

    public void tick() {
        super.tick();
        Player player = this.getPlayerOwner();
        if (player != null && (this.level().isClientSide() || !this.shouldRetract(player))) {
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.onHit(hitResult);
            }

            this.setPos(hitResult.getLocation());
            this.checkInsideBlocks();
        } else {
            this.discard();
        }
    }

    private boolean shouldRetract(Player player) {
        if (!player.isRemoved() && player.isAlive() && player.isHolding(MarvelItems.WEB_SHOOTER.get()) && !(this.distanceToSqr(player) > 10000.0)) {
            return false;
        } else {
            this.discard();
            return true;
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        if (getPlayerOwner() != null) {
            entityHitResult.getEntity().hurt(damageSources().source(DamageTypes.ARROW, this, getOwner() == null ? this : getOwner()), 2);
            if (level().getBlockState(entityHitResult.getEntity().blockPosition()).canBeReplaced()) level().setBlockAndUpdate(entityHitResult.getEntity().blockPosition(), MarvelBlocks.SPIDER_WEB.get().defaultBlockState().setValue(SpiderWebBlock.WATERLOGGED, level().getFluidState(entityHitResult.getEntity().blockPosition()).is(Fluids.WATER)).setValue(SpiderWebBlock.LAVALOGGED, level().getFluidState(entityHitResult.getEntity().blockPosition()).is(Fluids.LAVA)));
            getPlayerOwner().setData(MarvelAttachmentTypes.GRAPPLING, new EntityHolder<>(null));
            discard();
        }
    }

    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        this.setDeltaMovement(Vec3.ZERO);
        this.setInBlock(true);
        Player player = this.getPlayerOwner();
        if (player != null) {
            double d = player.getEyePosition().subtract(blockHitResult.getLocation()).length();
            this.setLength(Math.max((float)d * 0.5F - 3.0F, 1.5F));
        }

    }

    public void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putBoolean("in_block", this.inBlock());
        compoundTag.putFloat("length", this.length());
    }

    public void readAdditionalSaveData(CompoundTag compoundTag) {
        this.setInBlock(compoundTag.getBoolean("in_block"));
        this.setLength(compoundTag.getFloat("length"));
    }

    private void setInBlock(boolean bl) {
        this.getEntityData().set(IN_BLOCK, bl);
    }

    private void setLength(float f) {
        this.getEntityData().set(LENGTH, f);
    }

    public boolean inBlock() {
        return this.getEntityData().get(IN_BLOCK);
    }

    public float length() {
        return this.getEntityData().get(LENGTH);
    }

    protected Entity.MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    public void remove(Entity.RemovalReason removalReason) {
        this.updateOwnerInfo(null);
        super.remove(removalReason);
    }

    public void onClientRemoval() {
        this.updateOwnerInfo(null);
    }

    public void setOwner(@Nullable Entity entity) {
        super.setOwner(entity);
        this.updateOwnerInfo(this);
    }

    private void updateOwnerInfo(@Nullable WebShot webShot) {
        Player player = this.getPlayerOwner();
        if (player != null) {
            player.setData(MarvelAttachmentTypes.GRAPPLING, new EntityHolder<>(webShot));
        }

    }

    @Nullable
    public Player getPlayerOwner() {
        Entity entity = this.getOwner();
        return entity instanceof Player ? (Player)entity : null;
    }

    public boolean canChangeDimensions() {
        return false;
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        Entity entity = this.getOwner();
        return new ClientboundAddEntityPacket(this, entity == null ? this.getId() : entity.getId());
    }

    public void recreateFromPacket(ClientboundAddEntityPacket clientboundAddEntityPacket) {
        super.recreateFromPacket(clientboundAddEntityPacket);
        if (this.getPlayerOwner() == null) {
            this.kill();
        }
    }
}
