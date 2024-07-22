package net.tintankgames.marvel.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.tintankgames.marvel.MarvelSuperheroes;
import org.joml.Vector3f;

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
    public static final Gui.HeartType DEADPOOL_HEART_TYPE = Gui.HeartType.create("DEADPOOL",
            MarvelSuperheroes.id("hud/heart/deadpool_full"),
            MarvelSuperheroes.id("hud/heart/deadpool_full_blinking"),
            MarvelSuperheroes.id("hud/heart/deadpool_half"),
            MarvelSuperheroes.id("hud/heart/deadpool_half_blinking"),
            MarvelSuperheroes.id("hud/heart/deadpool_hardcore_full"),
            MarvelSuperheroes.id("hud/heart/deadpool_hardcore_full_blinking"),
            MarvelSuperheroes.id("hud/heart/deadpool_hardcore_half"),
            MarvelSuperheroes.id("hud/heart/deadpool_hardcore_half_blinking"));
    public static final HumanoidModel.ArmPose VIBRANIUM_SHIELD_POSE = HumanoidModel.ArmPose.create("VIBRANIUM_SHIELD", false, (model, entity, arm) -> {
        ModelPart armModel = arm == HumanoidArm.RIGHT ? model.rightArm : model.leftArm;
        armModel.offsetPos(new Vector3f(arm == HumanoidArm.RIGHT ? -1.0F : 1.0F, 0.0F, 0.0F));
        armModel.setRotation(-1.309F, 2.3562F * (arm == HumanoidArm.LEFT ? -1.0F : 1.0F), 0.3927F * (arm == HumanoidArm.RIGHT ? -1.0F : 1.0F));
    });
}
