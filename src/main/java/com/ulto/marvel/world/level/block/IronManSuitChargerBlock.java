
package com.ulto.marvel.world.level.block;

import com.ulto.marvel.world.level.block.entity.IronManSuitChargerBlockEntity;
import com.ulto.marvel.world.level.block.entity.MarvelModBlockEntityTypes;
import com.ulto.marvel.world.item.IronManSuitItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class IronManSuitChargerBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public IronManSuitChargerBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.SOUTH));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49915_) {
		p_49915_.add(FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case EAST, WEST -> Shapes.box(4f/16f, 0, 1f/16f, 12f/16f, 4f/16f, 15f/16f);
			default -> Shapes.box(1f/16f, 0, 4f/16f, 15f/16f, 4f/16f, 12f/16f);
		};
	}

	public void setPlacedBy(Level p_60172_, BlockPos p_60173_, BlockState p_60174_, LivingEntity p_60175_, ItemStack p_60176_) {
		if (!p_60172_.isClientSide) {
			this.detectSignal(p_60172_, p_60173_);
		}
	}

	public void neighborChanged(BlockState p_60198_, Level p_60199_, BlockPos p_60200_, Block p_60201_, BlockPos p_60202_, boolean p_60203_) {
		if (!p_60199_.isClientSide) {
			this.detectSignal(p_60199_, p_60200_);
		}
	}

	public void onPlace(BlockState p_60225_, Level p_60226_, BlockPos p_60227_, BlockState p_60228_, boolean p_60229_) {
		if (!p_60228_.is(p_60225_.getBlock())) {
			if (!p_60226_.isClientSide && p_60226_.getBlockEntity(p_60227_) == null) {
				this.detectSignal(p_60226_, p_60227_);
			}
		}
	}

	private void detectSignal(Level level, BlockPos pos) {
		if (level.getBlockEntity(pos) != null) {
			if (level.getBlockEntity(pos) instanceof IronManSuitChargerBlockEntity _blockEntity)
				_blockEntity.active = getNeighborSignal(level, pos);
		}
	}

	public boolean getNeighborSignal(Level level, BlockPos pos) {
		for(Direction direction : Direction.values()) if (level.hasSignal(pos.relative(direction), direction)) return true;
		return false;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_49820_) {
		return this.defaultBlockState().setValue(FACING, p_49820_.getHorizontalDirection().getOpposite());
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new IronManSuitChargerBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return type == MarvelModBlockEntityTypes.IRON_MAN_SUIT_CHARGER.get() ? IronManSuitChargerBlockEntity::tick : null;
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
		ItemStack handStack = player.getItemInHand(hand);
		if (handStack.getItem() instanceof IronManSuitItem suitItem && !suitItem.isNanotech() || handStack.isEmpty()) {
			IronManSuitChargerBlockEntity blockEntity = (IronManSuitChargerBlockEntity) level.getBlockEntity(pos);
			if (blockEntity != null) {
				if (swapItem(blockEntity, player, handStack.getItem() instanceof IronManSuitItem suitItem ? suitItem.getSlot() : blockEntity.hasItemInSlot(EquipmentSlot.FEET) ? EquipmentSlot.FEET : blockEntity.hasItemInSlot(EquipmentSlot.LEGS) ? EquipmentSlot.LEGS : blockEntity.hasItemInSlot(EquipmentSlot.CHEST) ? EquipmentSlot.CHEST : EquipmentSlot.HEAD, handStack, hand)) {
					if (!level.isClientSide()) player.getInventory().setChanged();
					return InteractionResult.sidedSuccess(level.isClientSide);
				}
			}
		}
		return InteractionResult.PASS;
	}

	private boolean swapItem(IronManSuitChargerBlockEntity blockEntity, Player p_31589_, EquipmentSlot p_31590_, ItemStack p_31591_, InteractionHand p_31592_) {
		ItemStack itemstack = blockEntity.getItemBySlot(p_31590_);
		if (!p_31591_.isEmpty() && p_31591_.getCount() > 1) {
			if (!itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = p_31591_.copy();
				itemstack1.setCount(1);
				blockEntity.setItemSlot(p_31590_, itemstack1);
				p_31591_.shrink(1);
				return true;
			}
		} else {
			blockEntity.setItemSlot(p_31590_, p_31591_);
			p_31589_.setItemInHand(p_31592_, itemstack);
			return true;
		}
	}

	@Override
	public boolean propagatesSkylightDown(BlockState p_49928_, BlockGetter p_49929_, BlockPos p_49930_) {
		return true;
	}

	public float getShadeBrightness(BlockState p_48731_, BlockGetter p_48732_, BlockPos p_48733_) {
		return 1.0F;
	}
}
