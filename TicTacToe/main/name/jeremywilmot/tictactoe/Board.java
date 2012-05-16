package name.jeremywilmot.tictactoe;

public class Board {

	SquareState[] squares = new SquareState[9];
	
	Board() {
		init();
	}
	
	public void init() {
		for (int i = 0; i < squares.length; i++) {
			squares[i] = SquareState.Empty;
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

	public void take(int i, boolean player1) {
		SquareState player = player1 ? SquareState.Player1 : SquareState.Player2;
		if (squares[i] != SquareState.Empty)
			throw new IllegalStateException("Player cannot take a square that has already been taken");
		squares[i] = player;
	}

	public boolean gameOver() {
		if (squares[0] == squares[1] && squares[1] == squares[2])
			return true;
		return false;
	}
	
}
