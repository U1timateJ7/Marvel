package net.tintankgames.marvel.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public class ServerLevelMixin {
    @Inject(at = @At("HEAD"), method = "gameEvent", cancellable = true)
    private void echoCapeWork(Holder<GameEvent> holder, Vec3 p_215042_, GameEvent.Context context, CallbackInfo ci) {
        if (context.sourceEntity() instanceof LivingEntity living && living.getItemBySlot(EquipmentSlot.FEET).is(MarvelItems.Tags.SOUND_DAMPENING_BOOTS)) {
            if (holder.is(GameEvent.STEP.key()) || holder.is(GameEvent.HIT_GROUND.key())) ci.cancel();
        }
    }
}
