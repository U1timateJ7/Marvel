package io.github.cottonmc.cotton.gui.widget.icon;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * An icon that draws a texture.
 *
 * @since 2.2.0
 */
public class TextureIcon implements Icon {
    private final Texture texture;
    private float opacity = 1f;
    private int color = 0xFF_FFFFFF;

    /**
     * Constructs a new texture icon.
     *
     * @param texture the identifier of the icon texture
     */
    public TextureIcon(ResourceLocation texture) {
        this(new Texture(texture));
    }

    /**
     * Constructs a new texture icon.
     *
     * @param texture the identifier of the icon texture
     * @since 3.0.0
     */
    public TextureIcon(Texture texture) {
        this.texture = texture;
    }

    /**
     * Gets the opacity of the texture.
     *
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * Sets the opacity of the texture.
     *
     * @param opacity the new opacity between 0 (fully transparent) and 1 (fully opaque)
     * @return this icon
     */
    public TextureIcon setOpacity(float opacity) {
        this.opacity = opacity;
        return this;
    }

    /**
     * Gets the color tint of the texture.
     *
     * @return the color tint
     */
    public int getColor() {
        return color;
    }

    /**
     * Sets the color tint of the texture.
     *
     * @param color the new color tint
     * @return this icon
     */
    public TextureIcon setColor(int color) {
        this.color = color;
        return this;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void paint(GuiGraphics context, int x, int y, int size) {
        ScreenDrawing.texturedRect(context, x, y, size, size, texture, color, opacity);
    }
}
