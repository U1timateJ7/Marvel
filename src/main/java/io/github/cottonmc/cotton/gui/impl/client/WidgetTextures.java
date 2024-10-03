package io.github.cottonmc.cotton.gui.impl.client;

import io.github.cottonmc.cotton.gui.client.LibGui;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.resources.ResourceLocation;
import net.tintankgames.marvel.mixin.AbstractButtonAccessor;
import net.tintankgames.marvel.mixin.AbstractSliderButtonAccessor;

public final class WidgetTextures {
	private static final WidgetSprites LIGHT_LABELED_SLIDER_HANDLE = new WidgetSprites(
			AbstractSliderButtonAccessor.marvel$getHandleTexture(),
			AbstractSliderButtonAccessor.marvel$getHandleHighlightedTexture()
	);
	private static final WidgetSprites DARK_LABELED_SLIDER_HANDLE = new WidgetSprites(
			id("widget/slider_handle_dark"),
			id("widget/slider_handle_highlighted_dark")
	);
	private static final WidgetSprites DARK_BUTTON = new WidgetSprites(
			id("widget/button_dark"),
			id("widget/button_disabled_dark"),
			id("widget/button_highlighted_dark")
	);
	private static final ScrollBarTextures LIGHT_SCROLL_BAR = new ScrollBarTextures(
			id("widget/scroll_bar/background_light"),
			id("widget/scroll_bar/thumb_light"),
			id("widget/scroll_bar/thumb_pressed_light"),
			id("widget/scroll_bar/thumb_hovered_light")
	);
	private static final ScrollBarTextures DARK_SCROLL_BAR = new ScrollBarTextures(
			id("widget/scroll_bar/background_dark"),
			id("widget/scroll_bar/thumb_dark"),
			id("widget/scroll_bar/thumb_pressed_dark"),
			id("widget/scroll_bar/thumb_hovered_dark")
	);

	public static WidgetSprites getButtonTextures(boolean dark) {
		return dark ? DARK_BUTTON : AbstractButtonAccessor.marvel$getTextures();
	}

	public static WidgetSprites getLabeledSliderHandleTextures(boolean dark) {
		return dark ? DARK_LABELED_SLIDER_HANDLE : LIGHT_LABELED_SLIDER_HANDLE;
	}

	public static ScrollBarTextures getScrollBarTextures(boolean dark) {
		return dark ? DARK_SCROLL_BAR : LIGHT_SCROLL_BAR;
	}

	private static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(LibGui.MOD_ID, path);
	}

	public record ScrollBarTextures(ResourceLocation background, ResourceLocation thumb, ResourceLocation thumbPressed,
									ResourceLocation thumbHovered) {
	}
}
