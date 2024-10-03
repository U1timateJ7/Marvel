package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

public class WLivingEntity extends WWidget {
    private final LivingEntity entity;
    @Nullable
    private Style style;
    private boolean render = true;

    public WLivingEntity(LivingEntity entity, @Nullable Style style) {
        this.entity = entity;
        this.style = style;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public boolean canResize() {
        return true;
    }

    @Override
    public void paint(GuiGraphics context, int x, int y, int mouseX, int mouseY) {
        float f = (x - width) / 2.0F;
        float f1 = (y - height) / 2.0F;
        float f2 = (float)Math.atan((f - mouseX) / 40.0F);
        float f3 = (float)Math.atan((f1 - mouseY) / 40.0F);
        if (render) InventoryScreen.renderEntityInInventoryFollowsAngle(context, x, y, x + width, y + height, 30, 0.0625F, f2, f3, entity);
        Style hoveredTextStyle = getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(context, hoveredTextStyle, x + mouseX, y + mouseY);
    }

    @Nullable
    public Style getTextStyleAt(int x, int y) {
        if (isWithinBounds(x, y) && getStyle() != null) {
            return getStyle();
        }
        return null;
    }

    @Nullable
    public Style getStyle() {
        return style;
    }

    public void setStyle(@Nullable Style style) {
        this.style = style;
    }
}
