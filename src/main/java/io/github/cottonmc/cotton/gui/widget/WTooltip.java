package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.Nullable;

public class WTooltip extends WWidget {
    private Component tooltip;

    public WTooltip(Component tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public boolean canResize() {
        return true;
    }

    @Override
    public void paint(GuiGraphics context, int x, int y, int mouseX, int mouseY) {
        Style hoveredTextStyle = getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(context, hoveredTextStyle, x + mouseX, y + mouseY);
    }

    @Nullable
    public Style getTextStyleAt(int x, int y) {
        if (isWithinBounds(x, y) && getTooltip() != null) {
            return Style.EMPTY.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, getTooltip()));
        }
        return null;
    }

    public Component getTooltip() {
        return tooltip;
    }

    public void setTooltip(Component tooltip) {
        this.tooltip = tooltip;
    }
}
