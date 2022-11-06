
package com.ulto.marvel.world.level.block;

import com.ulto.marvel.world.item.MarvelModItems;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.CropBlock;

public class HeartShapedHerbCropBlock extends CropBlock {
	public HeartShapedHerbCropBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return MarvelModItems.HEART_SHAPED_HERB_SEEDS.get();
	}
}
