package com.game.battleship.constants;

public enum BattleMessage {

	NO_MISSILE("has no more missiles left"), MISSILE_FIRE("fires a missile with target"), HIT_MSG(
			"which hit"), MISS_MSG("which missed"), WON("won the battle"), DRAW("peace");

	private final String message;

	BattleMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
