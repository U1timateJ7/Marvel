package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.entity.VeronicaSatellite;

import java.util.Objects;

public class VeronicaSatelliteItem extends Item {
    public VeronicaSatelliteItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getPlayer() != null && !context.getPlayer().getData(MarvelAttachmentTypes.VERONICA).enabled()) {
            Level level = context.getLevel();
            if (!(level instanceof ServerLevel)) {
                return InteractionResult.SUCCESS;
            } else {
                ItemStack itemstack = context.getItemInHand();
                BlockPos blockpos = context.getClickedPos();
                Direction direction = context.getClickedFace();
                BlockState blockstate = level.getBlockState(blockpos);

                BlockPos blockpos1;
                if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                    blockpos1 = blockpos;
                } else {
                    blockpos1 = blockpos.relative(direction);
                }

                VeronicaSatellite veronicaSatellite = MarvelEntityTypes.VERONICA_SATELLITE.get().spawn((ServerLevel) level, itemstack, context.getPlayer(), blockpos1, MobSpawnType.TRIGGERED, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
                if (veronicaSatellite != null) {
                    veronicaSatellite.setOwnerUUID(context.getPlayer().getUUID());
                    itemstack.consume(1, context.getPlayer());
                    level.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
                }

                return InteractionResult.CONSUME;
            }
        } else {
            if (context.getPlayer() instanceof ServerPlayer serverPlayer) {
                serverPlayer.sendSystemMessage(Component.translatable("item.marvel.veronica_satellite.fail").withStyle(ChatFormatting.RED), true);
            }
            return super.useOn(context);
        }
    }
}
