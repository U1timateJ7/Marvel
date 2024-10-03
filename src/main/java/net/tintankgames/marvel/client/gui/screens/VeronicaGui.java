package net.tintankgames.marvel.client.gui.screens;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.*;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.LayeredTextureIcon;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.network.SendSuitMessage;
import net.tintankgames.marvel.world.entity.IronManSentry;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.VeronicaSuit;

@OnlyIn(Dist.CLIENT)
public class VeronicaGui extends LightweightGuiDescription {
    protected VeronicaData.Suit selectedSuit = null;
    public LocalPlayer player = Minecraft.getInstance().player;

    public VeronicaGui() {
        WPlainPanel root = new WPlainPanel();
        root.setSize(350, 200);
        root.setInsets(Insets.ROOT_PANEL);
        setRootPanel(root);

        WText name = new WText(Component.empty());
        IronManSentry sentry = new IronManSentry(MarvelEntityTypes.IRON_MAN_SENTRY.get(), player.level());
        WLivingEntity display = new WLivingEntity(sentry, null);
        display.setRender(false);
        WButton send = new WButton(Component.translatable("gui.veronica.send"));
        send.setEnabled(false);
        WButton sendAll = new WButton(Component.translatable("gui.veronica.send_all"));
        sendAll.setEnabled(!player.getData(MarvelAttachmentTypes.VERONICA).getSuits().isEmpty());
        WListPanel<VeronicaData.Suit, WButton> suitList = new WListPanel<>(player.getData(MarvelAttachmentTypes.VERONICA).getSuits(), WButton::new, (suit, wButton) -> {
            wButton.setSize(180, 20);
            ItemStack helmet = suit.armor().get(3).copy();
            if (helmet.has(MarvelDataComponents.HELMET_OPEN)) helmet.set(MarvelDataComponents.HELMET_OPEN, false);
            ArmorItem helmetItem = (ArmorItem) suit.armor().get(3).getItem();
            ResourceLocation icon = helmetItem.getArmorTexture(helmet, player, EquipmentSlot.HEAD, helmetItem.getMaterial().value().layers().getFirst(), false);
            if (icon != null) wButton.setIcon(new LayeredTextureIcon(new Texture(icon, 0.125F, 0.125F, 0.25F, 0.25F), new Texture(icon.withPath(id -> id.replace(".png", "_glow.png")), 0.125F, 0.125F, 0.25F, 0.25F)));
            wButton.setLabel(((VeronicaSuit) suit.armor().getFirst().getItem()).veronicaName());
            wButton.setOnClick(() -> {
                selectedSuit = suit;
                sentry.setItemSlot(EquipmentSlot.FEET, selectedSuit.armor().get(0));
                sentry.setItemSlot(EquipmentSlot.LEGS, selectedSuit.armor().get(1));
                sentry.setItemSlot(EquipmentSlot.CHEST, selectedSuit.armor().get(2));
                sentry.setItemSlot(EquipmentSlot.HEAD, selectedSuit.armor().get(3));
                name.setText(((VeronicaSuit) selectedSuit.armor().getFirst().getItem()).veronicaName());
                display.setRender(true);
                send.setEnabled(true);
            });
        });
        send.setOnClick(() -> {
            if (selectedSuit != null) {
                PacketDistributor.sendToServer(new SendSuitMessage(selectedSuit));
                player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(selectedSuit);
                selectedSuit = null;
                send.setEnabled(false);
                sendAll.setEnabled(!player.getData(MarvelAttachmentTypes.VERONICA).getSuits().isEmpty());
                name.setText(Component.empty());
                display.setRender(false);
                suitList.data = player.getData(MarvelAttachmentTypes.VERONICA).getSuits();
                suitList.layout();
            }
        });
        sendAll.setOnClick(() -> {
            for (VeronicaData.Suit suit : player.getData(MarvelAttachmentTypes.VERONICA).getSuits()) {
                PacketDistributor.sendToServer(new SendSuitMessage(suit));
            }
            player.getData(MarvelAttachmentTypes.VERONICA).clear();
            Minecraft.getInstance().setScreen(null);
        });
        root.add(name, 20, 20, 120, 10);
        root.add(display, 20, 40, 80, 70);
        root.add(send, 20, 120, 80, 20);
        root.add(sendAll, 20, 150, 80, 20);
        root.add(suitList, 165, 10, 185, 180);

        root.validate(this);
    }
}
