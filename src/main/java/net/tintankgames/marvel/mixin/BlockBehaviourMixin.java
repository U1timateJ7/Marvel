package net.tintankgames.marvel.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.tintankgames.marvel.world.level.OpticBlastExplosionDamageCalculator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(BlockBehaviour.class)
public abstract class BlockBehaviourMixin {
    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;getDrops(Lnet/minecraft/world/level/storage/loot/LootParams$Builder;)Ljava/util/List;", shift = At.Shift.BEFORE), method = "onExplosionHit", cancellable = true)
    private void opticBlastDrops(BlockState p_311951_, Level p_312820_, BlockPos p_312489_, Explosion p_312925_, BiConsumer<ItemStack, BlockPos> p_312073_, CallbackInfo ci) {
        if (p_312925_.damageCalculator instanceof OpticBlastExplosionDamageCalculator && p_312820_.random.nextBoolean()) {
            p_311951_.onBlockExploded(p_312820_, p_312489_, p_312925_);
            ci.cancel();
        }
    }
}
