package com.ulto.marvel.plugin;

import com.ulto.marvel.common.MarvelMod;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.mantle.client.render.MantleRenderTypes;
import slimeknights.mantle.registration.ModelFluidAttributes;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.BlockDeferredRegisterExtension;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.shared.TinkerCommons;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TConstructPlugin {
    private static final Map<ResourceLocation, Supplier<ForgeFlowingFluid>> fluids = new HashMap<>();

    private static FluidAttributes.Builder hotBuilder() {
        return ModelFluidAttributes.builder().density(2000).viscosity(10000).temperature(1000).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }

    static {
        if (ModList.get().isLoaded("tconstruct")) {
            final BlockDeferredRegisterExtension BLOCKS = new BlockDeferredRegisterExtension(MarvelMod.MOD_ID);
            final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(MarvelMod.MOD_ID);
            final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(MarvelMod.MOD_ID);
            final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(MarvelMod.MOD_ID);
            IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

            BLOCKS.register(bus);
            ITEMS.register(bus);
            FLUIDS.register(bus);
            MODIFIERS.register(bus);

            class Absorbent extends NoLevelsModifier {
                @Override
                public int onDamageTool(IToolStackView tool, int level, int amount, @Nullable LivingEntity holder) {
                    return 0;
                }

                @Override
                public int getPriority() {
                    return 125;
                }

                @Override
                public int getDurabilityRGB(IToolStackView tool, int level) {
                    return 0xFFFFFF;
                }
            }

            final ItemObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));
            final ItemObject<Item> PALLADIUM_NUGGET = ITEMS.register("palladium_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));
            final ItemObject<Item> URU_NUGGET = ITEMS.register("uru_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));
            final ItemObject<Item> VIBRANIUM_NUGGET = ITEMS.register("vibranium_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));
            final ItemObject<Item> ADAMANTIUM_NUGGET = ITEMS.register("adamantium_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));
            final ItemObject<Item> GOLD_TITANIUM_NUGGET = ITEMS.register("gold_titanium_nugget", new Item.Properties().tab(TinkerCommons.TAB_GENERAL));

            final FluidObject<ForgeFlowingFluid> MOLTEN_TITANIUM = FLUIDS.register("molten_titanium", hotBuilder().temperature(1668), Material.LAVA, 14);
            final FluidObject<ForgeFlowingFluid> MOLTEN_PALLADIUM = FLUIDS.register("molten_palladium", hotBuilder().temperature(1550), Material.LAVA, 14);
            final FluidObject<ForgeFlowingFluid> MOLTEN_URU = FLUIDS.register("molten_uru", hotBuilder().temperature(2200), Material.LAVA, 14);
            final FluidObject<ForgeFlowingFluid> MOLTEN_VIBRANIUM = FLUIDS.register("molten_vibranium", hotBuilder().temperature(1750), Material.LAVA, 14);
            final FluidObject<ForgeFlowingFluid> MOLTEN_ADAMANTIUM = FLUIDS.register("molten_adamantium", hotBuilder().temperature(1500), Material.LAVA, 14);
            final FluidObject<ForgeFlowingFluid> MOLTEN_GOLD_TITANIUM = FLUIDS.register("molten_gold_titanium", hotBuilder().temperature(1700), Material.LAVA, 14);

            fluids.put(MOLTEN_TITANIUM.getId(), MOLTEN_TITANIUM);
            fluids.put(MOLTEN_PALLADIUM.getId(), MOLTEN_PALLADIUM);
            fluids.put(MOLTEN_URU.getId(), MOLTEN_URU);
            fluids.put(MOLTEN_VIBRANIUM.getId(), MOLTEN_VIBRANIUM);
            fluids.put(MOLTEN_ADAMANTIUM.getId(), MOLTEN_ADAMANTIUM);
            fluids.put(MOLTEN_GOLD_TITANIUM.getId(), MOLTEN_GOLD_TITANIUM);

            final StaticModifier<Absorbent> ABSORBENT = MODIFIERS.register("absorbent", Absorbent::new);
        }
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (Map.Entry<ResourceLocation, Supplier<ForgeFlowingFluid>> entry : fluids.entrySet()) {
                FluidObject<ForgeFlowingFluid> fluid = (FluidObject<ForgeFlowingFluid>) entry.getValue();
                ItemBlockRenderTypes.setRenderLayer(fluid.getStill(), MantleRenderTypes.FLUID);
                ItemBlockRenderTypes.setRenderLayer(fluid.getFlowing(), MantleRenderTypes.FLUID);
            }
        });
    }
}
