package com.game.battleship.battle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.game.battleship.battle.observer.Observer;
import com.game.battleship.battle.observer.Subject;
import com.game.battleship.exception.BattleShipException;
import com.game.battleship.exception.BattleShipExceptionCode;
import com.game.battleship.model.Missile;
import com.game.battleship.model.Position;
import com.game.battleship.model.Ship;
import com.game.battleship.model.ShipStatus;
import com.game.battleship.model.ShipType;

public class Board extends Subject implements Observer {

	private Set<Position> positions;

	private Set<Position> positionsOccupied;

	private List<Ship> ships;

	private Integer shipsAvailable;

	Board(Integer length, Integer height) {
		// Initialize positions
		this.positions = new HashSet<>();
		this.positionsOccupied = new HashSet<>();
		this.ships = new ArrayList<>();

		this.initializeBoard(length, height);
	}

	private void initializeBoard(Integer length, Integer height) {
		for (int i = 1; i <= length; i++) {
			for (int j = 1; j <= height; j++) {
				Position position = new Position(i, j);
				this.positions.add(position);
			}
		}
	}

	public boolean createAndlocalizeShip(Integer shipLength, Integer shipHeight, ShipType shipType,
			Position startingPosition) throws BattleShipException {

		Set<Position> shipPositions = getShipLocalizePosition(shipLength, shipHeight, startingPosition);
		boolean shipCreationStatus = false;
		boolean isValidPositions = true;
		for (Position position : shipPositions) {
			if (!this.positions.contains(position)) {
				isValidPositions = false;
				break;
			}
		}
		if (isValidPositions) {
			Ship ship = new Ship(shipLength, shipHeight, shipType);
			if (positions.contains(startingPosition) && validateShipPosition(shipPositions)) {
				ship.setStartingPosition(startingPosition);
				ship.setPositionOccupiedByShip(shipPositions);
				this.positionsOccupied.addAll(shipPositions);
				this.ships.add(ship);
				this.shipsAvailable = this.ships.size();
				shipCreationStatus = true;
				ship.registerObserver(this);
			} else {
				throw new BattleShipException(BattleShipExceptionCode.SHIP_LOCATION);
			}
		} else {
			throw new BattleShipException(BattleShipExceptionCode.POSITION_OUT_OF_RANGE);
		}
		return shipCreationStatus;

	}

	/**
	 * Get position that will be occupied by ship based on start position
	 * @param shipLength
	 * @param shipHeigh
	 * @param startingPosition
	 * @return
	 */
	private Set<Position> getShipLocalizePosition(Integer shipLength, Integer shipHeigh, Position startingPosition) {
		Set<Position> shipPosition = new HashSet<>();
		for (int x = startingPosition.getCoordinateX(); x < (startingPosition.getCoordinateX() + shipLength); x++) {
			for (int y = startingPosition.getCoordinateY(); y < (startingPosition.getCoordinateY() + shipHeigh); y++) {
				Position position = new Position(x, y);
				shipPosition.add(position);
			}
		}

		return shipPosition;
	}

	/**
	 * Check new ship position is overlapped with other position in board which are
	 * already occupied
	 * 
	 * @param newShipPositions
	 * @return
	 */
	private boolean validateShipPosition(Set<Position> newShipPositions) {

		boolean isValidShipPosition = true;
		for (Position position : newShipPositions) {
			if (this.positionsOccupied.contains(position)) {
				isValidShipPosition = false;
				break;
			}
		}
		return isValidShipPosition;
	}

	/**
	 * Check and execute missile in ships tagged to this board
	 * @param missile
	 * @return
	 */
	public boolean validateAndExecuteAttack(Missile missile) {

		boolean isAttackSuccesfull = false;
		for (Ship ship : this.ships) {
			if (ship.validatePositionAndAttacked(missile)) {
				isAttackSuccesfull = true;
				break;
			}
		}

		return isAttackSuccesfull;

	}

	/**
	 * Validate shoot position is within board boundry
	 * @param missile
	 * @return
	 */
	public boolean validateShootPosition(Missile missile) {
		return positions.contains(missile.getPosition());

	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public List<Ship> getShips() {
		return ships;
	}

	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}

	public Integer getShipsAvailable() {
		return shipsAvailable;
	}

	public void setShipsAvailable(Integer shipsAvailable) {
		this.shipsAvailable = shipsAvailable;
	}

	/**
	 * Ship notify board on change of ship status and board notify this status
	 * change to it's subscriber 
	 *
	 */
	public void update(Subject subject) {
		// Validate observer ship
		if (subject instanceof Ship) {
			// Validate the state of the ship
			if (((Ship) subject).getShipStatus() == ShipStatus.DESTROYED) {
				// Reduce the number of the ships available.
				this.shipsAvailable--;
			}
			// Notify observers
			this.notifyObservers();
		}
	}

}
