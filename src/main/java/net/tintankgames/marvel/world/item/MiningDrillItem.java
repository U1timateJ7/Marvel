package net.tintankgames.marvel.world.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.common.CommonHooks;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

public class MiningDrillItem extends SuitPowerItem {
    public MiningDrillItem(Properties properties) {
        super(properties.attributes(PickaxeItem.createAttributes(Tiers.IRON, 2.0F, -2.5F)));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isShiftKeyDown()) {
            boolean mode = !player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SINGLE_BLOCK, false);
            player.getItemBySlot(EquipmentSlot.CHEST).set(MarvelDataComponents.SINGLE_BLOCK, mode);
            player.displayClientMessage(Component.translatable(getDescriptionId(stack) + "." + (mode ? "single_block" : "multi_block")).withStyle(mode ? ChatFormatting.RED : ChatFormatting.GREEN), true);
            return InteractionResultHolder.success(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        return Tiers.IRON.getSpeed();
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return state.is(BlockTags.MINEABLE_WITH_PICKAXE) && !state.is(BlockTags.INCORRECT_FOR_IRON_TOOL);
    }

    public boolean canBreakExtraBlock(Level world, BlockPos pos, BlockState state, Player player) {
        return state.canHarvestBlock(world, pos, player) && state.is(BlockTags.MINEABLE_WITH_PICKAXE) && !state.is(BlockTags.INCORRECT_FOR_IRON_TOOL);
    }

    public ImmutableList<BlockPos> getExtraBlocksDug(Level world, Player player, HitResult rtr) {
        if (!(rtr instanceof BlockHitResult brtr)) return ImmutableList.of();
        Direction side = brtr.getDirection();

        BlockPos startPos = brtr.getBlockPos();
        BlockState state = world.getBlockState(startPos);
        float maxHardness = 1;
        if(!state.isAir()) maxHardness = state.getDestroyProgress(player, world, startPos)*0.4F;
        if(maxHardness < 0) maxHardness = 0;

        startPos = startPos.offset(-(side.getAxis()== Direction.Axis.X?0: 3 /2), -(side.getAxis()== Direction.Axis.Y?0: 3 /2), -(side.getAxis()== Direction.Axis.Z?0: 3 /2));
        ImmutableList.Builder<BlockPos> b = ImmutableList.builder();
        for (int dd = 0; dd < 1; dd++) {
            for (int dw = 0; dw < 3; dw++) {
                for (int dh = 0; dh < 3; dh++) {
                    BlockPos pos = startPos.offset((side.getAxis() == Direction.Axis.X ? dd : dw), (side.getAxis() == Direction.Axis.Y ? dd : dh), (side.getAxis() == Direction.Axis.Y ? dh : side.getAxis() == Direction.Axis.X ? dw : dd));
                    if (pos.equals(brtr.getBlockPos())) continue;
                    state = world.getBlockState(pos);
                    if (state.isAir()) continue;
                    boolean canHarvest = world.getBlockState(pos).canHarvestBlock(world, pos, player);
                    boolean drillMat = state.is(BlockTags.MINEABLE_WITH_PICKAXE) && !state.is(BlockTags.INCORRECT_FOR_IRON_TOOL);
                    boolean hardness = state.getDestroyProgress(player, world, pos) >= maxHardness;
                    if (canHarvest && drillMat && hardness) b.add(pos);
                }
            }
        }
        return b.build();
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState centerState, BlockPos centerPos, LivingEntity entity) {
        if (level.isClientSide || !(entity instanceof ServerPlayer player)) return false;
        if (player.isShiftKeyDown() || player.getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SINGLE_BLOCK, false)) return false;
        HitResult mop = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
        ImmutableList<BlockPos> additional = getExtraBlocksDug(level, player, mop);
        for (BlockPos pos : additional) {
            if (!level.hasChunkAt(pos)) continue;
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();

            if (!state.isAir() && state.getDestroyProgress(player, level, pos) != 0) {
                if (!this.canBreakExtraBlock(level, pos, state, player)) continue;
                BlockEvent.BreakEvent event = CommonHooks.fireBlockBreak(level, player.gameMode.getGameModeForPlayer(), player, pos, state);
                if (event.isCanceled()) continue;

                if (player.getAbilities().instabuild) {
                    if (block.onDestroyedByPlayer(state, level, pos, player, false, state.getFluidState())) block.destroy(level, pos, state);
                } else {
                    BlockEntity te = level.getBlockEntity(pos);
                    if (block.onDestroyedByPlayer(state, level, pos, player, true, state.getFluidState())) {
                        block.destroy(level, pos, state);
                        block.playerDestroy(level, player, pos, state, te, stack);
                    }
                }
                level.levelEvent(2001, pos, Block.getId(state));
                player.connection.send(new ClientboundBlockUpdatePacket(level, pos));
            }
        }
        return false;
    }
}
