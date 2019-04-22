package com.game.battleship.battle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.game.battleship.exception.BattleShipException;
import com.game.battleship.model.Position;
import com.game.battleship.model.ShipType;

public class BoardTest {

	@Test
	public void initializeBoardTest() {
		Board board = new Board(2, 2);

		Set<Position> positions = board.getPositions();

		assertEquals(4, positions.size());

		List<Position> expectedPositions = new ArrayList<>();

		expectedPositions.add(new Position(1, 1));
		expectedPositions.add(new Position(1, 2));
		expectedPositions.add(new Position(2, 1));
		expectedPositions.add(new Position(2, 2));

		for (Position pos : expectedPositions) {
			assertTrue(positions.contains(pos));
		}
	}

	@Test
	public void createAndlocalizeShipTest() throws BattleShipException {
		Board board = new Board(2, 2);

		Set<Position> positions = board.getPositions();

		assertEquals(4, positions.size());
		Position position = new Position(1, 1);
		board.createAndlocalizeShip(1, 1, ShipType.DEFENCE_Q, position);

		assertEquals(1, board.getShips().size());

	}

	@Test
	public void createAndlocalizeShipOverlapTest() throws BattleShipException {

		assertThrows(BattleShipException.class, () -> {
			Board board = new Board(2, 2);

			Set<Position> positions = board.getPositions();

			assertEquals(4, positions.size());
			Position position = new Position(1, 1);
			board.createAndlocalizeShip(1, 1, ShipType.DEFENCE_Q, position);

			board.createAndlocalizeShip(1, 1, ShipType.DEFENCE_Q, position);
		});

	}
}
