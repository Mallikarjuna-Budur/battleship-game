package com.game.battleship.model;

public enum ShipType {
	DEFENCE_P(1), DEFENCE_Q(2);

	private final int strength;

	ShipType(int power) {
		this.strength = power;
	}

	public int getStrength() {
		return strength;
	}
}
