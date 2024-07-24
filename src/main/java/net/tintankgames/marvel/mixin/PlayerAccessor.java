package net.tintankgames.marvel.mixin;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Player.class)
public interface PlayerAccessor {
    @Invoker
    float invokeGetEnchantedDamage(Entity p_345248_, float p_345836_, DamageSource p_345745_);
}
