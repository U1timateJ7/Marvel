package net.tintankgames.marvel.world.inventory;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitVariantRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.List;
import java.util.stream.Collectors;

public class SuitVariantMenu extends AbstractContainerMenu {
    private final ContainerLevelAccess access;
    private final DataSlot selectedRecipeIndex = DataSlot.standalone();
    private final Level level;
    private List<RecipeHolder<SuitVariantRecipe>> recipes = Lists.newArrayList();
    private ItemStack input = ItemStack.EMPTY;
    long lastSoundTime;
    final Slot inputSlot;
    final Slot resultSlot;
    Runnable slotUpdateListener = () -> {
    };
    public final Container container = new SimpleContainer(1) {
        @Override
        public void setChanged() {
            super.setChanged();
            SuitVariantMenu.this.slotsChanged(this);
            SuitVariantMenu.this.slotUpdateListener.run();
        }
    };
    final ResultContainer resultContainer = new ResultContainer();
    private final ContainerData data;

    public SuitVariantMenu(int p_40294_, Inventory p_40295_) {
        this(p_40294_, p_40295_, ContainerLevelAccess.NULL, new SimpleContainerData(4));
    }

    public SuitVariantMenu(int p_40297_, Inventory p_40298_, final ContainerLevelAccess p_40299_, ContainerData data) {
        super(MarvelMenuTypes.SUIT_VARIANT.get(), p_40297_);
        this.access = p_40299_;
        this.level = p_40298_.player.level();
        checkContainerDataCount(data, 4);
        this.data = data;
        this.inputSlot = this.addSlot(new Slot(this.container, 0, 20, 33));
        this.resultSlot = this.addSlot(new Slot(this.resultContainer, 1, 143, 33) {
            @Override
            public boolean mayPlace(ItemStack p_40362_) {
                return false;
            }

            @Override
            public void onTake(Player p_150672_, ItemStack p_150673_) {
                p_150673_.onCraftedBy(p_150672_.level(), p_150672_, p_150673_.getCount());
                SuitVariantMenu.this.resultContainer.awardUsedRecipes(p_150672_, this.getRelevantItems());
                ItemStack itemstack = SuitVariantMenu.this.inputSlot.remove(1);
                if (!itemstack.isEmpty()) {
                    SuitVariantMenu.this.setupResultSlot();
                }

                p_40299_.execute((p_40364_, p_40365_) -> {
                    long l = p_40364_.getGameTime();
                    if (SuitVariantMenu.this.lastSoundTime != l) {
                        p_40364_.playSound(null, p_40365_, SoundEvents.UI_STONECUTTER_TAKE_RESULT, SoundSource.BLOCKS, 1.0F, 1.0F);
                        SuitVariantMenu.this.lastSoundTime = l;
                    }
                });
                super.onTake(p_150672_, p_150673_);
            }

            private List<ItemStack> getRelevantItems() {
                return List.of(SuitVariantMenu.this.inputSlot.getItem());
            }
        });

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(p_40298_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(p_40298_, k, 8 + k * 18, 142));
        }

        this.addDataSlot(this.selectedRecipeIndex);
    }

    public int getSelectedRecipeIndex() {
        return this.selectedRecipeIndex.get();
    }

    public List<RecipeHolder<SuitVariantRecipe>> getRecipes() {
        return this.recipes;
    }

    public int getNumRecipes() {
        return this.recipes.size();
    }

    public boolean hasInputItem() {
        return this.inputSlot.hasItem() && !this.recipes.isEmpty();
    }

    @Override
    public boolean stillValid(Player p_40307_) {
        return stillValid(this.access, p_40307_, MarvelBlocks.SUIT_TABLE.get());
    }

    @Override
    public boolean clickMenuButton(Player p_40309_, int p_40310_) {
        if (this.isValidRecipeIndex(p_40310_)) {
            this.selectedRecipeIndex.set(p_40310_);
            this.setupResultSlot();
        }

        return true;
    }

    private boolean isValidRecipeIndex(int p_40335_) {
        return p_40335_ >= 0 && p_40335_ < this.recipes.size();
    }

    @Override
    public void slotsChanged(Container p_40302_) {
        ItemStack itemstack = this.inputSlot.getItem();
        if (!itemstack.is(this.input.getItem())) {
            this.input = itemstack.copy();
            this.setupRecipeList(p_40302_, itemstack);
        }
    }

    private static SingleRecipeInput createRecipeInput(Container p_346312_) {
        return new SingleRecipeInput(p_346312_.getItem(0));
    }

    private void setupRecipeList(Container p_40304_, ItemStack p_40305_) {
        this.recipes.clear();
        this.selectedRecipeIndex.set(-1);
        this.resultSlot.set(ItemStack.EMPTY);
        if (!p_40305_.isEmpty()) {
            this.recipes = this.level.getRecipeManager().getRecipesFor(MarvelRecipeTypes.SUIT_VARIANT.get(), createRecipeInput(p_40304_), this.level).stream().filter(recipe -> !ItemStack.isSameItem(recipe.value().getResultItem(level.registryAccess()), input) || !recipe.value().getResultItem(level.registryAccess()).getComponentsPatch().isEmpty()).collect(Collectors.toList());
        }
    }

    void setupResultSlot() {
        if (!this.recipes.isEmpty() && this.isValidRecipeIndex(this.selectedRecipeIndex.get())) {
            RecipeHolder<SuitVariantRecipe> recipeholder = this.recipes.get(this.selectedRecipeIndex.get());
            ItemStack itemstack = recipeholder.value().assemble(createRecipeInput(this.container), this.level.registryAccess());
            if (itemstack.isItemEnabled(this.level.enabledFeatures())) {
                this.resultContainer.setRecipeUsed(recipeholder);
                this.resultSlot.set(itemstack);
            } else {
                this.resultSlot.set(ItemStack.EMPTY);
            }
        } else {
            this.resultSlot.set(ItemStack.EMPTY);
        }

        this.broadcastChanges();
    }

    public void registerUpdateListener(Runnable p_40324_) {
        this.slotUpdateListener = p_40324_;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack p_40321_, Slot p_40322_) {
        return p_40322_.container != this.resultContainer && super.canTakeItemForPickAll(p_40321_, p_40322_);
    }

    @Override
    public ItemStack quickMoveStack(Player p_40328_, int p_40329_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_40329_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            Item item = itemstack1.getItem();
            itemstack = itemstack1.copy();
            if (p_40329_ == 1) {
                item.onCraftedBy(itemstack1, p_40328_.level(), p_40328_);
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_40329_ == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.level.getRecipeManager().getRecipeFor(MarvelRecipeTypes.SUIT_VARIANT.get(), new SingleRecipeInput(itemstack1), this.level).isPresent()) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (p_40329_ >= 2 && p_40329_ < 29) {
                if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (p_40329_ >= 29 && p_40329_ < 38 && !this.moveItemStackTo(itemstack1, 2, 29, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            }

            slot.setChanged();
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_40328_, itemstack1);
            this.broadcastChanges();
        }

        return itemstack;
    }

    @Override
    public void removed(Player p_40326_) {
        super.removed(p_40326_);
        this.resultContainer.removeItemNoUpdate(1);
        this.access.execute((p_40313_, p_40314_) -> this.clearContainer(p_40326_, this.container));
    }

    public BlockPos getBlockPos() {
        return new BlockPos(data.get(0), data.get(1), data.get(2));
    }
}
