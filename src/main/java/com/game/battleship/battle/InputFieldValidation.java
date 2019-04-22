package com.game.battleship.battle;

import com.game.battleship.exception.BattleShipException;
import com.game.battleship.exception.BattleShipExceptionCode;

public class InputFieldValidation {

	private InputFieldValidation() {
		// hide the constructors
	}
	public static boolean validateBoardWidth(Integer width) throws BattleShipException {
		if ((width.compareTo(0) < 1) || (width.compareTo(9) > 0)) {
			throw new BattleShipException(BattleShipExceptionCode.WIDTH_ERROR);
		}
		return true;
	}

	public static boolean validateBoardHeight(Character height) throws BattleShipException {

		if (!(height >= 'A' && height <= 'Z')) {
			throw new BattleShipException(BattleShipExceptionCode.WIDTH_ERROR);
		}
		return true;
	}

	public static boolean validateNumberOfBattleShip(Integer count, Integer length, Integer height)
			throws BattleShipException {

		if (count.compareTo(0) < 0 || count.compareTo(length * height) > 0) {
			throw new BattleShipException(BattleShipExceptionCode.BATTLE_SHIP_COUNT_ERROR);
		}
		return true;
	}
	
	public static boolean validateBattleShipType(String type)
			throws BattleShipException {

		if (!type.equals("P") && !type.equals("Q")) {
			throw new BattleShipException(BattleShipExceptionCode.BATTLE_SHIP_TYPE_ERROR);
		}
		return true;
	}
	
	public static boolean validateBattleShipWidth(Integer width,Integer boardWidth) throws BattleShipException {
		if ((width.compareTo(0) < 1) || (width.compareTo(boardWidth) > 0)) {
			throw new BattleShipException(BattleShipExceptionCode.WIDTH_ERROR);
		}
		return true;
	}

	public static boolean validateBattleShipHeight(Integer height,Integer boardHeight) throws BattleShipException {

		if ((height.compareTo(0) < 1) || (height.compareTo(boardHeight) > 0)) {
			throw new BattleShipException(BattleShipExceptionCode.HEIGHT_ERROR);
		}
		return true;
	}
	
	public static boolean validateBattleCoordinateX(Integer x,Integer boardWidth) throws BattleShipException {
		if ((x.compareTo(0) < 1) || (x.compareTo(boardWidth) > 0)) {
			throw new BattleShipException(BattleShipExceptionCode.BATTLE_SHIP_COORDINATE_X_ERROR);
		}
		return true;
	}

	public static boolean validateBattleCoordinateY(Integer y,Integer boardHeight) throws BattleShipException {

		if ((y.compareTo(0) < 1) || (y.compareTo(boardHeight) > 0)) {
			throw new BattleShipException(BattleShipExceptionCode.BATTLE_SHIP_COORDINATE_Y_ERROR);
		}
		return true;
	}
	
}
