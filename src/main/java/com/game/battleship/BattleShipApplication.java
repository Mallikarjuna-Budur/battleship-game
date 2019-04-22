package com.game.battleship;

import static com.game.battleship.battle.InputFieldValidation.validateBattleCoordinateX;
import static com.game.battleship.battle.InputFieldValidation.validateBattleCoordinateY;
import static com.game.battleship.battle.InputFieldValidation.validateBattleShipHeight;
import static com.game.battleship.battle.InputFieldValidation.validateBattleShipType;
import static com.game.battleship.battle.InputFieldValidation.validateBattleShipWidth;
import static com.game.battleship.battle.InputFieldValidation.validateBoardHeight;
import static com.game.battleship.battle.InputFieldValidation.validateBoardWidth;
import static com.game.battleship.battle.InputFieldValidation.validateNumberOfBattleShip;
import static com.game.battleship.utils.FieldConvertionUtils.getCharacterToInteger;
import static com.game.battleship.utils.FieldConvertionUtils.getPosition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.game.battleship.battle.Game;
import com.game.battleship.battle.Player;
import com.game.battleship.exception.BattleShipException;
import com.game.battleship.model.Missile;
import com.game.battleship.model.MissileType;
import com.game.battleship.model.Position;
import com.game.battleship.model.ShipType;
import com.game.battleship.model.TurnStrategy;

/**
 * This class is the main application starting point and initialize game
 * environment
 * 
 * @author Bala
 *
 */
public class BattleShipApplication {
	
	private final Logger logger = LoggerFactory.getLogger(BattleShipApplication.class);

	public static void main(String[] args) throws IOException {
		
		BattleShipApplication battleShipApplication = new BattleShipApplication();
		battleShipApplication.start();
	}

	/**
	 * Initialize Game Environment and start game
	 * @throws IOException
	 */
	public void start() throws IOException {

		try {
			List<Player> players = constructGameEnvironment();
			Game battleShipGame = new Game(players, TurnStrategy.HIT);
			battleShipGame.play();

		} catch (BattleShipException exception) {
			logger.error("BattleShipException : Exception code: {} - Exception message: {}", exception.getCode(),
					exception.getDescription());
		}
	}

	/**
	 * Construct game Environment by using input from user
	 * @return
	 * @throws BattleShipException
	 * @throws IOException
	 */
	private List<Player> constructGameEnvironment() throws BattleShipException, IOException {

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter area boundaries: ");
		String bountries = reader.readLine();

		Player playerA = new Player("player A");
		Player playerB = new Player("player B");

		// create board
		int length = Integer.parseInt(String.valueOf(bountries.charAt(0)));
		int height = getCharacterToInteger(bountries.charAt(1));
		// Validate board for border condition
		validateBoardWidth(length);
		validateBoardHeight(bountries.charAt(1));

		playerA.createBoard(length, height);
		playerB.createBoard(length, height);

		System.out.println("Enter number of ships: ");
		Integer numberOfShips = Integer.parseInt(reader.readLine());
		// Validate number of ships border condition
		validateNumberOfBattleShip(numberOfShips, length, height);

		int count = 1;
		while (count <= numberOfShips) {

			System.out.println("Type for battleship " + count + " :");
			String battleShipType = reader.readLine();

			ShipType shipType = initializeShipType(battleShipType);

			System.out.println("Dimension for battleship " + count + " :");
			String battleshipDimenstion = reader.readLine();

			String[] dimenstionsShip = battleshipDimenstion.split(" ");
			int shipLength = Integer.parseInt(dimenstionsShip[0]);
			int shipHeight = Integer.parseInt(dimenstionsShip[1]);

			// Validate ship width
			validateBattleShipWidth(shipLength, length);
			validateBattleShipHeight(shipHeight, height);

			System.out.println("Location of battleship " + count + " for player A:");
			String battleshipLocationA = reader.readLine();

			Position locationA = initializeShipPosition(battleshipLocationA, length, height);

			System.out.println("Location of battleship " + count + " for player B:");
			String battleshipLocationB = reader.readLine();

			Position locationB = initializeShipPosition(battleshipLocationB, length, height);

			playerA.getBoard().createAndlocalizeShip(shipLength, shipHeight, shipType, locationA);
			playerB.getBoard().createAndlocalizeShip(shipLength, shipHeight, shipType, locationB);
			count++;
		}

		System.out.println("Missile targets for player A: ");
		String missileTargetsA = reader.readLine();
		loadMissilesOfPlayer(playerA, missileTargetsA);

		System.out.println("Missile targets for player B: ");
		String missileTargetsB = reader.readLine();
		loadMissilesOfPlayer(playerB, missileTargetsB);
		List<Player> players = new ArrayList<>();
		players.add(playerA);
		players.add(playerB);
		return players;
	}

	/**
	 * Initialize and validate the ship type
	 * @param battleShipType
	 * @return
	 * @throws BattleShipException
	 */
	public ShipType initializeShipType(String battleShipType) throws BattleShipException {
		// Validate ship type
		validateBattleShipType(battleShipType);

		if (battleShipType.equals("P")) {
			return ShipType.DEFENCE_P;
		} else if (battleShipType.equals("Q")) {
			return ShipType.DEFENCE_Q;
		}
		return null;
	}

	
	/**
	 * Initialize and validate ship position
	 * @param battleshipLocation
	 * @param length
	 * @param height
	 * @return
	 * @throws BattleShipException
	 */
	public Position initializeShipPosition(String battleshipLocation, Integer length, Integer height)
			throws BattleShipException {
		Position location = getPosition(battleshipLocation);

		// validate battle ship position
		validateBattleCoordinateX(location.getCoordinateX(), length);
		validateBattleCoordinateY(location.getCoordinateY(), height);

		return location;
	}

	/**
	 * Load missiles for players
	 * @param player
	 * @param missileTargets
	 */
	private void loadMissilesOfPlayer(Player player, String missileTargets) {

		String[] targetsA = missileTargets.split(" ");

		for (String target : targetsA) {
			Missile missile = new Missile(getPosition(target), MissileType.DEFAULT);
			player.addMissile(missile);
		}
	}
}
