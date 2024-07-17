package net.tintankgames.marvel.client;

import com.google.common.base.Suppliers;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.joml.Vector3f;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class MarvelClientEnumExtensions {
    public static final Gui.HeartType ADAMANTIUM_HEART_TYPE = Gui.HeartType.create("ADAMANTIUM",
            MarvelSuperheroes.id("hud/heart/adamantium_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_half_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half_blinking"));
    public static final HumanoidModel.ArmPose VIBRANIUM_SHIELD_POSE = HumanoidModel.ArmPose.create("VIBRANIUM_SHIELD", false, (model, entity, arm) -> {
        ModelPart armModel = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
        armModel.offsetPos(new Vector3f(arm == HumanoidArm.RIGHT ? -1.0F : 1.0F, 0.0F, 0.0F));
        armModel.setRotation(-1.309F, 2.3562F * (arm == HumanoidArm.LEFT ? -1.0F : 1.0F), 0.3927F * (arm == HumanoidArm.RIGHT ? -1.0F : 1.0F));
    });
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_SEARCH_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_SEARCH", new ItemStack(Items.COMPASS)));
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_SUITS_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_SUITS", MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE.toStack(), MarvelItems.WASP_HELMET.toStack()));
    public static final Supplier<RecipeBookCategories> SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY = Suppliers.memoize(() -> RecipeBookCategories.create("SUIT_UPGRADING_IRON_MAN_SUITS", MarvelItems.IRON_MAN_MARK_2_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_3_HELMET.toStack()));
}
