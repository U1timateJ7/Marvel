package io.github.cottonmc.cotton.gui.widget;

import io.github.cottonmc.cotton.gui.GuiDescription;
import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.impl.client.NarrationMessages;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

/**
 * A player inventory widget that has a visually separate hotbar.
 *
 * @see io.github.cottonmc.cotton.gui.SyncedGuiDescription#createPlayerInventoryPanel()
 */
public class WPlayerInvPanel extends WPlainPanel {
    /**
     * A 9 by 3 {@link WItemSlot} that represents the player's inventory.
     *
     * @see Inventory
     * @since 8.1.0
     */
    protected final WItemSlot inventory;
    /**
     * A 9 by 1 {@link WItemSlot} that represents the player's hotbar.
     *
     * @see Inventory
     * @since 8.1.0
     */
    protected final WItemSlot hotbar;
    /**
     * The label seen above {@link WPlayerInvPanel#inventory}.
     *
     * <p> In vanilla and {@link WPlayerInvPanel#WPlayerInvPanel(Inventory)}, this label is always 'Inventory'
     *
     * @see #createInventoryLabel(Inventory)
     * @since 8.1.0
     */
    @Nullable
    protected final WWidget label;

    /**
     * Constructs a player inventory panel with a label.
     *
     * @param playerInventory the player inventory
     */
    public WPlayerInvPanel(Inventory playerInventory) {
        this(playerInventory, true);
    }

    /**
     * Constructs a player inventory panel.
     *
     * @param playerInventory the player inventory
     * @param hasLabel        whether there should be an "Inventory" label
     * @since 2.0.0
     */
    public WPlayerInvPanel(Inventory playerInventory, boolean hasLabel) {
        this(playerInventory, hasLabel ? createInventoryLabel(playerInventory) : null);
    }

    /**
     * Constructs a player inventory panel.
     *
     * @param playerInventory the player inventory
     * @param label           the label widget, can be null
     * @since 2.0.0
     */
    public WPlayerInvPanel(Inventory playerInventory, @Nullable WWidget label) {
        int y = 0;

        this.label = label;
        if (label != null) {
            this.add(label, 0, 0, label.getWidth(), label.getHeight());
            y += label.getHeight();
        }

        inventory = WItemSlot.ofPlayerStorage(playerInventory);
        hotbar = new WItemSlot(playerInventory, 0, 9, 1, false) {
            @Override
            protected Component getNarrationName() {
                return NarrationMessages.Vanilla.HOTBAR;
            }
        };
        this.add(inventory, 0, y);
        this.add(hotbar, 0, y + 58);
    }

    @Override
    public boolean canResize() {
        return false;
    }

    /**
     * Creates a vanilla-style inventory label for a player inventory.
     *
     * @param playerInventory the player inventory
     * @return the created label
     * @since 3.1.0
     */
    public static WLabel createInventoryLabel(Inventory playerInventory) {
        WLabel label = new WLabel(playerInventory.getDisplayName());
        label.setSize(9*18, 11);
        return label;
    }

    /**
     * Sets the background painter of this inventory widget's slots.
     *
     * @param painter the new painter
     * @return this panel
     */
    @OnlyIn(Dist.CLIENT)
    @Override
    public WPanel setBackgroundPainter(BackgroundPainter painter) {
        super.setBackgroundPainter(null);
        inventory.setBackgroundPainter(painter);
        hotbar.setBackgroundPainter(painter);
        return this;
    }

    @Override
    public void validate(GuiDescription c) {
        super.validate(c);
        if (c != null && label instanceof WLabel) {
            ((WLabel) label).setColor(c.getTitleColor());
        }
    }
}
