package com.ulto.marvel.procedures;

import com.ulto.marvel.network.MarvelModVariables;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class RemoteControlDieProcedure {
    @SubscribeEvent
    public static void onPlayerTick(LivingDeathEvent event) {
        LivingEntity entity = event.getEntityLiving();
        execute(event, entity);
    }

    private static void execute(Event event, LivingEntity entity) {
        if (entity == null)
            return;
        if (MarvelModVariables.getPlayerVariables(entity).controllingMark47) {
            event.setCanceled(true);
            Mark47DisconnectProcedure.execute(entity.level, entity.getX(), entity.getY(), entity.getZ(), entity);
        }
    }
}
