package net.tintankgames.marvel.world.damagesources;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.tintankgames.marvel.MarvelSuperheroes;

public class MarvelDamageTypes {
    public static final ResourceKey<DamageType> KINETIC_BLAST = create("kinetic_blast");
    public static final ResourceKey<DamageType> VIBRANIUM_SHIELD = create("vibranium_shield");
    public static final ResourceKey<DamageType> VIBRANIUM_SHIELD_DISPENSER = create("vibranium_shield_dispenser");
    public static final ResourceKey<DamageType> OPTIC_BLAST = create("optic_blast");
    public static final ResourceKey<DamageType> OPTIC_BLAST_GOOFY = create("optic_blast_goofy");
    public static final ResourceKey<DamageType> WEB_SHOT = create("web_shot");
    public static final ResourceKey<DamageType> GIANT_MAN = create("giant_man");
    public static final ResourceKey<DamageType> MJOLNIR = create("mjolnir");
    public static final ResourceKey<DamageType> MJOLNIR_DISPENSER = create("mjolnir_dispenser");
    public static final ResourceKey<DamageType> STORMBREAKER = create("stormbreaker");
    public static final ResourceKey<DamageType> STORMBREAKER_DISPENSER = create("stormbreaker_dispenser");

    public static ResourceKey<DamageType> create(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, MarvelSuperheroes.id(id));
    }

    public static void bootstrap(BootstrapContext<DamageType> bootstrapContext) {
        bootstrapContext.register(KINETIC_BLAST, new DamageType("kinetic_blast", 0.1f));
        bootstrapContext.register(VIBRANIUM_SHIELD, new DamageType("vibranium_shield", 0.1f));
        bootstrapContext.register(VIBRANIUM_SHIELD_DISPENSER, new DamageType("vibranium_shield_dispenser", 0.1f));
        bootstrapContext.register(OPTIC_BLAST, new DamageType("optic_blast", 0.1f));
        bootstrapContext.register(OPTIC_BLAST_GOOFY, new DamageType("optic_blast_goofy", 0.1f));
        bootstrapContext.register(WEB_SHOT, new DamageType("web_shot", 0.1f));
        bootstrapContext.register(GIANT_MAN, new DamageType("giant_man", 0.1f));
        bootstrapContext.register(MJOLNIR, new DamageType("mjolnir", 0.1f));
        bootstrapContext.register(MJOLNIR_DISPENSER, new DamageType("mjolnir_dispenser", 0.1f));
        bootstrapContext.register(STORMBREAKER, new DamageType("stormbreaker", 0.1f));
        bootstrapContext.register(STORMBREAKER_DISPENSER, new DamageType("stormbreaker_dispenser", 0.1f));
    }

    public static class Tags {
        public static final TagKey<DamageType> OPTIC_BLAST = create("optic_blast");

        private static TagKey<DamageType> create(String id) {
            return TagKey.create(Registries.DAMAGE_TYPE, MarvelSuperheroes.id(id));
        }
    }
}
