package name.jeremywilmot.tictactoe;

public class Board {

	private final SquareState[] squares = new SquareState[9];
	
	Board() {
		init();
	}
	
	public void init() {
		for (int i = 0; i < squares.length; i++) {
			squares[i] = SquareState.EMPTY;
		}
	}
	
	public String display() {
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < squares.length; i++) {
			output.append(squares[i]);
			if(i == 2 || i == 5 || i == 8) 
				output.append('\n');
			else
				output.append(' ');
		}
		return 	 output.toString();
	}

	public void setSquare(int i, boolean player1) {
		SquareState player = player1 ? SquareState.PLAYER1 : SquareState.PLAYER2;
		if (squares[i] != SquareState.EMPTY)
			throw new IllegalStateException("Player cannot take a square that has already been taken");
		squares[i] = player;
	}

	public boolean gameOver() {
		if (horizontalVictory() || 
				verticalVictory() ||
				diagonalVictory() ||
				allSquaresTaken()) {
			return true;
		}
		return false;
	}
	
	private boolean allSquaresTaken() {
		for (SquareState state : squares) {
			if (state == SquareState.EMPTY)
				return false;
		}
		return true;
	}
	
	private boolean diagonalVictory() {
		return squaresTakenBySamePlayer(2, 4, 6) ||
		squaresTakenBySamePlayer(0, 4, 8);
	}

	private boolean verticalVictory() {
		return squaresTakenBySamePlayer(0, 3, 6) ||
		squaresTakenBySamePlayer(1, 4, 7) ||
		squaresTakenBySamePlayer(2, 5, 8);
	}
	
	private boolean horizontalVictory() {
		return squaresTakenBySamePlayer(0, 1, 2) || 
				squaresTakenBySamePlayer(3, 4, 5) ||
				squaresTakenBySamePlayer(6, 7, 8);
	}
	
	private boolean squaresTakenBySamePlayer(int firstLocation, int... locations) {
		SquareState firstState = squares[firstLocation];
		if (firstState == SquareState.EMPTY)
			return false;
		for (int location : locations) {
			SquareState square = squares[location];
			if (!square.equals(firstState))
				return false;
		}
		return true;
	}
	
	public static enum SquareState {
		PLAYER1("X"),
		PLAYER2("Y"),
		EMPTY(".");
		
		private String name;
		
		SquareState(String name) {
			this.name = name;
		}
		
		@Override
		public String toString() {
			return name;
		}
	}
	
//	public static enum Position {
//		TOP_LEFT,
//		TOP_MIDDLE,
//		TOP_RIGHT,
//		MIDDLE_LEFT,
//		MIDDLE_MIDDLE,
//		MIDDLE_RIGHT,
//		BOTTOM_LEFT,
//		BOTTOM_MIDDLE,
//		BOTTOM_RIGHT;
//	}
}
