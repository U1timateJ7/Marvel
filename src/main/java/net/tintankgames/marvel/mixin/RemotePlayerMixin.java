package net.tintankgames.marvel.mixin;

import com.google.common.collect.Streams;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.tintankgames.marvel.attachment.EntitySuit;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.item.MarvelItems;
import net.tintankgames.marvel.world.item.component.Size;
import net.tintankgames.marvel.world.item.component.SuitParts;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RemotePlayer.class)
public abstract class RemotePlayerMixin extends AbstractClientPlayer {
    public RemotePlayerMixin(ClientLevel p_250460_, GameProfile p_249912_) {
        super(p_250460_, p_249912_);
    }

    @Inject(at = @At("RETURN"), method = "tick")
    private void removeWalkingAnimation(CallbackInfo ci) {
        setData(MarvelAttachmentTypes.ENTITY_SUIT, Streams.stream(getArmorSlots()).allMatch(stack -> stack.is(MarvelItems.Tags.IRON_MAN_MARK_38_ARMOR)) ? EntitySuit.IRON_MAN_MARK_38 : EntitySuit.NONE);
        if ((getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.FLYING, false) || getItemBySlot(EquipmentSlot.MAINHAND).getOrDefault(MarvelDataComponents.FLYING, false) || getItemBySlot(EquipmentSlot.OFFHAND).getOrDefault(MarvelDataComponents.FLYING, false)) && ((hasArmor(MarvelItems.Tags.FLYING_ARMOR, true) || marvel$hasFlyingParts(false)) && (!getItemBySlot(EquipmentSlot.CHEST).has(MarvelDataComponents.SIZE) || getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SIZE, Size.NORMAL) == Size.SMALL) || getMainHandItem().is(MarvelItems.MJOLNIR) || getMainHandItem().is(MarvelItems.STORMBREAKER) || getOffhandItem().is(MarvelItems.MJOLNIR) || getOffhandItem().is(MarvelItems.STORMBREAKER))) walkAnimation.update(0, 1.0F);
    }

    @Unique
    private boolean hasArmor(TagKey<Item> tagKey, boolean needsHead) {
        boolean head = getItemBySlot(EquipmentSlot.HEAD).is(tagKey) || !needsHead;
        boolean chest = getItemBySlot(EquipmentSlot.CHEST).is(tagKey);
        boolean legs = getItemBySlot(EquipmentSlot.LEGS).is(tagKey);
        boolean feet = getItemBySlot(EquipmentSlot.FEET).is(tagKey);
        boolean hasAllParts = Streams.stream(getArmorSlots()).allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), true)).hasAllParts());
        return head && chest && legs && feet && hasAllParts;
    }

    @Unique
    private boolean marvel$hasFlyingParts(boolean bothSides) {
        SuitParts chest = getItemBySlot(EquipmentSlot.CHEST).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.CHESTPLATE, false));
        SuitParts feet = getItemBySlot(EquipmentSlot.FEET).getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(ArmorItem.Type.BOOTS, false));
        if (bothSides) return chest.parts().get(3) && chest.parts().get(5) && feet.parts().get(0) && feet.parts().get(1);
        else return (chest.parts().get(3) || chest.parts().get(5)) && (feet.parts().get(0) || feet.parts().get(1));
    }
}
