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
import net.tintankgames.marvel.world.inventory.SuitRepairingMenu;
import net.tintankgames.marvel.world.level.block.SuitTableBlock;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class SuitRepairingScreen extends AbstractContainerScreen<SuitRepairingMenu> {
    private static final ResourceLocation TAB_TOP_UNSELECTED_1 = MarvelSuperheroes.id("container/suit_table/tab_top_unselected_1");
    private static final ResourceLocation TAB_TOP_UNSELECTED_2 = MarvelSuperheroes.id("container/suit_table/tab_top_unselected_2");
    private static final ResourceLocation TAB_TOP_SELECTED_3 = MarvelSuperheroes.id("container/suit_table/tab_top_selected_3");
    private static final ResourceLocation SUIT_TABLE_REPAIR_LOCATION = MarvelSuperheroes.id("textures/gui/container/suit_table_repair.png");
    private SuitTabButton upgrading;
    private SuitTabButton variants;
    private SuitTabButton repairing;

    public SuitRepairingScreen(SuitRepairingMenu p_98448_, Inventory p_98449_, Component p_98450_) {
        super(p_98448_, p_98449_, SuitTableBlock.REPAIRING_TITLE);
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
        upgrading = SuitTabButton.getBuilder(SuitTableBlock.UPGRADING_TITLE, button -> {
            MarvelMenuTypes.targetMouseX = minecraft.mouseHandler.xpos;
            MarvelMenuTypes.targetMouseY = minecraft.mouseHandler.ypos;
            PacketDistributor.sendToServer(new SwitchSuitTabMessage(0));
        }).sprite(TAB_TOP_UNSELECTED_1, 26, 32).size(26, 32).build();
        variants = SuitTabButton.getBuilder(SuitTableBlock.VARIANTS_TITLE, button -> {
            MarvelMenuTypes.targetMouseX = minecraft.mouseHandler.xpos;
            MarvelMenuTypes.targetMouseY = minecraft.mouseHandler.ypos;
            PacketDistributor.sendToServer(new SwitchSuitTabMessage(1));
        }).sprite(TAB_TOP_UNSELECTED_2, 26, 32).size(26, 32).build();
        repairing = SuitTabButton.getBuilder(SuitTableBlock.REPAIRING_TITLE, button -> {}).sprite(TAB_TOP_SELECTED_3, 26, 32).size(26, 32).build();
        upgrading.setPosition(i, j - 28);
        variants.setPosition(i + 27, j - 28);
        repairing.setPosition(i + 54, j - 28);
        addRenderableWidget(upgrading);
        addRenderableWidget(variants);
        addRenderableWidget(repairing);
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
        p_283540_.blit(SUIT_TABLE_REPAIR_LOCATION, i, j, 0, 0, this.imageWidth, this.imageHeight);
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
            if (isHovering(repairing.getX() - leftPos + 3, repairing.getY() - topPos + 3, repairing.getWidth() - 6, repairing.getHeight() - 7, p_282171_, p_281909_)) {
                p_283594_.renderTooltip(this.font, repairing.getMessage(), p_282171_, p_281909_);
            }
        }
    }
}
