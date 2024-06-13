package net.tintankgames.marvel.world.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.tintankgames.marvel.client.model.SuitModel;
import net.tintankgames.marvel.core.components.MarvelDataComponents;

import java.util.List;
import java.util.function.Consumer;

public abstract class SuitItem extends ArmorItem {
    private final TagKey<Item> suitPieces;
    private final boolean needsHelmet;

    public SuitItem(Holder<ArmorMaterial> material, Type type, TagKey<Item> suitPieces, Properties properties) {
        this(material, type, true, suitPieces, List.of(), properties);
    }

    public SuitItem(Holder<ArmorMaterial> material, Type type, boolean needsHelmet, TagKey<Item> suitPieces, Properties properties) {
        this(material, type, needsHelmet, suitPieces, List.of(), properties);
    }

    public SuitItem(Holder<ArmorMaterial> material, Type type, TagKey<Item> suitPieces, List<MobEffectInstance> effects, Properties properties) {
        this(material, type, true, suitPieces, effects, properties);
    }

    public SuitItem(Holder<ArmorMaterial> material, Type type, boolean needsHelmet, TagKey<Item> suitPieces, List<MobEffectInstance> effects, Properties properties) {
        super(material, type, properties.component(MarvelDataComponents.SUIT_EFFECTS, effects));
        this.suitPieces = suitPieces;
        this.needsHelmet = needsHelmet;
    }

    public static MobEffectInstance effect(Holder<MobEffect> effect, int amplifier) {
        return new MobEffectInstance(effect, 1, amplifier, false, false);
    }

    public static MobEffectInstance effect(Holder<MobEffect> effect, int amplifier, int duration) {
        return new MobEffectInstance(effect, duration, amplifier, false, false);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean p_41408_) {
        if (entity instanceof LivingEntity living) {
            if ((living.getItemBySlot(EquipmentSlot.HEAD).is(suitPieces) || !needsHelmet) && living.getItemBySlot(EquipmentSlot.CHEST).is(suitPieces) && living.getItemBySlot(EquipmentSlot.LEGS).is(suitPieces) && living.getItemBySlot(EquipmentSlot.FEET).is(suitPieces)) {
                if (living.getItemBySlot(EquipmentSlot.CHEST) == stack) {
                    for (MobEffectInstance instance : stack.getOrDefault(MarvelDataComponents.SUIT_EFFECTS, List.<MobEffectInstance>of())) {
                        living.addEffect(new MobEffectInstance(instance));
                    }
                }
                if (living instanceof Player player) {
                    for (Item powerItem : stack.getOrDefault(MarvelDataComponents.POWER_ITEMS, List.<Item>of())) {
                        if (!player.getInventory().contains(itemStack -> itemStack.is(powerItem)) && !player.getSlot(499).get().is(powerItem)) {
                            player.addItem(new ItemStack(powerItem));
                        }
                        if (player.getInventory().countItem(powerItem) > 1) {
                            player.getInventory().removeItem(player.getInventory().getItem(findSlotMatchingItem(player.getInventory().items, powerItem)));
                        }
                    }
                }
            } else {
                if (living instanceof Player player) {
                    for (Item powerItem : stack.getOrDefault(MarvelDataComponents.POWER_ITEMS, List.<Item>of())) {
                        if (player.getInventory().contains(itemStack -> itemStack.is(powerItem))) {
                            player.getInventory().removeItem(player.getInventory().getItem(findSlotMatchingItem(player.getInventory().items, powerItem)));
                        }
                    }
                }
            }
        }
    }

    public static int findSlotMatchingItem(List<ItemStack> items, Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (!items.get(i).isEmpty() && item == items.get(i).getItem()) {
                return i;
            }
        }

        return -1;
    }

    protected abstract ModelLayerLocation modelFactory(Type type, ItemStack itemStack);

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                SuitModel<?> suitModel = new SuitModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(modelFactory(getType(), itemStack)));
                copyPropertiesTo(original, suitModel);
                return suitModel;
            }

            private void copyPropertiesTo(HumanoidModel<?> original, SuitModel<?> model) {
                model.attackTime = original.attackTime;
                model.riding = original.riding;
                model.young = original.young;
                model.leftArmPose = original.leftArmPose;
                model.rightArmPose = original.rightArmPose;
                model.crouching = original.crouching;
                model.head.copyFrom(original.head);
                model.hat.copyFrom(original.hat);
                model.body.copyFrom(original.body);
                model.rightArm.copyFrom(original.rightArm);
                model.leftArm.copyFrom(original.leftArm);
                model.rightLeg.copyFrom(original.rightLeg);
                model.leftLeg.copyFrom(original.leftLeg);
            }
        });
    }

    @Override
    public ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean innerModel) {
        return BuiltInRegistries.ITEM.getKey(this).withPath(id -> "textures/models/suit/" + id.replace("_" + getType().getName(), "") + (slot == EquipmentSlot.HEAD && stack.getOrDefault(MarvelDataComponents.HELMET_OPEN, false) ? "_open" : "") + ".png");
    }
}
