package name.jeremywilmot.tictactoe;

public class Player {

	private final String name;
	private final SquareState symbol;
	
	Player(String name, SquareState symbol) {
		this.name = name;
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public SquareState getSymbol() {
		return symbol;
	}
	
}
