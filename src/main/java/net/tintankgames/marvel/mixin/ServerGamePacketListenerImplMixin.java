package net.tintankgames.marvel.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.neoforged.neoforge.common.NeoForgeMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
    @Shadow public ServerPlayer player;

    @ModifyVariable(at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/server/level/ServerPlayer;isFallFlying()Z"), method = "handleMovePlayer", ordinal = 0)
    private boolean allowFlyingOnServers(boolean value) {
        return this.player.getAttributeValue(NeoForgeMod.CREATIVE_FLIGHT) > 0 || value;
    }

    @ModifyExpressionValue(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/GameRules;getBoolean(Lnet/minecraft/world/level/GameRules$Key;)Z"), method = "handleMovePlayer")
    private boolean stillAllowFlyingOnServers(boolean value) {
        return this.player.getAttributeValue(NeoForgeMod.CREATIVE_FLIGHT) > 0 || value;
    }
}
