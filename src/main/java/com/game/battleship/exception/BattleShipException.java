package com.game.battleship.exception;

public class BattleShipException extends Exception {

	private static final long serialVersionUID = -743454409788681851L;
	/**
	 * String with the description of the exception.
	 */
	private final String code;
	private final String description;

	/**
	 * Constructor. Call super and set description.
	 * 
	 * @param battleShipExceptionCode
	 */
	public BattleShipException(BattleShipExceptionCode battleShipExceptionCode) {
		super();
		this.code = battleShipExceptionCode.getId();
		this.description = battleShipExceptionCode.getMessage();
	}

	
	public String getCode() {
		return code;
	}

	/**
	 * Get the description of the exception.
	 * 
	 * @return String description
	 */
	public String getDescription() {
		return description;
	}
}
