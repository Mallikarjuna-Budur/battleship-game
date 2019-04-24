package com.game.battleship.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.game.battleship.battle.observer.Subject;

/**
 * This ship class holds ship details and {@code shipPositionsStrength} is used to hold
 * position and it's strength with fast lookup of O(1).
 * 
 * {@code positionsDestroyed} is used hold shipCells when it's strength become zero or cell destroyed
 * 
 * @author Bala
 *
 */
public class Ship extends Subject{

	private Position startingPosition;
	private Integer shipLength;
	private Integer shipHeigh;
	private Integer totalShipCells;
	private Map<Position, Integer> shipPositionsStrength;
	private Set<Position> positionsDestroyed;
	private ShipType shipType;
	private ShipStatus shipStatus;

	public Ship(Integer shipLength, Integer shipHeigh, ShipType shipType) {
		super();
		this.shipLength = shipLength;
		this.shipHeigh = shipHeigh;
		this.shipType = shipType;
		this.shipStatus = ShipStatus.LIVE;
		this.totalShipCells = this.shipLength * this.shipHeigh;
		this.shipPositionsStrength = new HashMap<>(this.totalShipCells);
		this.positionsDestroyed = new HashSet<>(this.totalShipCells);
	}

	/**
	 * This method used to validate missile position with available position and
	 * check this position not already destroyed.
	 * 
	 * If the {@code missile} result in Hit then ship notify it's observers about the status change
	 * 
	 * @param missile
	 * @return
	 */
	public boolean validatePositionAndAttacked(Missile missile) {

		Position position = missile.getPosition();
		if (position != null && isPositionOccupied(position)) {

			if (this.positionsDestroyed.contains(position)) {
				return false;
			} else {
				Integer strength = this.shipPositionsStrength.get(position);
				strength = strength - missile.getMissileType().getStrength();

				this.shipPositionsStrength.put(position, strength);
				if (strength.compareTo(0) <= 0) {
					this.positionsDestroyed.add(position);
				}

				if (this.positionsDestroyed.size() == totalShipCells) {
					this.shipStatus = ShipStatus.DESTROYED;
				} else {
					this.shipStatus = ShipStatus.HIT;
				}
				this.notifyObservers();
				return true;
			}

		}

		return false;

	}

	public boolean isPositionOccupied(Position position) {
		return this.shipPositionsStrength.containsKey(position);
	}

	/**
	 * This method is used to set the position occupied by ship and with strength
	 * 
	 * @param shipPositions
	 */
	public void setPositionOccupiedByShip(Set<Position> shipPositions) {
		for (Position position : shipPositions) {
			shipPositionsStrength.put(position, shipType.getStrength());
		}

	}

	public ShipStatus getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(ShipStatus shipStatus) {
		this.shipStatus = shipStatus;
	}

	public Position getStartingPosition() {
		return startingPosition;
	}

	public void setStartingPosition(Position startingPosition) {
		this.startingPosition = startingPosition;
	}

}
