package net.tintankgames.marvel.mixin;

import net.minecraft.network.protocol.game.ServerboundSetStructureBlockPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ServerboundSetStructureBlockPacket.class)
public abstract class ServerboundSetStructureBlockPacketMixin {
    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"), method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", index = 2)
    private int sizeFix(int p_14046_) {
        return Integer.MAX_VALUE;
    }

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"), method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", index = 1)
    private int posFix(int p_14046_) {
        return p_14046_ < 0 ? -Integer.MAX_VALUE : p_14046_;
    }
}
