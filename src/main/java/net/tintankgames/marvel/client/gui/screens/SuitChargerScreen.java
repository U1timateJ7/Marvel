package net.tintankgames.marvel.client.gui.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.world.inventory.SuitChargerMenu;
import net.tintankgames.marvel.world.item.SuitChargerItem;

@OnlyIn(Dist.CLIENT)
public class SuitChargerScreen extends AbstractContainerScreen<SuitChargerMenu> {
    private float xMouse;
    private float yMouse;
    private static final ResourceLocation SUIT_CHARGER_LOCATION = MarvelSuperheroes.id("textures/gui/container/suit_charger.png");

    public SuitChargerScreen(SuitChargerMenu p_98448_, Inventory p_98449_, Component p_98450_) {
        super(p_98448_, p_98449_, p_98450_);
    }

    @Override
    public void render(GuiGraphics p_282508_, int p_98480_, int p_98481_, float p_98482_) {
        super.render(p_282508_, p_98480_, p_98481_, p_98482_);
        this.renderTooltip(p_282508_, p_98480_, p_98481_);
        this.xMouse = (float) p_98480_;
        this.yMouse = (float) p_98481_;
    }

    protected void renderLabels(GuiGraphics p_281635_, int p_282681_, int p_283686_) {
        if (menu.slots.get(0).hasItem() && menu.slots.get(1).hasItem() && menu.slots.get(2).hasItem() && menu.slots.get(3).hasItem() && menu.slots.get(1).getItem().getItem() instanceof SuitChargerItem suitChargerItem) p_281635_.drawString(this.font, suitChargerItem.mark(), 71 - (this.font.width(suitChargerItem.mark()) / 2), 9, 16777215, false);
    }

    @Override
    protected void renderBg(GuiGraphics p_283540_, float p_282132_, int p_283078_, int p_283647_) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        p_283540_.blit(SUIT_CHARGER_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
        if (menu.slots.get(0).hasItem() && menu.slots.get(1).hasItem() && menu.slots.get(2).hasItem() && menu.slots.get(3).hasItem()) {
            boolean flag = menu.slots.get(0).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(1).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(2).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(3).getItem().has(MarvelDataComponents.ENERGY);
            if (flag) p_283540_.blit(SUIT_CHARGER_LOCATION, i + 25, j + 8 + (70 - (int) (menu.slots.get(1).getItem().getOrDefault(MarvelDataComponents.ENERGY, 0.0F) * 0.7F)), this.imageWidth, 70 - (int) (menu.slots.get(1).getItem().getOrDefault(MarvelDataComponents.ENERGY, 0.0F) * 0.7F), 18, (int) (menu.slots.get(1).getItem().getOrDefault(MarvelDataComponents.ENERGY, 0.0F) * 0.7F));
            else p_283540_.blit(SUIT_CHARGER_LOCATION, i + 25, j + 8, this.imageWidth + 18, 0, 18, 70);
        }
        InventoryScreen.renderEntityInInventoryFollowsMouse(p_283540_, i + 101, j + 8, i + 150, j + 78, 30, 0.0625F, this.xMouse, this.yMouse, this.minecraft.player);
    }

    @Override
    protected void renderTooltip(GuiGraphics p_283594_, int p_282171_, int p_281909_) {
        super.renderTooltip(p_283594_, p_282171_, p_281909_);
        if (this.menu.getCarried().isEmpty()) {
            if (isHovering(26, 9, 16, 68, p_282171_, p_281909_) && menu.slots.get(0).hasItem() && menu.slots.get(1).hasItem() && menu.slots.get(2).hasItem() && menu.slots.get(3).hasItem()) {
                boolean flag = menu.slots.get(0).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(1).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(2).getItem().has(MarvelDataComponents.ENERGY) && menu.slots.get(3).getItem().has(MarvelDataComponents.ENERGY);
                p_283594_.renderTooltip(this.font, flag ? Component.translatable("item.suit.energy", String.format("%.1f", menu.slots.get(1).getItem().getOrDefault(MarvelDataComponents.ENERGY, 0.0F)), "%") : Component.translatable("container.suit_charger.cant_charge"), p_282171_, p_281909_);
            }
        }
    }
}
