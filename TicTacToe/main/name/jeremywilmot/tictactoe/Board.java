package name.jeremywilmot.tictactoe;

import java.util.HashMap;
import java.util.Map;

import static name.jeremywilmot.tictactoe.Board.Position.*;

// XXX should board determine winner or is that a function of Game?
public final class Board {

	private final Map<Position, SquareState> board;
	
	Board() {
		board = new HashMap<Position, SquareState>();
		init();
	}
	
	private void init() {
		for (Position position : Position.values()) {
			board.put(position, SquareState.EMPTY);
		}
	}
	
	private Board(Map<Position, SquareState> board) {
		this.board = board;
	}
	
	/**
	 * Sets a position to be the provided SquareState.
	 * @throws IllegalStateExcption if position is already taken
	 */
	public Board setSquare(Position position, SquareState state) {
		checkIfPositionTaken(position);
		Map<Position, SquareState> boardCopy = new HashMap<Position, SquareState>(board);
		boardCopy.put(position, state);
		return new Board(boardCopy);
	}

	private void checkIfPositionTaken(Position position) {
		if (board.get(position) != SquareState.EMPTY)
			throw new IllegalStateException("Player cannot take a square that has already been taken");
	}

	public SquareState getSquare(Position position) {
		return board.get(position);
	}

	public boolean hasWinner() {
		if (horizontalVictory() || 
				verticalVictory() ||
				diagonalVictory()) {
			return true;
		}
		return false;
	}
	
	public boolean gameOver() {
		return hasWinner() || allSquaresTaken();
	}
	
	private boolean allSquaresTaken() {
		for (SquareState state : board.values()) {
			if (state == SquareState.EMPTY)
				return false;
		}
		return true;
	}
	
	private boolean diagonalVictory() {
		return squaresTakenBySamePlayer(TOP_LEFT, MIDDLE_CENTER, BOTTOM_RIGHT) ||
			squaresTakenBySamePlayer(BOTTOM_LEFT, MIDDLE_CENTER, TOP_RIGHT);
	}

	private boolean horizontalVictory() {
		return squaresTakenBySamePlayer(TOP_LEFT, TOP_CENTER, TOP_RIGHT) ||
				squaresTakenBySamePlayer(MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT) ||
				squaresTakenBySamePlayer(BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT);
	}
	
	private boolean verticalVictory() {
		return squaresTakenBySamePlayer(TOP_LEFT, MIDDLE_LEFT, BOTTOM_LEFT) || 
				squaresTakenBySamePlayer(TOP_CENTER, MIDDLE_CENTER, BOTTOM_CENTER) ||
				squaresTakenBySamePlayer(TOP_RIGHT, MIDDLE_RIGHT, BOTTOM_RIGHT);
	}
	
	private boolean squaresTakenBySamePlayer(Position firstPosition, Position... positions) {
		SquareState firstState = board.get(firstPosition);
		if (firstState == SquareState.EMPTY) {
			return false;
		}
		for (Position position : positions) {
			SquareState state = board.get(position);
			if (!state.equals(firstState)) {
				return false;
			}
		}
		return true;
	}
	
	public static enum Position {
		TOP_LEFT,
		TOP_CENTER,
		TOP_RIGHT,
		MIDDLE_LEFT,
		MIDDLE_CENTER,
		MIDDLE_RIGHT,
		BOTTOM_LEFT,
		BOTTOM_CENTER,
		BOTTOM_RIGHT;
	}
	
	public static enum SquareState {
		PLAYER1,
		PLAYER2,
		EMPTY
	}
	
}
