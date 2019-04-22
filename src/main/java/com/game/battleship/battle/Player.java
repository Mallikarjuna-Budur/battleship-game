package com.game.battleship.battle;

import java.util.LinkedList;
import java.util.Queue;

import com.game.battleship.model.Missile;

/**
 * This player class is responsible to create board and hold missiles
 * @author Bala
 *
 */
public class Player {

	private String name;

	private Queue<Missile> missiles;

	private Board board;

	public Player(String name) {
		super();
		this.name = name;
		this.missiles = new LinkedList<>();
	}

	public void createBoard(Integer length, Integer height) {
		this.board = new Board(length, height);
	}

	public boolean isMissilesCompleted() {
		return missiles.isEmpty();
	}
	public String getName() {
		return name;
	}

	public Missile getMissile() {
		return missiles.poll();
	}

	public Board getBoard() {
		return board;
	}

	public void addMissile(Missile missile) {
		this.missiles.add(missile);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
