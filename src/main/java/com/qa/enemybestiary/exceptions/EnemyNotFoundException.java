package com.qa.enemybestiary.exceptions;

public class EnemyNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8466493329239413857L;
	
	public EnemyNotFoundException(String message) {
		super(message);
	}

}
