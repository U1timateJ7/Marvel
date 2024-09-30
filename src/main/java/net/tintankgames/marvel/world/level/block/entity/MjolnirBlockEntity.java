package net.tintankgames.marvel.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.MarvelConfig;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.network.CallMjolnirMessage;
import net.tintankgames.marvel.world.entity.projectile.ThrownMjolnir;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

import java.util.UUID;

@EventBusSubscriber(Dist.CLIENT)
public class MjolnirBlockEntity extends BlockEntity {
    private UUID owner = null;
    private ItemStack stack = new ItemStack(MarvelItems.MJOLNIR.get());

    public MjolnirBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(MarvelBlockEntityTypes.MJOLNIR.get(), p_155229_, p_155230_);
    }

    @Nullable
    public UUID getOwner() {
        return owner;
    }

    @Nullable
    public Player getOwner(Level level) {
        return owner != null ? level.getPlayerByUUID(owner) : null;
    }

    public ItemStack getStack() {
        return stack.copy();
    }

    public void setOwner(@Nullable UUID owner) {
        this.owner = owner;
    }

    public void setOwner(Player owner) {
        setOwner(owner.getUUID());
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
    }

    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider p_323910_) {
        return saveCustomOnly(p_323910_);
    }

    @Override
    protected void saveAdditional(CompoundTag p_187471_, HolderLookup.Provider p_323635_) {
        super.saveAdditional(p_187471_, p_323635_);
        if (owner != null) p_187471_.putUUID("owner", owner);
        p_187471_.put("item", stack.saveOptional(p_323635_));
    }

    @Override
    protected void loadAdditional(CompoundTag p_338466_, HolderLookup.Provider p_338445_) {
        super.loadAdditional(p_338466_, p_338445_);
        if (p_338466_.contains("owner", Tag.TAG_INT_ARRAY)) owner = p_338466_.getUUID("owner");
        stack = ItemStack.parseOptional(p_338445_, p_338466_.getCompound("item"));
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MjolnirBlockEntity blockEntity) {
        if (!level.isClientSide() && blockEntity.getOwner(level) != null && blockEntity.getOwner(level).getExistingData(MarvelAttachmentTypes.CALLING_MJOLNIR).orElse(false) && Math.sqrt(pos.distToCenterSqr(blockEntity.getOwner(level).position())) <= MarvelConfig.mjolnirCallRange) {
            ThrownMjolnir thrownMjolnir = new ThrownMjolnir(level, blockEntity.getOwner(level), blockEntity.getStack());
            thrownMjolnir.setPos(pos.getX(), pos.getY(), pos.getZ());
            thrownMjolnir.setBaseDamage(blockEntity.getStack().get(DataComponents.ATTRIBUTE_MODIFIERS).modifiers().stream().filter(modifier -> modifier.attribute().is(Attributes.ATTACK_DAMAGE)).toList().getFirst().modifier().amount() + 1);
            thrownMjolnir.returnToOwner();
            level.addFreshEntity(thrownMjolnir);
            level.removeBlock(pos, true);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void callMjolnir(PlayerInteractEvent.RightClickEmpty event) {
        PacketDistributor.sendToServer(CallMjolnirMessage.INSTANCE);
    }
}
