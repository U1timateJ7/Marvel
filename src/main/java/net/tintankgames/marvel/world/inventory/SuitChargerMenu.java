package net.tintankgames.marvel.world.inventory;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.tintankgames.marvel.world.item.SuitChargerItem;
import net.tintankgames.marvel.world.level.block.MarvelBlocks;

public class SuitChargerMenu extends AbstractContainerMenu {
    static final ResourceLocation[] TEXTURE_EMPTY_SLOTS = new ResourceLocation[]{
            InventoryMenu.EMPTY_ARMOR_SLOT_BOOTS, InventoryMenu.EMPTY_ARMOR_SLOT_LEGGINGS, InventoryMenu.EMPTY_ARMOR_SLOT_CHESTPLATE, InventoryMenu.EMPTY_ARMOR_SLOT_HELMET
    };
    private static final EquipmentSlot[] SLOT_IDS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
    private final ContainerLevelAccess access;
    private final Player player;

    protected SuitChargerMenu(int p_38852_, Inventory p_39357_) {
        this(p_38852_, p_39357_, new SimpleContainer(4), ContainerLevelAccess.NULL);
    }

    public SuitChargerMenu(int p_38852_, Inventory inventory, Container container, ContainerLevelAccess containerLevelAccess) {
        super(MarvelMenuTypes.SUIT_CHARGER.get(), p_38852_);
        this.access = containerLevelAccess;
        this.player = inventory.player;

        for (int k = 0; k < 4; k++) {
            final EquipmentSlot equipmentslot = SLOT_IDS[k];
            this.addSlot(new Slot(container, 3 - k, 8, 8 + k * 18) {
                @Override
                public int getMaxStackSize() {
                    return 1;
                }

                @Override
                public boolean mayPlace(ItemStack stack) {
                    return LivingEntity.getEquipmentSlotForItem(stack) == equipmentslot && stack.getItem() instanceof SuitChargerItem;
                }

                @Override
                public boolean mayPickup(Player player) {
                    ItemStack itemstack = this.getItem();
                    return itemstack.isEmpty() || super.mayPickup(player);
                }

                @Override
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }

        for (int k = 0; k < 4; k++) {
            final EquipmentSlot equipmentslot = SLOT_IDS[k];
            this.addSlot(new Slot(inventory, 39 - k, 152, 8 + k * 18) {
                @Override
                public void setByPlayer(ItemStack stack, ItemStack oldStack) {
                    onEquipItem(player, equipmentslot, stack, oldStack);
                    super.setByPlayer(stack, oldStack);
                }

                @Override
                public int getMaxStackSize() {
                    return 1;
                }

                @Override
                public boolean mayPlace(ItemStack stack) {
                    return stack.canEquip(equipmentslot, player);
                }

                @Override
                public boolean mayPickup(Player player) {
                    ItemStack itemstack = this.getItem();
                    return (itemstack.isEmpty() || player.isCreative() || !EnchantmentHelper.hasBindingCurse(itemstack)) && super.mayPickup(player);
                }

                @Override
                public Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                    return Pair.of(InventoryMenu.BLOCK_ATLAS, TEXTURE_EMPTY_SLOTS[equipmentslot.getIndex()]);
                }
            });
        }

        for (int k = 0; k < 3; k++) {
            for (int i1 = 0; i1 < 9; i1++) {
                this.addSlot(new Slot(inventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
            }
        }

        for (int l = 0; l < 9; l++) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));
        }
    }

    static void onEquipItem(Player p_270432_, EquipmentSlot p_270254_, ItemStack p_270316_, ItemStack p_270993_) {
        p_270432_.onEquipItem(p_270254_, p_270993_, p_270316_);
    }

    @Override
    public boolean stillValid(Player p_39368_) {
        return stillValid(this.access, p_39368_, MarvelBlocks.SUIT_CHARGER.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotId) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotId);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            EquipmentSlot equipmentslot = Mob.getEquipmentSlotForItem(itemstack);
            if (slotId >= 0 && slotId < 4) {
                if (!this.moveItemStackTo(itemstack1, 8, 44, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 4 && slotId < 8) {
                if (!this.moveItemStackTo(itemstack1, 8, 44, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (itemstack1.getItem() instanceof SuitChargerItem && equipmentslot.getType() == EquipmentSlot.Type.ARMOR && !this.slots.get(3 - equipmentslot.getIndex()).hasItem()) {
                int i = 3 - equipmentslot.getIndex();
                if (!this.moveItemStackTo(itemstack1, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (equipmentslot.getType() == EquipmentSlot.Type.ARMOR && !this.slots.get(7 - equipmentslot.getIndex()).hasItem()) {
                int i = 7 - equipmentslot.getIndex();
                if (!this.moveItemStackTo(itemstack1, i, i + 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 8 && slotId < 35) {
                if (!this.moveItemStackTo(itemstack1, 35, 44, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (slotId >= 35 && slotId < 44) {
                if (!this.moveItemStackTo(itemstack1, 8, 35, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 8, 44, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY, itemstack);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }
}
