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
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.attachment.MarvelAttachmentTypes;
import net.tintankgames.marvel.attachment.VeronicaData;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.network.SendSuitMessage;
import net.tintankgames.marvel.world.entity.IronManSentry;
import net.tintankgames.marvel.world.entity.MarvelEntityTypes;
import net.tintankgames.marvel.world.item.EnergySuitItem;
import net.tintankgames.marvel.world.item.VeronicaSuit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class VeronicaGui extends LightweightGuiDescription {
    protected int selectedSuit = -1;
    public LocalPlayer player = Minecraft.getInstance().player;
    public List<VeronicaData.Suit> suits;

    public VeronicaGui() {
        suits = player.getData(MarvelAttachmentTypes.VERONICA).getSuits();
        WText name = new WText(Component.empty());
        WText sadFace = new WText(Component.translatable("gui.veronica.no_suits.1"));
        WText noSuits = new WText(Component.translatable("gui.veronica.no_suits.2"));
        WText tip = new WText(Component.translatable("gui.veronica.no_suits.3"));
        IronManSentry sentry = new IronManSentry(MarvelEntityTypes.IRON_MAN_SENTRY.get(), player.level());
        WLivingEntity display = new WLivingEntity(sentry, null);
        display.setRender(false);
        WTooltip energyTooltip = new WTooltip(Component.empty());
        WSprite energyBackground = new WSprite(MarvelSuperheroes.id("textures/gui/sprites/veronica/energy_background.png"));
        WTickedSprite energy = new WTickedSprite(new Texture(MarvelSuperheroes.id("textures/gui/sprites/veronica/energy.png"), 0, 1, 1, 1));
        WItem helmetDisplay = new WItem(ItemStack.EMPTY, true);
        WItem chestplateDisplay = new WItem(ItemStack.EMPTY, true);
        WItem leggingsDisplay = new WItem(ItemStack.EMPTY, true);
        WItem bootsDisplay = new WItem(ItemStack.EMPTY, true);
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
                selectedSuit = suit.id();
                sentry.setItemSlot(EquipmentSlot.FEET, suit.armor().get(0));
                sentry.setItemSlot(EquipmentSlot.LEGS, suit.armor().get(1));
                sentry.setItemSlot(EquipmentSlot.CHEST, suit.armor().get(2));
                sentry.setItemSlot(EquipmentSlot.HEAD, suit.armor().get(3));
                bootsDisplay.setItems(Collections.singletonList(suit.armor().get(0)));
                leggingsDisplay.setItems(Collections.singletonList(suit.armor().get(1)));
                chestplateDisplay.setItems(Collections.singletonList(suit.armor().get(2)));
                helmetDisplay.setItems(Collections.singletonList(suit.armor().get(3)));
                energy.tick();
                name.setText(((VeronicaSuit) suit.armor().getFirst().getItem()).veronicaName());
                display.setRender(true);
                send.setEnabled(true);
            });
        });
        send.setOnClick(() -> {
            if (selectedSuit != -1 && EnergySuitItem.getEnergy(player.getData(MarvelAttachmentTypes.VERONICA).getSuit(selectedSuit).armor().get(2)) > 5.0F) {
                PacketDistributor.sendToServer(new SendSuitMessage(player.getData(MarvelAttachmentTypes.VERONICA).getSuit(selectedSuit)));
                selectedSuit = -1;
                player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(selectedSuit);
            }
        });
        sendAll.setOnClick(() -> {
            for (VeronicaData.Suit suit : player.getData(MarvelAttachmentTypes.VERONICA).getSuits()) {
                if (EnergySuitItem.getEnergy(suit.armor().get(2)) > 5.0F) PacketDistributor.sendToServer(new SendSuitMessage(suit));
                player.getData(MarvelAttachmentTypes.VERONICA).removeSuit(suit.id());
            }
            Minecraft.getInstance().setScreen(null);
        });
        energy.setOnTick(() -> {
            if (selectedSuit != -1) {
                int value = (int) (player.getData(MarvelAttachmentTypes.VERONICA).getSuit(selectedSuit).armor().get(2).getOrDefault(MarvelDataComponents.ENERGY, 0.0F) * 0.7F);
                energy.setUv(0, 1.0F - (value / 70.0F), 1, 1);
                energy.setLocation(7, 52 + (70 - value));
                energy.setSize(18, value);
                energyTooltip.setTooltip(Component.translatable("item.suit.energy", String.format("%.1f", player.getData(MarvelAttachmentTypes.VERONICA).getSuit(selectedSuit).armor().get(2).getOrDefault(MarvelDataComponents.ENERGY, 0.0F)), "%"));
            } else {
                energy.setUv(0, 1, 1, 1);
                energy.setLocation(7, 122);
                energy.setSize(18, 0);
                energyTooltip.setTooltip(Component.empty());
            }
        });
        WPlainPanel root = new WPlainPanel() {
            @Override
            public void tick() {
                if (!Arrays.equals(suits.toArray(new VeronicaData.Suit[0]), player.getData(MarvelAttachmentTypes.VERONICA).getSuits().toArray(new VeronicaData.Suit[0]), VeronicaData.Suit::compareTo)) {
                    suitList.data = player.getData(MarvelAttachmentTypes.VERONICA).getSuits();
                    suitList.layout();
                    suits = player.getData(MarvelAttachmentTypes.VERONICA).getSuits();
                    List<WWidget> children = streamChildren().toList();
                    if (selectedSuit == -1 && !suits.isEmpty()) {
                        VeronicaData.Suit suit = suits.getFirst();
                        selectedSuit = suit.id();
                        sentry.setItemSlot(EquipmentSlot.FEET, suit.armor().get(0));
                        sentry.setItemSlot(EquipmentSlot.LEGS, suit.armor().get(1));
                        sentry.setItemSlot(EquipmentSlot.CHEST, suit.armor().get(2));
                        sentry.setItemSlot(EquipmentSlot.HEAD, suit.armor().get(3));
                        bootsDisplay.setItems(Collections.singletonList(suit.armor().get(0)));
                        leggingsDisplay.setItems(Collections.singletonList(suit.armor().get(1)));
                        chestplateDisplay.setItems(Collections.singletonList(suit.armor().get(2)));
                        helmetDisplay.setItems(Collections.singletonList(suit.armor().get(3)));
                        energy.tick();
                        name.setText(((VeronicaSuit) suit.armor().getFirst().getItem()).veronicaName());
                        display.setRender(true);
                        send.setEnabled(true);
                        if (!children.contains(name)) add(name, 20, 20, 120, 10);
                        if (!children.contains(display)) add(display, 20, 40, 80, 70);
                        if (!children.contains(energyBackground)) add(energyBackground, 0, 45, 18, 70);
                        if (!children.contains(energy)) add(energy, 0, 115, 18, 0);
                        if (!children.contains(energyTooltip)) add(energyTooltip, 0, 45, 18, 70);
                        if (!children.contains(helmetDisplay)) add(helmetDisplay, 102, 44, 18, 18);
                        if (!children.contains(chestplateDisplay)) add(chestplateDisplay, 102, 62, 18, 18);
                        if (!children.contains(leggingsDisplay)) add(leggingsDisplay, 102, 80, 18, 18);
                        if (!children.contains(bootsDisplay)) add(bootsDisplay, 102, 98, 18, 18);
                        if (!children.contains(send)) add(send, 20, 120, 80, 20);
                        if (!children.contains(sendAll)) add(sendAll, 20, 158, 80, 20);
                        remove(sadFace);
                        remove(noSuits);
                        remove(tip);
                    }
                    if (suits.isEmpty()) {
                        selectedSuit = -1;
                        send.setEnabled(false);
                        sendAll.setEnabled(!player.getData(MarvelAttachmentTypes.VERONICA).getSuits().isEmpty());
                        name.setText(Component.empty());
                        display.setRender(false);
                        bootsDisplay.setItems(Collections.singletonList(ItemStack.EMPTY));
                        leggingsDisplay.setItems(Collections.singletonList(ItemStack.EMPTY));
                        chestplateDisplay.setItems(Collections.singletonList(ItemStack.EMPTY));
                        helmetDisplay.setItems(Collections.singletonList(ItemStack.EMPTY));
                        energy.tick();
                        remove(name);
                        remove(display);
                        remove(energyBackground);
                        remove(energy);
                        remove(energyTooltip);
                        remove(helmetDisplay);
                        remove(chestplateDisplay);
                        remove(leggingsDisplay);
                        remove(bootsDisplay);
                        remove(send);
                        remove(sendAll);
                        if (!children.contains(sadFace)) add(sadFace, 170, 90, 15, 10);
                        if (!children.contains(noSuits)) add(noSuits, 90, 100, 200, 10);
                        if (!children.contains(tip)) add(tip, 60, 110, 260, 10);
                    }
                } else {
                    super.tick();
                }
            }
        };
        root.setSize(350, 200);
        root.setInsets(Insets.ROOT_PANEL);
        setRootPanel(root);
        root.add(suitList, 165, 10, 185, 180);
        if (!suits.isEmpty()) {
            VeronicaData.Suit suit = suits.getFirst();
            selectedSuit = suit.id();
            sentry.setItemSlot(EquipmentSlot.FEET, suit.armor().get(0));
            sentry.setItemSlot(EquipmentSlot.LEGS, suit.armor().get(1));
            sentry.setItemSlot(EquipmentSlot.CHEST, suit.armor().get(2));
            sentry.setItemSlot(EquipmentSlot.HEAD, suit.armor().get(3));
            bootsDisplay.setItems(Collections.singletonList(suit.armor().get(0)));
            leggingsDisplay.setItems(Collections.singletonList(suit.armor().get(1)));
            chestplateDisplay.setItems(Collections.singletonList(suit.armor().get(2)));
            helmetDisplay.setItems(Collections.singletonList(suit.armor().get(3)));
            name.setText(((VeronicaSuit) suit.armor().getFirst().getItem()).veronicaName());
            display.setRender(true);
            send.setEnabled(true);
            root.add(name, 20, 20, 120, 10);
            root.add(display, 20, 40, 80, 70);
            root.add(energyBackground, 0, 45, 18, 70);
            root.add(energy, 0, 115, 18, 0);
            root.add(energyTooltip, 0, 45, 18, 70);
            root.add(helmetDisplay, 102, 44, 18, 18);
            root.add(chestplateDisplay, 102, 62, 18, 18);
            root.add(leggingsDisplay, 102, 80, 18, 18);
            root.add(bootsDisplay, 102, 98, 18, 18);
            root.add(send, 20, 120, 80, 20);
            root.add(sendAll, 20, 158, 80, 20);
        } else {
            root.add(sadFace, 170, 90, 15, 10);
            root.add(noSuits, 90, 100, 200, 10);
            root.add(tip, 60, 110, 260, 10);
        }

        root.validate(this);
    }
}
