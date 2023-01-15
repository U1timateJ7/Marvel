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
        if (!state.getCollisionShape(level, pos).isEmpty() || ForgeHooks.isLivingOnLadder(state, level, pos, (LivingEntity)(Object)this).isPresent()) return true;
        else {
            if (level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below())) {
                boolean north = level.getBlockState(pos.north()).isCollisionShapeFullBlock(level, pos.north()) && level.getBlockState(pos.north().above()).isCollisionShapeFullBlock(level, pos.north().above());
                boolean east = level.getBlockState(pos.east()).isCollisionShapeFullBlock(level, pos.east()) && level.getBlockState(pos.east().above()).isCollisionShapeFullBlock(level, pos.east().above());
                boolean south = level.getBlockState(pos.south()).isCollisionShapeFullBlock(level, pos.south()) && level.getBlockState(pos.south().above()).isCollisionShapeFullBlock(level, pos.south().above());
                boolean west = level.getBlockState(pos.west()).isCollisionShapeFullBlock(level, pos.west()) && level.getBlockState(pos.west().above()).isCollisionShapeFullBlock(level, pos.west().above());
                return north || east || south || west;
            } else {
                boolean north = level.getBlockState(pos.north()).isCollisionShapeFullBlock(level, pos.north());
                boolean east = level.getBlockState(pos.east()).isCollisionShapeFullBlock(level, pos.east());
                boolean south = level.getBlockState(pos.south()).isCollisionShapeFullBlock(level, pos.south());
                boolean west = level.getBlockState(pos.west()).isCollisionShapeFullBlock(level, pos.west());
                return north || east || south || west;
            }
        }
    }
}
