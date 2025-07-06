package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.icon.Icon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class WStyledButton extends WButton {
    /**
     * Constructs a button with no label and no icon.
     */
    public WStyledButton() {
        super();
    }

    /**
     * Constructs a button with an icon.
     *
     * @param icon the icon
     */
    public WStyledButton(@Nullable Icon icon) {
        super(icon);
    }

    /**
     * Constructs a button with a label.
     *
     * @param label the label
     */
    public WStyledButton(@Nullable Component label) {
        super(label);
    }

    /**
     * Constructs a button with an icon and a label.
     *
     * @param icon  the icon
     * @param label the label
     */
    public WStyledButton(@Nullable Icon icon, @Nullable Component label) {
        super(icon, label);
    }

    @Override
    public void paint(GuiGraphics context, int x, int y, int mouseX, int mouseY) {
        super.paint(context, x, y, mouseX, mouseY);
        Style hoveredTextStyle = getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(context, hoveredTextStyle, x + mouseX, y + mouseY);
    }

    /**
     * Gets the text style at the specific widget-space coordinates.
     *
     * @param x the X coordinate in widget space
     * @param y the Y coordinate in widget space
     * @return the text style at the position, or null if not found
     */
    @Nullable
    public Style getTextStyleAt(int x, int y) {
        if (isWithinBounds(x, y) && getLabel() != null) {
            return getLabel().getStyle();
        }
        return null;
    }
}
