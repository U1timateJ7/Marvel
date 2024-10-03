package net.tintankgames.marvel.client.gui.components;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.SpriteIconButton;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class SuitTabButton extends SpriteIconButton {
    protected SuitTabButton(int p_295914_, int p_294852_, Component p_295609_, int p_294922_, int p_296462_, ResourceLocation p_295554_, Button.OnPress p_294427_, @Nullable Button.CreateNarration p_330653_) {
        super(p_295914_, p_294852_, p_295609_, p_294922_, p_296462_, p_295554_, p_294427_, p_330653_);
    }

    @Override
    public void renderWidget(GuiGraphics p_295402_, int p_295733_, int p_294839_, float p_296191_) {
        int i = this.getX() + this.getWidth() / 2 - this.spriteWidth / 2;
        int j = this.getY() + this.getHeight() / 2 - this.spriteHeight / 2;
        p_295402_.blitSprite(this.sprite, i, j, this.spriteWidth, this.spriteHeight);
    }

    public static SuitTabButton.Builder getBuilder(Component p_294639_, Button.OnPress p_295155_) {
        return new SuitTabButton.Builder(p_294639_, p_295155_);
    }

    @Override
    public void renderString(GuiGraphics p_294683_, Font p_295870_, int p_295770_) {
    }

    @OnlyIn(Dist.CLIENT)
    public static class Builder {
        private final Component message;
        private final Button.OnPress onPress;
        private int width = 150;
        private int height = 20;
        @Nullable
        private ResourceLocation sprite;
        private int spriteWidth;
        private int spriteHeight;
        @Nullable
        Button.CreateNarration narration;

        public Builder(Component p_294568_, Button.OnPress p_294520_) {
            this.message = p_294568_;
            this.onPress = p_294520_;
        }

        public Builder width(int p_295136_) {
            this.width = p_295136_;
            return this;
        }

        public Builder size(int p_295812_, int p_296135_) {
            this.width = p_295812_;
            this.height = p_296135_;
            return this;
        }

        public Builder sprite(ResourceLocation p_295718_, int p_296046_, int p_295188_) {
            this.sprite = p_295718_;
            this.spriteWidth = p_296046_;
            this.spriteHeight = p_295188_;
            return this;
        }

        public Builder narration(Button.CreateNarration p_331295_) {
            this.narration = p_331295_;
            return this;
        }

        public SuitTabButton build() {
            if (this.sprite == null) {
                throw new IllegalStateException("Sprite not set");
            } else {
                return new SuitTabButton(
                        this.width, this.height, this.message, this.spriteWidth, this.spriteHeight, this.sprite, this.onPress, this.narration
                );
            }
        }
    }
}
