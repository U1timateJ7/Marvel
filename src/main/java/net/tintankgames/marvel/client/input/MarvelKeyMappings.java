package net.tintankgames.marvel.client.input;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.tintankgames.marvel.MarvelSuperheroes;
import net.tintankgames.marvel.network.PrimarySuitAbilityMessage;
import net.tintankgames.marvel.network.SecondarySuitAbilityMessage;
import net.tintankgames.marvel.network.ToggleHelmetMessage;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = MarvelSuperheroes.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class MarvelKeyMappings {
    private static final KeyMapping PRIMARY_SUIT_ABILITY = new KeyMapping(MarvelSuperheroes.id("primary_suit_ability").toLanguageKey("key"), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KeyMapping.CATEGORY_GAMEPLAY) {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);

            if (isDownOld != isDown && isDown) {
                Minecraft.getInstance().player.connection.send(PrimarySuitAbilityMessage.INSTANCE);
            }

            isDownOld = isDown;
        }
    };
    private static final KeyMapping SECONDARY_SUIT_ABILITY = new KeyMapping(MarvelSuperheroes.id("secondary_suit_ability").toLanguageKey("key"), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_C, KeyMapping.CATEGORY_GAMEPLAY) {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);

            if (isDownOld != isDown && isDown) {
                Minecraft.getInstance().player.connection.send(SecondarySuitAbilityMessage.INSTANCE);
            }

            isDownOld = isDown;
        }
    };
    private static final KeyMapping TOGGLE_HELMET = new KeyMapping(MarvelSuperheroes.id("toggle_helmet").toLanguageKey("key"), InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KeyMapping.CATEGORY_GAMEPLAY) {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);

            if (isDownOld != isDown && isDown) {
                Minecraft.getInstance().player.connection.send(ToggleHelmetMessage.INSTANCE);
            }

            isDownOld = isDown;
        }
    };

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(PRIMARY_SUIT_ABILITY);
        event.register(SECONDARY_SUIT_ABILITY);
        event.register(TOGGLE_HELMET);
    }

    @EventBusSubscriber(Dist.CLIENT)
    @OnlyIn(Dist.CLIENT)
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            if (Minecraft.getInstance().screen == null) {
                PRIMARY_SUIT_ABILITY.consumeClick();
                SECONDARY_SUIT_ABILITY.consumeClick();
                TOGGLE_HELMET.consumeClick();
            }
        }
    }
}
