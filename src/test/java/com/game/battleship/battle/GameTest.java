package com.game.battleship.battle;

import static com.game.battleship.utils.FieldConvertionUtils.getPosition;

import java.util.ArrayList;
import java.util.List;

import com.game.battleship.exception.BattleShipException;
import com.game.battleship.model.Missile;
import com.game.battleship.model.MissileType;
import com.game.battleship.model.Position;
import com.game.battleship.model.ShipType;

public class GameTest {

	
	public void playTest() throws BattleShipException {
		
		Player playerA = new Player("player A");
		Player playerB = new Player("player B");
		
		playerA.createBoard(2, 2);
		playerB.createBoard(2, 2);
		
		Position locationA = new Position(1, 1);
		Position locationB = new Position(2, 2);

		playerA.getBoard().createAndlocalizeShip(1, 1, ShipType.DEFENCE_Q, locationA);
		playerB.getBoard().createAndlocalizeShip(1, 1, ShipType.DEFENCE_Q, locationB);
		
		String missileTargetsA ="A1 B2 B1";
		String[] targetsA = missileTargetsA.split(" ");

		for (String target : targetsA) {
			Missile missile = new Missile(getPosition(target), MissileType.DEFAULT);
			playerA.addMissile(missile);
		}
		
		String missileTargetsB ="A1 A2 A1";
		String[] targetsB = missileTargetsB.split(" ");

		for (String target : targetsB) {
			Missile missile = new Missile(getPosition(target), MissileType.DEFAULT);
			playerB.addMissile(missile);
		}
		
		List<Player> players = new ArrayList<>();
		players.add(playerA);
		players.add(playerB);
	}
}
