package net.tintankgames.marvel.client.gui.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.gui.components.SuitTabButton;
import net.tintankgames.marvel.network.SwitchSuitTabMessage;
import net.tintankgames.marvel.world.inventory.MarvelMenuTypes;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import net.tintankgames.marvel.world.level.block.SuitTableBlock;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class SuitUpgradingScreen extends AbstractContainerScreen<SuitUpgradingMenu> {
    private static final ResourceLocation TAB_TOP_SELECTED_1 = MarvelSuperheroes.id("container/suit_table/tab_top_selected_1");
    private static final ResourceLocation TAB_TOP_UNSELECTED_2 = MarvelSuperheroes.id("container/suit_table/tab_top_unselected_2");
    private static final ResourceLocation SUIT_TABLE_UPGRADE_LOCATION = MarvelSuperheroes.id("textures/gui/container/suit_table_upgrade.png");
    private SuitTabButton upgrading;
    private SuitTabButton variants;

    public SuitUpgradingScreen(SuitUpgradingMenu p_98448_, Inventory p_98449_, Component p_98450_) {
        super(p_98448_, p_98449_, SuitTableBlock.UPGRADING_TITLE);
        this.titleLabelY--;
    }

    @Override
    protected void init() {
        super.init();
        if (MarvelMenuTypes.targetMouseX >= 0 && MarvelMenuTypes.targetMouseY >= 0) {
            GLFW.glfwSetCursorPos(Minecraft.getInstance().getWindow().getWindow(), MarvelMenuTypes.targetMouseX, MarvelMenuTypes.targetMouseY);
            MarvelMenuTypes.targetMouseX = -1;
            MarvelMenuTypes.targetMouseY = -1;
        }
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        upgrading = SuitTabButton.getBuilder(Component.translatable("container.suit_upgrading"), button -> {}).sprite(TAB_TOP_SELECTED_1, 26, 32).size(26, 32).build();
        variants = SuitTabButton.getBuilder(Component.translatable("container.suit_variants"), button -> {
            MarvelMenuTypes.targetMouseX = minecraft.mouseHandler.xpos;
            MarvelMenuTypes.targetMouseY = minecraft.mouseHandler.ypos;
            PacketDistributor.sendToServer(new SwitchSuitTabMessage(true));
        }).sprite(TAB_TOP_UNSELECTED_2, 26, 32).size(26, 32).build();
        upgrading.setPosition(i, j - 28);
        variants.setPosition(i + 27, j - 28);
        addRenderableWidget(upgrading);
        addRenderableWidget(variants);
    }

    @Override
    public void render(GuiGraphics p_282508_, int p_98480_, int p_98481_, float p_98482_) {
        super.render(p_282508_, p_98480_, p_98481_, p_98482_);
        this.renderTooltip(p_282508_, p_98480_, p_98481_);
    }

    @Override
    protected void renderBg(GuiGraphics p_283540_, float p_282132_, int p_283078_, int p_283647_) {
        int i = this.leftPos;
        int j = (this.height - this.imageHeight) / 2;
        p_283540_.blit(SUIT_TABLE_UPGRADE_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    protected void renderTooltip(GuiGraphics p_283594_, int p_282171_, int p_281909_) {
        super.renderTooltip(p_283594_, p_282171_, p_281909_);
        if (this.menu.getCarried().isEmpty()) {
            if (isHovering(upgrading.getX() - leftPos + 3, upgrading.getY() - topPos + 3, upgrading.getWidth() - 6, upgrading.getHeight() - 7, p_282171_, p_281909_)) {
                p_283594_.renderTooltip(this.font, upgrading.getMessage(), p_282171_, p_281909_);
            }
            if (isHovering(variants.getX() - leftPos + 3, variants.getY() - topPos + 3, variants.getWidth() - 6, variants.getHeight() - 7, p_282171_, p_281909_)) {
                p_283594_.renderTooltip(this.font, variants.getMessage(), p_282171_, p_281909_);
            }
        }
    }
}
