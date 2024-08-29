package net.tintankgames.marvel.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.network.SpawnPointMessage;
import net.tintankgames.marvel.network.TpToPlayerMessage;

public class SpaceStoneScreen extends Screen {
    private static final ResourceLocation SPACE_STONE_LOCATION = MarvelSuperheroes.id("textures/gui/space_stone.png");
    protected int imageWidth = 204;
    protected int imageHeight = 64;
    protected int titleLabelX;
    protected int titleLabelY;
    protected int leftPos;
    protected int topPos;
    protected Holder<SoundEvent> soundEvent;

    public SpaceStoneScreen(Component component, Holder<SoundEvent> soundEvent) {
        super(component);
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.soundEvent = soundEvent;
    }

    @Override
    protected void init() {
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        addRenderableWidget(Button.builder(Component.translatable("gui.space_stone.spawn_point"), button -> {
            PacketDistributor.sendToServer(new SpawnPointMessage(soundEvent));
            minecraft.setScreen(null);
        }).bounds(leftPos + 8, topPos + 16, 80, 20).build());
        EditBox name = addRenderableWidget(new EditBox(minecraft.font, leftPos + 96, topPos + 16, 100, 20, Component.translatable("gui.space_stone.player_select")));
        name.setHint(Component.translatable("gui.space_stone.player_select"));
        addRenderableWidget(Button.builder(Component.translatable("gui.space_stone.teleport"), button -> {
            PacketDistributor.sendToServer(new TpToPlayerMessage(name.getValue(), soundEvent));
            minecraft.setScreen(null);
        }).bounds(leftPos + 96, topPos + 38, 100, 20).build());
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int i = this.leftPos;
        int j = this.topPos;
        renderTransparentBackground(graphics);
        graphics.blit(SPACE_STONE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        for (Renderable renderable : this.renderables) {
            renderable.render(graphics, mouseX, mouseY, partialTick);
        }
        RenderSystem.disableDepthTest();
        graphics.pose().pushPose();
        graphics.pose().translate((float)i, (float)j, 0.0F);
        graphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
        graphics.pose().popPose();
        RenderSystem.enableDepthTest();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
