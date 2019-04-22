package com.game.battleship.utils;

import com.game.battleship.model.Position;

public class FieldConvertionUtils {
	
	private FieldConvertionUtils() {
		// hide constructors 
	}

	public static Integer getCharacterToInteger(Character chr) {
		return chr - 'A' + 1;
	}
	
	public static String getIntegerToCharacter(int integer) {
		return String.valueOf((char)('A' + (integer - 1)));
	}
	
	public static Position getPosition(String str) {

		int y = getCharacterToInteger(str.charAt(0));

		int x = Integer.parseInt((String.valueOf(str.charAt(1))));

		return new Position(x, y);
	}
	
}
