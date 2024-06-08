package net.tintankgames.marvel.world.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;
import net.tintankgames.marvel.core.components.MarvelDataComponents;
import net.tintankgames.marvel.sounds.MarvelSoundEvents;

public class AdamantiumClawsItem extends SuitPowerItem {
    public AdamantiumClawsItem(Properties properties) {
        super(properties.attributes(SwordItem.createAttributes(MarvelTiers.ADAMANTIUM_CLAWS, 3, -2.2F)));
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if (p_41406_ instanceof ServerPlayer player) {
            if (player.getMainHandItem() == p_41404_) {
                if (!p_41404_.has(MarvelDataComponents.CLAWS_OUT)) {
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.WOLVERINE_CLAWS_OUT.get(), SoundSource.PLAYERS);
                    p_41404_.set(MarvelDataComponents.CLAWS_OUT, Unit.INSTANCE);
                }
            } else {
                if (p_41404_.has(MarvelDataComponents.CLAWS_OUT)) {
                    player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), MarvelSoundEvents.WOLVERINE_CLAWS_IN.get(), SoundSource.PLAYERS);
                    p_41404_.remove(MarvelDataComponents.CLAWS_OUT);
                }
            }
        }
    }
}
