package net.tintankgames.marvel.world.item;

import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import io.github.cottonmc.cotton.gui.widget.icon.LayeredTextureIcon;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.EntitySuit;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.client.model.MarvelModels;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.entity.VeronicaSentry;

import java.util.List;

public class IronManMark38SuitItem extends SentryIronManSuitItem {
    public IronManMark38SuitItem(Type type, Properties properties) {
        super(MarvelArmorMaterials.IRON_MAN_DIAMOND, type, MarvelItems.Tags.IRON_MAN_MARK_38_ARMOR, List.of(effect(MobEffects.DAMAGE_BOOST, 1), effect(MobEffects.DAMAGE_RESISTANCE, 0), effect(MobEffects.MOVEMENT_SLOWDOWN, 0)), List.of(MarvelItems.REPULSOR.get(), MarvelItems.UNIBEAM.get()), properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean p_41408_) {
        super.inventoryTick(stack, level, entity, slot, p_41408_);
        if (entity instanceof ServerPlayer player && player.getData(MarvelAttachmentTypes.ENTITY_SUIT) != EntitySuit.IRON_MAN_MARK_38) {
            entity.getSlot(slot).set(stack.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack.getItemHolder().getRegisteredName() + "_component"))));
        }
    }

    @Override
    public boolean onEntityItemUpdate(ItemStack stack, ItemEntity entity) {
        entity.setItem(stack.transmuteCopy(BuiltInRegistries.ITEM.get(ResourceLocation.parse(stack.getItemHolder().getRegisteredName() + "_component"))));
        return super.onEntityItemUpdate(stack, entity);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelLayerLocation modelFactory(Type type, ItemStack itemStack) {
        return MarvelModels.suit(type);
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return MarvelSuperheroes.id("textures/models/suit/empty.png");
    }

    @Override
    public Component mark() {
        return Component.translatable("container.suit_charger.mark_38");
    }

    @Override
    public Component hudMark() {
        return Component.translatable("gui.iron_man.mark_38");
    }

    @Override
    public Component sentryName() {
        return Component.translatable("entity.marvel.iron_man_sentry.mark_38");
    }

    @Override
    public int markNumber() {
        return 38;
    }

    @Override
    public Component veronicaName() {
        return Component.translatable("gui.veronica.iron_man_mark_38");
    }

    @Override
    public EntityType<? extends VeronicaSentry> type() {
        return MarvelEntityTypes.IRON_MAN_MARK_38.get();
    }

    @Override
    public Icon createIcon(ItemStack stack, Player player) {
        ResourceLocation icon = MarvelSuperheroes.id("textures/entity/igor/iron_man_mark_38.png");
        return new LayeredTextureIcon(new Texture(icon, 0.0625F, 0.2265625F, 0.125F, 0.2890625F), new Texture(icon.withPath(id -> id.replace(".png", "_glow.png")), 0.0625F, 0.2265625F, 0.125F, 0.2890625F));
    }
}
