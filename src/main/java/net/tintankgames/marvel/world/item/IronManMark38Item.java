package net.tintankgames.marvel.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.tintankgames.marvel.world.entity.IronManMark38;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;

import java.util.Objects;

public class IronManMark38Item extends Item {
    public IronManMark38Item(Properties props) {
        super(props);
    }

    @Override
    public InteractionResult useOn(UseOnContext p_43223_) {
        Level level = p_43223_.getLevel();
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = p_43223_.getItemInHand();
            BlockPos blockpos = p_43223_.getClickedPos();
            Direction direction = p_43223_.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);
            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }

            IronManMark38 igor = MarvelEntityTypes.IRON_MAN_MARK_38.get().spawn((ServerLevel) level, itemstack, p_43223_.getPlayer(), blockpos1, MobSpawnType.TRIGGERED, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (igor != null) {
                igor.setTame(true, false);
                igor.setOwnerUUID(p_43223_.getPlayer().getUUID());
                itemstack.shrink(1);
                level.gameEvent(p_43223_.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level p_43225_, Player p_43226_, InteractionHand p_43227_) {
        ItemStack itemstack = p_43226_.getItemInHand(p_43227_);
        BlockHitResult blockhitresult = getPlayerPOVHitResult(p_43225_, p_43226_, ClipContext.Fluid.SOURCE_ONLY);
        if (blockhitresult.getType() != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(itemstack);
        } else if (!(p_43225_ instanceof ServerLevel)) {
            return InteractionResultHolder.success(itemstack);
        } else {
            BlockPos blockpos = blockhitresult.getBlockPos();
            if (!(p_43225_.getBlockState(blockpos).getBlock() instanceof LiquidBlock)) {
                return InteractionResultHolder.pass(itemstack);
            } else if (p_43225_.mayInteract(p_43226_, blockpos) && p_43226_.mayUseItemAt(blockpos, blockhitresult.getDirection(), itemstack)) {
                IronManMark38 igor = MarvelEntityTypes.IRON_MAN_MARK_38.get().spawn((ServerLevel)p_43225_, itemstack, p_43226_, blockpos, MobSpawnType.TRIGGERED, false, false);
                if (igor == null) {
                    return InteractionResultHolder.pass(itemstack);
                } else {
                    itemstack.consume(1, p_43226_);
                    igor.setTame(true, false);
                    igor.setOwnerUUID(p_43226_.getUUID());
                    p_43226_.awardStat(Stats.ITEM_USED.get(this));
                    p_43225_.gameEvent(p_43226_, GameEvent.ENTITY_PLACE, igor.position());
                    return InteractionResultHolder.consume(itemstack);
                }
            } else {
                return InteractionResultHolder.fail(itemstack);
            }
        }
    }

    public static CustomData defaultEntityData() {
        CompoundTag tag = new CompoundTag();
        tag.putString("id", MarvelEntityTypes.IRON_MAN_MARK_38.getRegisteredName());
        ListTag armorItems = new ListTag();
        CompoundTag boots = new CompoundTag();
        CompoundTag leggings = new CompoundTag();
        CompoundTag chestplate = new CompoundTag();
        CompoundTag helmet = new CompoundTag();
        boots.putString("id", MarvelItems.IRON_MAN_MARK_38_BOOTS.getRegisteredName());
        leggings.putString("id", MarvelItems.IRON_MAN_MARK_38_LEGGINGS.getRegisteredName());
        chestplate.putString("id", MarvelItems.IRON_MAN_MARK_38_CHESTPLATE.getRegisteredName());
        helmet.putString("id", MarvelItems.IRON_MAN_MARK_38_HELMET.getRegisteredName());
        armorItems.add(boots);
        armorItems.add(leggings);
        armorItems.add(chestplate);
        armorItems.add(helmet);
        tag.put("ArmorItems", armorItems);
        return CustomData.of(tag);
    }
}
