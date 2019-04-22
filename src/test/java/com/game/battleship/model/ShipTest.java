package com.game.battleship.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ShipTest {

	@Test
	public void isPositionOccupiedTest() {
		Ship ship = new Ship(1, 1, ShipType.DEFENCE_Q);
		Position postion = new Position(1, 1);
		Set<Position> shipPositions = new HashSet<>();
		shipPositions.add(postion);
		ship.setPositionOccupiedByShip(shipPositions);

		assertTrue(ship.isPositionOccupied(postion));

	}

	@Test
	public void isPositionOccupiedNegativeTest() {
		Ship ship = new Ship(1, 1, ShipType.DEFENCE_Q);
		Position postion = new Position(1, 1);
		Set<Position> shipPositions = new HashSet<>();
		shipPositions.add(postion);
		ship.setPositionOccupiedByShip(shipPositions);

		Position positionNotAvailable = new Position(2, 2);
		assertFalse(ship.isPositionOccupied(positionNotAvailable));

	}
	
	@Test
	public void validatePositionAndAttackedMissileMissTest() {
		Ship ship = new Ship(1, 1, ShipType.DEFENCE_Q);
		Position position = new Position(1, 1);
		Set<Position> shipPositions = new HashSet<>();
		shipPositions.add(position);
		ship.setPositionOccupiedByShip(shipPositions);
		
		Position positionOut = new Position(1, 2);
		Missile missile = new Missile(positionOut, MissileType.DEFAULT);
		
		assertFalse(ship.validatePositionAndAttacked(missile));
	}
	
	@Test
	public void validatePositionAndAttackedMissileHitTest() {
		Ship ship = new Ship(1, 1, ShipType.DEFENCE_Q);
		Position position = new Position(1, 1);
		Set<Position> shipPositions = new HashSet<>();
		shipPositions.add(position);
		ship.setPositionOccupiedByShip(shipPositions);
		
		Position positionOut = new Position(1, 1);
		Missile missile = new Missile(positionOut, MissileType.DEFAULT);
		
		assertTrue(ship.validatePositionAndAttacked(missile));
	}
	
	@Test
	public void validatePositionAndAttackedShipStatusTest() {
		Ship ship = new Ship(1, 1, ShipType.DEFENCE_Q);
		Position position = new Position(1, 1);
		Set<Position> shipPositions = new HashSet<>();
		shipPositions.add(position);
		ship.setPositionOccupiedByShip(shipPositions);
		
		Position positionOut = new Position(1, 1);
		Missile missile1 = new Missile(positionOut, MissileType.DEFAULT);
		
		assertTrue(ship.validatePositionAndAttacked(missile1));
		
		Missile missile2 = new Missile(positionOut, MissileType.DEFAULT);
		
		assertTrue(ship.validatePositionAndAttacked(missile2));
		
		
		assertEquals(ShipStatus.DESTROYED, ship.getShipStatus());
	}
}
