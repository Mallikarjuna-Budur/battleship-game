package com.game.battleship.model;

public class Missile {

	private Position position;

	private MissileType missileType;

	public Missile(Position position, MissileType missileType) {
		super();
		this.position = position;
		this.missileType = missileType;
	}

	public Position getPosition() {
		return position;
	}

	public MissileType getMissileType() {
		return missileType;
	}

}
