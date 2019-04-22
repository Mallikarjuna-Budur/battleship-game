package com.game.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.game.battleship.exception.BattleShipException;
import com.game.battleship.model.Position;
import com.game.battleship.model.ShipType;

public class BattleShipApplicationTest {

	@Test
	public void initializeShipTypeTest() throws BattleShipException {

		BattleShipApplication battleShipApp = new BattleShipApplication();

		String typeQ = "Q";

		String typeP = "P";

		assertEquals(ShipType.DEFENCE_Q, battleShipApp.initializeShipType(typeQ));
		assertEquals(ShipType.DEFENCE_P, battleShipApp.initializeShipType(typeP));

	}

	@Test
	public void initializeShipTypeValidationTest() {

		assertThrows(BattleShipException.class, () -> {
			BattleShipApplication battleShipApp = new BattleShipApplication();

			String typeQ = "V";

			battleShipApp.initializeShipType(typeQ);
		});

	}

	@Test
	public void initializeShipPositionTest() throws BattleShipException {

		BattleShipApplication battleShipApp = new BattleShipApplication();

		Position position = battleShipApp.initializeShipPosition("A1", 2, 2);

		Position positionExpected = new Position(1, 1);
		assertEquals(positionExpected, position);
	}

	@Test
	public void initializeShipPositionOutOfBountTest() {

		assertThrows(BattleShipException.class, () -> {
			BattleShipApplication battleShipApp = new BattleShipApplication();

			battleShipApp.initializeShipPosition("A5", 2, 2);
		});

	}
}
