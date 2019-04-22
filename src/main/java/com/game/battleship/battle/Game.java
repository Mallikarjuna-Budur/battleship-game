package com.game.battleship.battle;

import static com.game.battleship.utils.FieldConvertionUtils.getIntegerToCharacter;

import java.util.List;

import com.game.battleship.battle.observer.Observer;
import com.game.battleship.battle.observer.Subject;
import com.game.battleship.constants.BattleMessage;
import com.game.battleship.exception.BattleShipException;
import com.game.battleship.exception.BattleShipExceptionCode;
import com.game.battleship.model.GameStatus;
import com.game.battleship.model.Missile;
import com.game.battleship.model.ShipStatus;
import com.game.battleship.model.TurnStrategy;

public class Game implements Observer {

	private List<Player> players;

	private GameStatus gameStatus;

	private Player playerAttacking;

	private Player playerAttacked;

	private TurnStrategy turnStrategy;

	public Game(List<Player> players, TurnStrategy turnStrategy) {
		this.players = players;
		this.gameStatus = GameStatus.INITIALIZED;
		this.playerAttacking = this.players.get(0);
		this.playerAttacked = this.players.get(1);
		this.turnStrategy = turnStrategy;
		// Register observer
		this.playerAttacking.getBoard().registerObserver(this);
		this.playerAttacked.getBoard().registerObserver(this);
	}

	public void play() throws BattleShipException {
		while (!(playerAttacking.isMissilesCompleted() && playerAttacked.isMissilesCompleted())) {

			
			String message = executeAndPlay();
			System.out.println(message);
			if (this.validateGameCompleteStatus()) {
				break;
			}

		}

		if (this.validateGameCompleteStatus()) {
			System.out.println(playerAttacking.getName() + " " + BattleMessage.WON.getMessage());
		} else {
			System.out.println(BattleMessage.DRAW.getMessage());
		}

	}
	
	private String executeAndPlay() throws BattleShipException {
		StringBuilder message = new StringBuilder();
		if (playerAttacking.isMissilesCompleted()) {
			message.append(playerAttacking.getName()).append(" ");
			message.append(BattleMessage.NO_MISSILE.getMessage());
			this.togglePlayers();
		} else {
			Missile missile = playerAttacking.getMissile();
			ShipStatus shipStatus = this.attack(missile);

			message.append(playerAttacking.getName()).append(" ");
			message.append(BattleMessage.MISSILE_FIRE.getMessage()).append(" ");
			message.append(getIntegerToCharacter(missile.getPosition().getCoordinateY()))
					.append(missile.getPosition().getCoordinateX()).append(" ");
			if (shipStatus == ShipStatus.HIT) {

				message.append(BattleMessage.HIT_MSG.getMessage());
			} else {
				message.append(BattleMessage.MISS_MSG.getMessage());
			}

			this.togglePlayers(shipStatus);
		}
		return message.toString();

	}

	private void togglePlayers(ShipStatus shipStatus) {

		if (TurnStrategy.HIT == turnStrategy) {
			togglePlayerByHit(shipStatus);
		} else {
			togglePlayers();
		}

	}

	private void togglePlayers() {
		Player player = this.playerAttacking;
		this.playerAttacking = this.playerAttacked;
		this.playerAttacked = player;

	}

	private void togglePlayerByHit(ShipStatus shipStatus) {
		if (ShipStatus.MISS == shipStatus) {
			Player player = this.playerAttacking;
			this.playerAttacking = this.playerAttacked;
			this.playerAttacked = player;
		}

	}

	private ShipStatus attack(Missile missile) throws BattleShipException {

		this.validateMissile(missile);

		return this.executeMissile(missile);
	}

	private void validateMissile(Missile missile) throws BattleShipException {
		// Validate the shoot in the board
		if (!(this.getPlayerAttacked().getBoard().validateShootPosition(missile))) {
			throw new BattleShipException(BattleShipExceptionCode.POSITION_OUT_OF_RANGE);
		}
	}

	private ShipStatus executeMissile(Missile missile) {
		if (this.playerAttacked.getBoard().validateAndExecuteAttack(missile)) {
			return ShipStatus.HIT;
		} else {
			return ShipStatus.MISS;
		}
	}

	private boolean validateGameCompleteStatus() {
		return this.gameStatus == GameStatus.COMPLETED;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public GameStatus getGameStatus() {
		return gameStatus;
	}

	public Player getPlayerAttacking() {
		return playerAttacking;
	}

	public Player getPlayerAttacked() {
		return playerAttacked;
	}

	public void update(Subject subject) {
		// Validate observer board
		if (subject instanceof Board && ((Board) subject).getShipsAvailable() == 0) {
			this.gameStatus = GameStatus.COMPLETED;
		}
	}

}
