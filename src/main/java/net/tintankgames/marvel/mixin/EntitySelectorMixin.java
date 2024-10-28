package net.tintankgames.marvel.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(EntitySelector.class)
public class EntitySelectorMixin {
    @Inject(at = @At("RETURN"), method = "pushableBy", cancellable = true)
    private static void noPushingAround(Entity pushing, CallbackInfoReturnable<Predicate<Entity>> cir) {
        cir.setReturnValue(cir.getReturnValue().and(entity -> !(pushing.getData(MarvelAttachmentTypes.HELD_ENTITY).entity == entity || entity.getData(MarvelAttachmentTypes.HELD_ENTITY).entity == pushing)));
    }
}
