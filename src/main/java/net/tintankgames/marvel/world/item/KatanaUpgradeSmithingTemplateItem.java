package net.tintankgames.marvel.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;
import net.tintankgames.marvel.MarvelSuperheroes;

import java.util.List;

public class KatanaUpgradeSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component KATANA_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", MarvelSuperheroes.id("katana"))).withStyle(TITLE_FORMAT);
    private static final Component KATANA_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", MarvelSuperheroes.id("smithing_template.katana_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component KATANA_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", MarvelSuperheroes.id("smithing_template.katana_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component KATANA_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", MarvelSuperheroes.id("smithing_template.katana_upgrade.base_slot_description")));
    private static final Component KATANA_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", MarvelSuperheroes.id("smithing_template.katana_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_SWORD = ResourceLocation.withDefaultNamespace("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.withDefaultNamespace("item/empty_slot_ingot");

    public KatanaUpgradeSmithingTemplateItem() {
        super(KATANA_UPGRADE_APPLIES_TO, KATANA_UPGRADE_INGREDIENTS, KATANA_UPGRADE, KATANA_UPGRADE_BASE_SLOT_DESCRIPTION, KATANA_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createPlatingUpgradeIconList(), createPlatingUpgradeMaterialList());
    }

    private static List<ResourceLocation> createPlatingUpgradeIconList() {
        return List.of(EMPTY_SLOT_SWORD);
    }

    private static List<ResourceLocation> createPlatingUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
