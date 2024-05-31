package com.ulto.marvel.mixin;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getItemBySlot(EquipmentSlot p_21127_);

    @Shadow private Optional<BlockPos> lastClimbablePos;

    public LivingEntityMixin(EntityType<?> p_19870_, Level p_19871_) {
        super(p_19870_, p_19871_);
    }

    @Inject(at = @At("HEAD"), method = "onClimbable", cancellable = true)
    private void spiderManClimbable(CallbackInfoReturnable<Boolean> cir) {
        if (hasSpiderManArmor()) {
            if (this.isSpectator()) {
                cir.setReturnValue(false);
            }
            BlockPos pos = this.blockPosition();
            BlockState feet = this.getFeetBlockState();
            if (isCurrentBlockClimbable(feet, pos)) {
                this.lastClimbablePos = Optional.of(pos);
                cir.setReturnValue(true);
            }
        }
    }

    private boolean hasSpiderManArmor() {
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(MarvelModItems.Tags.SPIDER_MAN_ARMOR);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(MarvelModItems.Tags.SPIDER_MAN_ARMOR);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(MarvelModItems.Tags.SPIDER_MAN_ARMOR);
        return chest && legs && feet;
    }

    private boolean isCurrentBlockClimbable(BlockState state, BlockPos pos) {
        if (ForgeHooks.isLivingOnLadder(state, level, pos, (LivingEntity)(Object)this).isPresent()) return true;
        else {
            if (state.getCollisionShape(level, pos).isEmpty()) {
                if (!level.getBlockState(pos.below()).getCollisionShape(level, pos.below()).isEmpty()) {
                    boolean north = !level.getBlockState(pos.north()).getCollisionShape(level, pos.north()).isEmpty() && !level.getBlockState(pos.north().above()).getCollisionShape(level, pos.north().above()).isEmpty();
                    boolean east = !level.getBlockState(pos.east()).getCollisionShape(level, pos.east()).isEmpty() && !level.getBlockState(pos.east().above()).getCollisionShape(level, pos.east().above()).isEmpty();
                    boolean south = !level.getBlockState(pos.south()).getCollisionShape(level, pos.south()).isEmpty() && !level.getBlockState(pos.south().above()).getCollisionShape(level, pos.south().above()).isEmpty();
                    boolean west = !level.getBlockState(pos.west()).getCollisionShape(level, pos.west()).isEmpty() && !level.getBlockState(pos.west().above()).getCollisionShape(level, pos.west().above()).isEmpty();
                    return north || east || south || west;
                } else {
                    boolean north = !level.getBlockState(pos.north()).getCollisionShape(level, pos.north()).isEmpty();
                    boolean east = !level.getBlockState(pos.east()).getCollisionShape(level, pos.east()).isEmpty();
                    boolean south = !level.getBlockState(pos.south()).getCollisionShape(level, pos.south()).isEmpty();
                    boolean west = !level.getBlockState(pos.west()).getCollisionShape(level, pos.west()).isEmpty();
                    boolean north1 = !level.getBlockState(pos.north().above()).getCollisionShape(level, pos.north().above()).isEmpty();
                    boolean east1 = !level.getBlockState(pos.east().above()).getCollisionShape(level, pos.east().above()).isEmpty();
                    boolean south1 = !level.getBlockState(pos.south().above()).getCollisionShape(level, pos.south().above()).isEmpty();
                    boolean west1 = !level.getBlockState(pos.west().above()).getCollisionShape(level, pos.west().above()).isEmpty();
                    boolean north2 = !level.getBlockState(pos.north().above(2)).getCollisionShape(level, pos.north().above(2)).isEmpty();
                    boolean east2 = !level.getBlockState(pos.east().above(2)).getCollisionShape(level, pos.east().above(2)).isEmpty();
                    boolean south2 = !level.getBlockState(pos.south().above(2)).getCollisionShape(level, pos.south().above(2)).isEmpty();
                    boolean west2 = !level.getBlockState(pos.west().above(2)).getCollisionShape(level, pos.west().above(2)).isEmpty();
                    boolean above = !level.getBlockState(pos.above(2)).getCollisionShape(level, pos.above(2)).isEmpty();
                    return north || east || south || west || north1 || east1 || south1 || west1 || north2 || east2 || south2 || west2 || above;
                }
            } else {
                if (!level.getBlockState(pos.below()).getCollisionShape(level, pos.below()).isEmpty()) {
                    boolean north = !level.getBlockState(pos.north().above()).getCollisionShape(level, pos.north().above()).isEmpty() && !level.getBlockState(pos.north().above(2)).getCollisionShape(level, pos.north().above(2)).isEmpty();
                    boolean east = !level.getBlockState(pos.east().above()).getCollisionShape(level, pos.east().above()).isEmpty() && !level.getBlockState(pos.east().above(2)).getCollisionShape(level, pos.east().above(2)).isEmpty();
                    boolean south = !level.getBlockState(pos.south().above()).getCollisionShape(level, pos.south().above()).isEmpty() && !level.getBlockState(pos.south().above(2)).getCollisionShape(level, pos.south().above(2)).isEmpty();
                    boolean west = !level.getBlockState(pos.west().above()).getCollisionShape(level, pos.west().above()).isEmpty() && !level.getBlockState(pos.west().above(2)).getCollisionShape(level, pos.west().above(2)).isEmpty();
                    return north || east || south || west;
                } else {
                    boolean north = !level.getBlockState(pos.north().above()).getCollisionShape(level, pos.north().above()).isEmpty();
                    boolean east = !level.getBlockState(pos.east().above()).getCollisionShape(level, pos.east().above()).isEmpty();
                    boolean south = !level.getBlockState(pos.south().above()).getCollisionShape(level, pos.south().above()).isEmpty();
                    boolean west = !level.getBlockState(pos.west().above()).getCollisionShape(level, pos.west().above()).isEmpty();
                    boolean north1 = !level.getBlockState(pos.north().above(2)).getCollisionShape(level, pos.north().above(2)).isEmpty();
                    boolean east1 = !level.getBlockState(pos.east().above(2)).getCollisionShape(level, pos.east().above(2)).isEmpty();
                    boolean south1 = !level.getBlockState(pos.south().above(2)).getCollisionShape(level, pos.south().above(2)).isEmpty();
                    boolean west1 = !level.getBlockState(pos.west().above(2)).getCollisionShape(level, pos.west().above(2)).isEmpty();
                    boolean north2 = !level.getBlockState(pos.north().above(3)).getCollisionShape(level, pos.north().above(3)).isEmpty();
                    boolean east2 = !level.getBlockState(pos.east().above(3)).getCollisionShape(level, pos.east().above(3)).isEmpty();
                    boolean south2 = !level.getBlockState(pos.south().above(3)).getCollisionShape(level, pos.south().above(3)).isEmpty();
                    boolean west2 = !level.getBlockState(pos.west().above(3)).getCollisionShape(level, pos.west().above(3)).isEmpty();
                    boolean above = !level.getBlockState(pos.above(3)).getCollisionShape(level, pos.above(3)).isEmpty();
                    return north || east || south || west || north1 || east1 || south1 || west1 || north2 || east2 || south2 || west2 || above;
                }
            }
        }
    }
}
