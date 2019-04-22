package com.game.battleship.model;

public enum MissileType {

	DEFAULT(1);

	private final int strength;

	MissileType(int power) {
		this.strength = power;
	}

	public int getStrength() {
		return strength;
	}
}
