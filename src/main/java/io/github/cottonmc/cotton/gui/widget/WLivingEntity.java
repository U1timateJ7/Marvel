package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class WLivingEntity extends WWidget {
    @Nullable
    private LivingEntity entity;
    @Nullable
    private Style style;
    private boolean render = true;

    public WLivingEntity(@Nullable LivingEntity entity, @Nullable Style style) {
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
        if (render && entity != null) {
            float scale = 1.8F / entity.getBbHeight();
            renderEntityInInventoryFollowsAngle(context, x, y, x + width, y + height, 30 * scale, 0.0625F, f2, f3, entity);
        }
        Style hoveredTextStyle = getTextStyleAt(mouseX, mouseY);
        ScreenDrawing.drawTextHover(context, hoveredTextStyle, x + mouseX, y + mouseY);
    }

    public static void renderEntityInInventoryFollowsAngle(GuiGraphics p_282802_, int p_275688_, int p_275245_, int p_275535_, int p_294406_, float p_294663_, float p_275604_, float angleXComponent, float angleYComponent, LivingEntity p_275689_) {
        float f = (float)(p_275688_ + p_275535_) / 2.0F;
        float f1 = (float)(p_275245_ + p_294406_) / 2.0F;
        p_282802_.enableScissor(p_275688_, p_275245_, p_275535_, p_294406_);
        Quaternionf quaternionf = new Quaternionf().rotateZ((float) Math.PI);
        Quaternionf quaternionf1 = new Quaternionf().rotateX(angleYComponent * 20.0F * (float) (Math.PI / 180.0));
        quaternionf.mul(quaternionf1);
        float f4 = p_275689_.yBodyRot;
        float f5 = p_275689_.getYRot();
        float f6 = p_275689_.getXRot();
        float f7 = p_275689_.yHeadRotO;
        float f8 = p_275689_.yHeadRot;
        p_275689_.yBodyRot = 180.0F + angleXComponent * 20.0F;
        p_275689_.setYRot(180.0F + angleXComponent * 40.0F);
        p_275689_.setXRot(-angleYComponent * 20.0F);
        p_275689_.yHeadRot = p_275689_.getYRot();
        p_275689_.yHeadRotO = p_275689_.getYRot();
        float f9 = p_275689_.getScale();
        Vector3f vector3f = new Vector3f(0.0F, p_275689_.getBbHeight() / 2.0F + p_275604_ * f9, 0.0F);
        float f10 = p_294663_ / f9;
        InventoryScreen.renderEntityInInventory(p_282802_, f, f1, f10, vector3f, quaternionf, quaternionf1, p_275689_);
        p_275689_.yBodyRot = f4;
        p_275689_.setYRot(f5);
        p_275689_.setXRot(f6);
        p_275689_.yHeadRotO = f7;
        p_275689_.yHeadRot = f8;
        p_282802_.disableScissor();
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

    @Nullable
    public LivingEntity getEntity() {
        return entity;
    }

    public void setEntity(@Nullable LivingEntity entity) {
        this.entity = entity;
    }
}
