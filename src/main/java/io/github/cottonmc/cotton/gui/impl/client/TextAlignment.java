package io.github.cottonmc.cotton.gui.impl.client;

import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import io.github.cottonmc.cotton.gui.widget.data.VerticalAlignment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.util.FormattedCharSequence;

public final class TextAlignment {
	public static int getTextOffsetX(HorizontalAlignment alignment, int width, FormattedCharSequence text) {
		return switch (alignment) {
			case LEFT -> 0;

			case CENTER -> {
				Font renderer = Minecraft.getInstance().font;
				int textWidth = renderer.width(text);
				yield width / 2 - textWidth / 2;
			}

			case RIGHT -> {
				Font renderer = Minecraft.getInstance().font;
				int textWidth = renderer.width(text);
				yield width - textWidth;
			}
		};
	}

	public static int getTextOffsetY(VerticalAlignment alignment, int height, int lines) {
		return switch (alignment) {
			case TOP -> 0;

			case CENTER -> {
				Font renderer = Minecraft.getInstance().font;
				int textHeight = renderer.lineHeight * lines;
				yield height / 2 - textHeight / 2;
			}

			case BOTTOM -> {
				Font renderer = Minecraft.getInstance().font;
				int textHeight = renderer.lineHeight * lines;
				yield height - textHeight;
			}
		};
	}
}
