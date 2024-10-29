package net.tintankgames.marvel.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Brain.class)
public abstract class BrainMixin<E extends LivingEntity> {
    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void noTickWhileHeld(ServerLevel level, E living, CallbackInfo ci) {
        if (level.players().stream().anyMatch(player -> player.getData(MarvelAttachmentTypes.HELD_ENTITY).entity == living)) {
            ci.cancel();
        }
    }
}
