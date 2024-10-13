package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.widget.data.Texture;
import net.minecraft.resources.ResourceLocation;

public class WTickedSprite extends WSprite {
    private Runnable onTick;

    public WTickedSprite(Texture texture) {
        super(texture);
    }

    public WTickedSprite(ResourceLocation image) {
        super(image);
    }

    public WTickedSprite(ResourceLocation image, float u1, float v1, float u2, float v2) {
        super(image, u1, v1, u2, v2);
    }

    public WTickedSprite(int frameTime, ResourceLocation... frames) {
        super(frameTime, frames);
    }

    public WTickedSprite(int frameTime, Texture... frames) {
        super(frameTime, frames);
    }

    public void setOnTick(Runnable onTick) {
        this.onTick = onTick;
    }

    public Runnable getOnTick() {
        return onTick;
    }

    @Override
    public void tick() {
        super.tick();
        getOnTick().run();
    }
}
