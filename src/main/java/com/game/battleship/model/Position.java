package com.game.battleship.model;

/**
 * Position. It represents slot in the board
 * @author Bala
 *
 */
public class Position {

	private Integer coordinateX;

	private Integer coordinateY;

	public Position(Integer coordinateX, Integer coordinateY) {
		super();
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	public Integer getCoordinateX() {
		return coordinateX;
	}

	public void setCoordinateX(Integer coordinateX) {
		this.coordinateX = coordinateX;
	}

	public Integer getCoordinateY() {
		return coordinateY;
	}

	public void setCoordinateY(Integer coordinateY) {
		this.coordinateY = coordinateY;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinateX == null) ? 0 : coordinateX.hashCode());
		result = prime * result + ((coordinateY == null) ? 0 : coordinateY.hashCode());
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
		Position other = (Position) obj;
		if (coordinateX == null) {
			if (other.coordinateX != null)
				return false;
		} else if (!coordinateX.equals(other.coordinateX))
			return false;
		if (coordinateY == null) {
			if (other.coordinateY != null)
				return false;
		} else if (!coordinateY.equals(other.coordinateY))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Position [coordinateX=" + coordinateX + ", coordinateY=" + coordinateY + "]";
	}
	
}
