package net.tintankgames.marvel.client.gui.screens;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.client.gui.components.SuitTabButton;
import net.tintankgames.marvel.client.gui.screens.recipebook.SuitUpgradingRecipeBookComponent;
import net.tintankgames.marvel.network.SwitchSuitTabMessage;
import net.tintankgames.marvel.world.inventory.MarvelMenuTypes;
import net.tintankgames.marvel.world.inventory.SuitUpgradingMenu;
import net.tintankgames.marvel.world.level.block.SuitTableBlock;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class SuitUpgradingScreen extends AbstractContainerScreen<SuitUpgradingMenu> implements RecipeUpdateListener {
    private static final ResourceLocation TAB_TOP_SELECTED_1 = MarvelSuperheroes.id("container/suit_table/tab_top_selected_1");
    private static final ResourceLocation TAB_TOP_UNSELECTED_2 = MarvelSuperheroes.id("container/suit_table/tab_top_unselected_2");
    private static final ResourceLocation SUIT_TABLE_UPGRADE_LOCATION = MarvelSuperheroes.id("textures/gui/container/suit_table_upgrade.png");
    private final RecipeBookComponent recipeBookComponent = new SuitUpgradingRecipeBookComponent();
    private boolean widthTooNarrow;
    private SuitTabButton upgrading;
    private SuitTabButton variants;

    public SuitUpgradingScreen(SuitUpgradingMenu p_98448_, Inventory p_98449_, Component p_98450_) {
        super(p_98448_, p_98449_, SuitTableBlock.UPGRADING_TITLE);
        this.titleLabelY--;
    }

    @Override
    protected void init() {
        super.init();
        this.widthTooNarrow = this.width < 379;
        this.recipeBookComponent.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.menu);
        this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
        this.addRenderableWidget(new ImageButton(this.leftPos + 5, this.height / 2 - 31, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_313433_ -> {
            this.recipeBookComponent.toggleVisibility();
            this.leftPos = this.recipeBookComponent.updateScreenPosition(this.width, this.imageWidth);
            p_313433_.setPosition(this.leftPos + 5, this.height / 2 - 31);
            int i = this.leftPos;
            int j = (this.height - this.imageHeight) / 2;
            upgrading.setPosition(i, j - 28);
            variants.setPosition(i + 27, j - 28);
        }));
        this.addWidget(this.recipeBookComponent);
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
    public void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    @Override
    public void render(GuiGraphics p_282508_, int p_98480_, int p_98481_, float p_98482_) {
        if (this.recipeBookComponent.isVisible() && this.widthTooNarrow) {
            this.renderBackground(p_282508_, p_98480_, p_98481_, p_98482_);
            this.recipeBookComponent.render(p_282508_, p_98480_, p_98481_, p_98482_);
        } else {
            super.render(p_282508_, p_98480_, p_98481_, p_98482_);
            this.recipeBookComponent.render(p_282508_, p_98480_, p_98481_, p_98482_);
            this.recipeBookComponent.renderGhostRecipe(p_282508_, this.leftPos, this.topPos, true, p_98482_);
        }

        this.renderTooltip(p_282508_, p_98480_, p_98481_);
        this.recipeBookComponent.renderTooltip(p_282508_, this.leftPos, this.topPos, p_98480_, p_98481_);
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

    @Override
    public boolean keyPressed(int p_320488_, int p_320952_, int p_320483_) {
        return this.recipeBookComponent.keyPressed(p_320488_, p_320952_, p_320483_) ? true : super.keyPressed(p_320488_, p_320952_, p_320483_);
    }

    @Override
    public boolean charTyped(char p_320706_, int p_320696_) {
        return this.recipeBookComponent.charTyped(p_320706_, p_320696_) ? true : super.charTyped(p_320706_, p_320696_);
    }

    @Override
    protected boolean isHovering(int p_98462_, int p_98463_, int p_98464_, int p_98465_, double p_98466_, double p_98467_) {
        return (!this.widthTooNarrow || !this.recipeBookComponent.isVisible()) && super.isHovering(p_98462_, p_98463_, p_98464_, p_98465_, p_98466_, p_98467_);
    }

    @Override
    public boolean mouseClicked(double p_98452_, double p_98453_, int p_98454_) {
        if (this.recipeBookComponent.mouseClicked(p_98452_, p_98453_, p_98454_)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return this.widthTooNarrow && this.recipeBookComponent.isVisible() ? true : super.mouseClicked(p_98452_, p_98453_, p_98454_);
        }
    }

    @Override
    protected boolean hasClickedOutside(double p_98456_, double p_98457_, int p_98458_, int p_98459_, int p_98460_) {
        boolean flag = p_98456_ < (double)p_98458_
                || p_98457_ < (double)p_98459_
                || p_98456_ >= (double)(p_98458_ + this.imageWidth)
                || p_98457_ >= (double)(p_98459_ + this.imageHeight);
        return this.recipeBookComponent.hasClickedOutside(p_98456_, p_98457_, this.leftPos, this.topPos, this.imageWidth, this.imageHeight, p_98460_) && flag;
    }

    @Override
    protected void slotClicked(Slot p_98469_, int p_98470_, int p_98471_, ClickType p_98472_) {
        super.slotClicked(p_98469_, p_98470_, p_98471_, p_98472_);
        this.recipeBookComponent.slotClicked(p_98469_);
    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }
}
