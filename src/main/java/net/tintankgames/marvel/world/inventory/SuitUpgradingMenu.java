package net.tintankgames.marvel.world.inventory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.world.item.crafting.MarvelRecipeTypes;
import net.tintankgames.marvel.world.item.crafting.SuitUpgradingRecipe;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

import java.util.List;
import java.util.Optional;

public class SuitUpgradingMenu extends AbstractContainerMenu {
    private final CraftingContainer craftSlots = new TransientCraftingContainer(this, 5, 2);
    private final ResultContainer resultSlot = new ResultContainer();
    private final ContainerLevelAccess access;
    private final Player player;
    private final List<RecipeHolder<SuitUpgradingRecipe>> recipes;
    private final ContainerData data;

    protected SuitUpgradingMenu(int p_38852_, Inventory p_39357_) {
        this(p_38852_, p_39357_, ContainerLevelAccess.NULL, new SimpleContainerData(4));
    }

    public SuitUpgradingMenu(int p_38852_, Inventory inventory, ContainerLevelAccess containerLevelAccess, ContainerData data) {
        super(MarvelMenuTypes.SUIT_UPGRADING.get(), p_38852_);
        this.access = containerLevelAccess;
        this.player = inventory.player;
        checkContainerDataCount(data, 4);
        this.data = data;
        this.recipes = player.level().getRecipeManager().getAllRecipesFor(MarvelRecipeTypes.SUIT_UPGRADING.get());
        this.addSlot(new SuitUpgradingResultSlot(inventory.player, this.craftSlots, this.resultSlot, 0, 143, 33));

        this.addSlot(new Slot(this.craftSlots, 0, 20, 33));

        this.addSlot(new Slot(this.craftSlots, 1, 59, 15));
        this.addSlot(new Slot(this.craftSlots, 2, 77, 15));
        this.addSlot(new Slot(this.craftSlots, 3, 95, 15));
        this.addSlot(new Slot(this.craftSlots, 4, 59, 33));
        this.addSlot(new Slot(this.craftSlots, 5, 77, 33));
        this.addSlot(new Slot(this.craftSlots, 6, 95, 33));
        this.addSlot(new Slot(this.craftSlots, 7, 59, 51));
        this.addSlot(new Slot(this.craftSlots, 8, 77, 51));
        this.addSlot(new Slot(this.craftSlots, 9, 95, 51));

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
        }
    }

    protected static void slotChangedCraftingGrid(AbstractContainerMenu p_150547_, Level p_150548_, Player p_150549_, CraftingContainer p_150550_, ResultContainer p_150551_) {
        if (!p_150548_.isClientSide) {
            ServerPlayer serverplayer = (ServerPlayer)p_150549_;
            ItemStack itemstack = ItemStack.EMPTY;
            Optional<RecipeHolder<SuitUpgradingRecipe>> optional = p_150548_.getServer().getRecipeManager().getRecipeFor(MarvelRecipeTypes.SUIT_UPGRADING.get(), p_150550_, p_150548_);
            if (optional.isPresent()) {
                RecipeHolder<SuitUpgradingRecipe> recipeholder = optional.get();
                SuitUpgradingRecipe upgradingRecipe = recipeholder.value();
                if (p_150551_.setRecipeUsed(p_150548_, serverplayer, recipeholder)) {
                    ItemStack itemstack1 = upgradingRecipe.assemble(p_150550_, p_150548_.registryAccess());
                    if (itemstack1.isItemEnabled(p_150548_.enabledFeatures())) {
                        itemstack = itemstack1;
                    }
                }
            }

            p_150551_.setItem(0, itemstack);
            p_150547_.setRemoteSlot(0, itemstack);
            serverplayer.connection.send(new ClientboundContainerSetSlotPacket(p_150547_.containerId, p_150547_.incrementStateId(), 0, itemstack));
        }
    }

    @Override
    public void slotsChanged(Container p_39366_) {
        this.access.execute((p_39386_, p_39387_) -> slotChangedCraftingGrid(this, p_39386_, this.player, this.craftSlots, this.resultSlot));
    }

    @Override
    public void removed(Player p_39389_) {
        super.removed(p_39389_);
        this.access.execute((p_39371_, p_39372_) -> this.clearContainer(p_39389_, this.craftSlots));
    }

    @Override
    public boolean stillValid(Player p_39368_) {
        return stillValid(this.access, p_39368_, MarvelBlocks.SUIT_TABLE.get());
    }

    @Override
    public ItemStack quickMoveStack(Player p_39391_, int p_39392_) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(p_39392_);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (p_39392_ == 0) {
                this.access.execute((p_39378_, p_39379_) -> itemstack1.getItem().onCraftedBy(itemstack1, p_39378_, p_39391_));
                if (!this.moveItemStackTo(itemstack1, 11, 47, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (p_39391_.level().getRecipeManager().getAllRecipesFor(MarvelRecipeTypes.SUIT_UPGRADING.get()).stream().anyMatch(recipe -> recipe.value().getSuit().test(itemstack1))) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (p_39392_ >= 11 && p_39392_ < 47) {
                if (!this.moveItemStackTo(itemstack1, 2, 10, false)) {
                    if (p_39392_ < 38) {
                        if (!this.moveItemStackTo(itemstack1, 38, 47, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 11, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (!this.moveItemStackTo(itemstack1, 2, 10, false)) {
                    if (p_39392_ < 38) {
                        if (!this.moveItemStackTo(itemstack1, 38, 47, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 11, 38, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 11, 47, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(p_39391_, itemstack1);
            if (p_39392_ == 0) {
                p_39391_.drop(itemstack1, false);
            }
        }

        return itemstack;
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack p_39381_, Slot p_39382_) {
        return p_39382_.container != this.resultSlot && super.canTakeItemForPickAll(p_39381_, p_39382_);
    }

    public BlockPos getBlockPos() {
        return new BlockPos(data.get(0), data.get(1), data.get(2));
    }
}
