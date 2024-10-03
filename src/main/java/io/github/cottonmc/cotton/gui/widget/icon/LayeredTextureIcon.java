package io.github.cottonmc.cotton.gui.widget.icon;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Arrays;
import java.util.List;

/**
 * An icon that draws a texture.
 *
 * @since 2.2.0
 */
public class LayeredTextureIcon implements Icon {
    private final List<Texture> textures;
    private float opacity = 1f;
    private int color = 0xFF_FFFFFF;

    /**
     * Constructs a new texture icon.
     *
     * @param texture the identifier of the icon texture
     */
    public LayeredTextureIcon(ResourceLocation... texture) {
        this(Arrays.stream(texture).map(Texture::new).toArray(Texture[]::new));
    }

    /**
     * Constructs a new texture icon.
     *
     * @param texture the identifier of the icon texture
     * @since 3.0.0
     */
    public LayeredTextureIcon(Texture... texture) {
        this.textures = List.of(texture);
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
    public LayeredTextureIcon setOpacity(float opacity) {
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
    public LayeredTextureIcon setColor(int color) {
        this.color = color;
        return this;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void paint(GuiGraphics context, int x, int y, int size) {
        for (Texture texture : textures) {
            ScreenDrawing.texturedRect(context, x, y, size, size, texture, color, opacity);
        }
    }
}
