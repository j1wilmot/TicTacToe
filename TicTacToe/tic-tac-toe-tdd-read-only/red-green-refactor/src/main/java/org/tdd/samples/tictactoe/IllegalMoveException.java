package org.tdd.samples.tictactoe;

public class IllegalMoveException extends RuntimeException {

	public IllegalMoveException(String message) {
		super(message);
	}

}
