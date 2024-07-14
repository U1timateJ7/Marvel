package net.tintankgames.marvel.client;

import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.fml.common.asm.enumextension.EnumProxy;
import net.neoforged.neoforge.client.IArmPoseTransformer;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.world.item.MarvelItems;
import org.joml.Vector3f;

import java.util.List;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class MarvelClientEnumExtensions {
    public static final EnumProxy<Gui.HeartType> ADAMANTIUM_HEART_TYPE = new EnumProxy<>(Gui.HeartType.class,
            MarvelSuperheroes.id("hud/heart/adamantium_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_half_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_full_blinking"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half"),
            MarvelSuperheroes.id("hud/heart/adamantium_hardcore_half_blinking"));
    public static final EnumProxy<HumanoidModel.ArmPose> VIBRANIUM_SHIELD_POSE = new EnumProxy<>(HumanoidModel.ArmPose.class, false, (IArmPoseTransformer) (model, entity, arm) -> {
        ModelPart armModel = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
        armModel.offsetPos(new Vector3f(arm == HumanoidArm.RIGHT ? -1.0F : 1.0F, 0.0F, 0.0F));
        armModel.setRotation(-1.309F, 2.3562F * (arm == HumanoidArm.LEFT ? -1.0F : 1.0F), 0.3927F * (arm == HumanoidArm.RIGHT ? -1.0F : 1.0F));
    });
    public static final EnumProxy<RecipeBookCategories> SUIT_UPGRADING_SEARCH_CATEGORY = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(new ItemStack(Items.COMPASS)));
    public static final EnumProxy<RecipeBookCategories> SUIT_UPGRADING_SUITS_CATEGORY = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(MarvelItems.ANT_MAN_UPGRADED_CHESTPLATE.toStack(), MarvelItems.WASP_HELMET.toStack()));
    public static final EnumProxy<RecipeBookCategories> SUIT_UPGRADING_IRON_MAN_SUITS_CATEGORY = new EnumProxy<>(RecipeBookCategories.class, (Supplier<List<ItemStack>>) () -> List.of(MarvelItems.IRON_MAN_MARK_6_CHESTPLATE.toStack(), MarvelItems.IRON_MAN_MARK_5_HELMET.toStack()));
}
