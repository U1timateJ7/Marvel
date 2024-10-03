package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.impl.client.NarrationMessages;
import io.github.cottonmc.cotton.gui.impl.client.WidgetTextures;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.mixin.AbstractSliderButtonAccessor;
import org.jetbrains.annotations.Nullable;

/**
 * A vanilla-style labeled slider widget.
 *
 * <p>In addition to the standard slider listeners,
 * labeled sliders also support "label updaters" that can update the label
 * when the value is changed.
 *
 * @see WAbstractSlider for more information about listeners
 */
public class WLabeledSlider extends WAbstractSlider {
    @Nullable private Component label = null;
    @Nullable private LabelUpdater labelUpdater = null;
    private HorizontalAlignment labelAlignment = HorizontalAlignment.CENTER;

    /**
     * Constructs a horizontal slider with no default label.
     *
     * @param min the minimum value
     * @param max the maximum value
     */
    public WLabeledSlider(int min, int max) {
        this(min, max, Axis.HORIZONTAL);
    }

    /**
     * Constructs a slider with no default label.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @param axis the slider axis
     */
    public WLabeledSlider(int min, int max, Axis axis) {
        super(min, max, axis);
    }

    /**
     * Constructs a slider.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @param axis the slider axis
     * @param label the slider label (can be null)
     */
    public WLabeledSlider(int min, int max, Axis axis, @Nullable Component label) {
        this(min, max, axis);
        this.label = label;
    }

    /**
     * Constructs a horizontal slider.
     *
     * @param min the minimum value
     * @param max the maximum value
     * @param label the slider label (can be null)
     */
    public WLabeledSlider(int min, int max, @Nullable Component label) {
        this(min, max);
        this.label = label;
    }

    /**
     * Gets the current label of this slider.
     *
     * @return the label
     */
    @Nullable
    public Component getLabel() {
        return label;
    }

    /**
     * Sets the label of this slider.
     *
     * @param label the new label
     */
    public void setLabel(@Nullable Component label) {
        this.label = label;
    }

    @Override
    protected void onValueChanged(int value) {
        super.onValueChanged(value);
        if (labelUpdater != null) {
            label = labelUpdater.updateLabel(value);
        }
    }

    /**
     * Gets the text alignment of this slider's label.
     *
     * @return the alignment
     */
    public HorizontalAlignment getLabelAlignment() {
        return labelAlignment;
    }

    /**
     * Sets the text alignment of this slider's label.
     *
     * @param labelAlignment the new alignment
     */
    public void setLabelAlignment(HorizontalAlignment labelAlignment) {
        this.labelAlignment = labelAlignment;
    }

    /**
     * Gets the {@link LabelUpdater} of this slider.
     *
     * @return the label updater
     */
    @Nullable
    public LabelUpdater getLabelUpdater() {
        return labelUpdater;
    }

    /**
     * Sets the {@link LabelUpdater} of this slider.
     *
     * @param labelUpdater the new label updater
     */
    public void setLabelUpdater(@Nullable LabelUpdater labelUpdater) {
        this.labelUpdater = labelUpdater;
    }

    @Override
    protected int getThumbWidth() {
        return 8;
    }

    @Override
    protected boolean isMouseInsideBounds(int x, int y) {
        return x >= 0 && x <= width && y >= 0 && y <= height;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void paint(GuiGraphics context, int x, int y, int mouseX, int mouseY) {
        int aWidth = axis == Axis.HORIZONTAL ? width : height;
        int aHeight = axis == Axis.HORIZONTAL ? height : width;
        int rotMouseX = axis == Axis.HORIZONTAL
                ? (direction == Direction.LEFT ? width - mouseX : mouseX)
                : (direction == Direction.UP ? height - mouseY : mouseY);
        int rotMouseY = axis == Axis.HORIZONTAL ? mouseY : mouseX;

        var matrices = context.pose();
        matrices.pushPose();
        matrices.translate(x, y, 0);
        if (axis == Axis.VERTICAL) {
            matrices.translate(0, height, 0);
            matrices.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(270));
        }
        context.blitSprite(AbstractSliderButtonAccessor.marvel$getTexture(), 0, 0, aWidth, aHeight);

        int thumbX = Math.round(coordToValueRatio * (value - min));
        int thumbY = 0;
        int thumbWidth = getThumbWidth();
        int thumbHeight = aHeight;
        boolean hovering = rotMouseX >= thumbX && rotMouseX <= thumbX + thumbWidth && rotMouseY >= thumbY && rotMouseY <= thumbY + thumbHeight;

        var thumbTextures = WidgetTextures.getLabeledSliderHandleTextures(shouldRenderInDarkMode());
        var thumbTexture = thumbTextures.get(true, dragging || hovering);
        context.blitSprite(thumbTexture, thumbX, thumbY, thumbWidth, thumbHeight);

        if (label != null) {
            int color = isMouseInsideBounds(mouseX, mouseY) ? 0xFFFFA0 : 0xE0E0E0;
            ScreenDrawing.drawStringWithShadow(context, label.getVisualOrderText(), labelAlignment, 2, aHeight / 2 - 4, aWidth - 4, color);
        }
        matrices.popPose();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addNarrations(NarrationElementOutput builder) {
        if (getLabel() != null) {
            builder.add(NarratedElementType.TITLE, Component.translatable(NarrationMessages.LABELED_SLIDER_TITLE_KEY, getLabel(), value, min, max));
            builder.add(NarratedElementType.USAGE, NarrationMessages.SLIDER_USAGE);
        } else {
            super.addNarrations(builder);
        }
    }

    /**
     * A label updater updates the label of a slider based on the current value.
     *
     * <p>Useful for situations when you want to have display values on the slider.
     */
    @FunctionalInterface
    public interface LabelUpdater {
        /**
         * Gets the updated label for the new slider value.
         *
         * @param value the slider value
         * @return the label
         */
        Component updateLabel(int value);
    }
}
