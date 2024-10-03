package net.tintankgames.marvel.mixin;

import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractSliderButton.class)
public interface AbstractSliderButtonAccessor {
	@Accessor("SLIDER_SPRITE")
	static ResourceLocation marvel$getTexture() {
		throw new AssertionError();
	}

	@Accessor("SLIDER_HANDLE_SPRITE")
	static ResourceLocation marvel$getHandleTexture() {
		throw new AssertionError();
	}

	@Accessor("SLIDER_HANDLE_HIGHLIGHTED_SPRITE")
	static ResourceLocation marvel$getHandleHighlightedTexture() {
		throw new AssertionError();
	}
}
