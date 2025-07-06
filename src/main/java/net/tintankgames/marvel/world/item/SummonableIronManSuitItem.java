package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.input.MarvelKeyMappings;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.entity.IronManSuitPart;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.component.SuitPartItem;
import net.tintankgames.marvel.world.item.component.SuitParts;

import java.util.List;

public abstract class SummonableIronManSuitItem extends SentryIronManSuitItem {
    public final List<SuitPartItem> partPowerItems;

    public SummonableIronManSuitItem(Holder<ArmorMaterial> armorMaterial, Type type, TagKey<Item> tagKey, List<MobEffectInstance> list, List<SuitPartItem> partPowerItems, Properties properties) {
        super(armorMaterial, type, tagKey, list, List.of(), properties.component(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(type, true)));
        this.partPowerItems = partPowerItems;
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return stack.is(MarvelItems.Tags.INVISIBLE_WHEN_OPEN) && slot == EquipmentSlot.HEAD && stack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? MarvelSuperheroes.id("textures/models/suit/empty.png") : BuiltInRegistries.ITEM.getKey(this).withPath(id -> "textures/models/suit/" + id.replace("_" + getType().getName(), "") + (slot == EquipmentSlot.HEAD && stack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? "_open" : "") + layer.suffix + ".png");
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        if (entity.level().canSeeSky(entity.blockPosition()) && entity.getOwner() instanceof Player player && !entity.hasPickUpDelay() && player.getData(MarvelAttachmentTypes.VERONICA).enabled()) {
            if (entity.level() instanceof ServerLevel serverLevel) {
                SuitParts parts = stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(getType(), true));
                for (int i = 0; i < parts.parts().size(); i++) {
                    if (parts.parts().get(i)) {
                        IronManSuitPart part = MarvelEntityTypes.IRON_MAN_SUIT_PART.get().spawn(serverLevel, entity.blockPosition(), MobSpawnType.TRIGGERED);
                        part.setPos(entity.position());
                        part.setTame(true, false);
                        part.setOwnerUUID(player.getUUID());
                        ItemStack newStack = stack.copy();
                        newStack.set(MarvelDataComponents.SUIT_PARTS, SuitParts.onePart(i, parts.parts().size()));
                        part.setPiece(newStack);
                        part.setFlyingToVeronica(true);
                    }
                }
                entity.discard();
            }
            return true;
        }
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    protected double getFlightMax(ItemStack helmet, ItemStack chestplate, ItemStack leggings, ItemStack boots) {
        List<ItemStack> pieces = List.of(helmet, chestplate, leggings, boots);
        return super.getFlightMax(helmet, chestplate, leggings, boots) / (pieces.stream().allMatch(stack -> stack.getItem() instanceof ArmorItem armorItem && stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(armorItem.getType(), false)).hasAllParts()) ? 1 : 2);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        super.appendHoverText(stack, context, list, flags);
        if (!stack.getOrDefault(MarvelDataComponents.SUIT_PARTS, SuitParts.defaultParts(getType(), true)).hasAllParts()) list.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man.part_incomplete").toLanguageKey("item")).withStyle(ChatFormatting.RED));
    }

    @Override
    protected void addAbilityMessage(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable(BuiltInRegistries.ITEM.getKey(this).withPath("iron_man.key.x").toLanguageKey("item"), Component.keybind(MarvelKeyMappings.SUMMON_SUIT.getName()).withStyle(ChatFormatting.BOLD)).withStyle(ChatFormatting.GRAY));
    }
}
