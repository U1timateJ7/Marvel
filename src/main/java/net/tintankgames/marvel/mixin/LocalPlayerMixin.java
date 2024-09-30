package net.tintankgames.marvel.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer {
    public LocalPlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
        super(p_250460_, p_249912_);
    }

    @Shadow public abstract void onUpdateAbilities();
    @Shadow public Input input;

    @ModifyArg(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V"), method = "aiStep")
    private Vec3 fastFlying(Vec3 par1) {
        int j = 0;
        if (this.input.shiftKeyDown) {
            j--;
        }
        if (this.input.jumping) {
            j++;
        }
        if (marvel$hasArmor(MarvelItems.Tags.IRON_MAN_MARK_19_ARMOR, true)) return this.getDeltaMovement().add(0.0, j * getAbilities().getFlyingSpeed() * 2.0F * 3.0F, 0.0);
        return par1;
    }

    @Unique
    private boolean marvel$hasArmor(TagKey<Item> tagKey, boolean needsHead) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        return head && chest && legs && feet;
    }
}
