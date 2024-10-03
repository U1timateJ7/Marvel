package net.tintankgames.marvel.mixin;

import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.WidgetSprites;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(AbstractButton.class)
public interface AbstractButtonAccessor {
	@Accessor("SPRITES")
	static WidgetSprites marvel$getTextures() {
		throw new AssertionError();
	}
}
