package com.ulto.marvel.procedures;

import java.util.Random;
import java.util.Map;

import com.ulto.marvel.MarvelModElements;

@MarvelModElements.ModElement.Tag
public class HeartShapedHerbAdditionalGenerationConditionProcedure extends MarvelModElements.ModElement {
	public HeartShapedHerbAdditionalGenerationConditionProcedure(MarvelModElements instance) {
		super(instance, 98);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if ((((new Random()).nextInt((int) 9 + 1)) == 4)) {
			return (true);
		}
		return (false);
	}
}
