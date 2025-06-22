package net.tintankgames.marvel.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    @Shadow public abstract ServerLevel getLevel();

    @Inject(at = @At("HEAD"), method = "gameEvent", cancellable = true)
    private void pantherBoots(Holder<GameEvent> holder, Vec3 p_215042_, GameEvent.Context context, CallbackInfo ci) {
        if (context.sourceEntity() instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SOUND_DAMPENING_BOOTS)) {
            if (holder.is(GameEvent.STEP.key()) || holder.is(GameEvent.HIT_GROUND.key())) ci.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "sendParticles(Lnet/minecraft/server/level/ServerPlayer;ZDDDLnet/minecraft/network/protocol/Packet;)Z", cancellable = true)
    private void farParticles(ServerPlayer p_8637_, boolean p_8638_, double p_8639_, double p_8640_, double p_8641_, Packet<?> p_8642_, CallbackInfoReturnable<Boolean> cir) {
        if (p_8642_ instanceof ClientboundLevelParticlesPacket packet && BuiltInRegistries.PARTICLE_TYPE.getKey(packet.getParticle().getType()).getNamespace().equals(MarvelSuperheroes.MOD_ID)) {
            if (p_8637_.level() != this.getLevel()) {
                cir.setReturnValue(false);
            } else {
                p_8637_.connection.send(p_8642_);
                cir.setReturnValue(true);
            }
        }
    }
}
