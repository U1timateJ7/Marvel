package com.ulto.marvel.procedures;

import java.util.Random;

public class HeartShapedHerbAdditionalGenerationConditionProcedure {
	public static boolean execute() {
		if (new Random().nextInt(9 + 1) == 4) {
			return true;
		}
		return false;
	}
}
