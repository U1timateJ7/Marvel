package net.tintankgames.marvel.client.gui.screens;

import io.github.cottonmc.cotton.gui.client.CottonClientScreen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VeronicaScreen extends CottonClientScreen {
    public VeronicaScreen() {
        super(Component.translatable("gui.veronica.title"), new VeronicaGui());
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
