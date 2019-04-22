package com.game.battleship.exception;

public enum BattleShipExceptionCode {

	POSITION_OUT_OF_RANGE("001", "Position entered out of board range"),
	
	SHIP_LOCATION("002","Ship position out of bount or overlap with other ship"),
	WIDTH_ERROR("003","Input width is out range"),
	HEIGHT_ERROR("004","Input height is out range"),
	BATTLE_SHIP_COUNT_ERROR("005","Battle ship count is out of range"),
	BATTLE_SHIP_TYPE_ERROR("006","Battle ship Type is not in {P ,Q} "),
	BATTLE_SHIP_COORDINATE_X_ERROR("007","Battle ship Location X- axis is out of range"),
	BATTLE_SHIP_COORDINATE_Y_ERROR("008","Battle ship Location Y- axis is out of range");
	

	private final String id;
	private final String message;

	BattleShipExceptionCode(String id, String message) {
		this.id = id;
		this.message = message;
	}

	public String getId() {
		return this.id;
	}

	public String getMessage() {
		return this.message;
	}
}
