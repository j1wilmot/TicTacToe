package name.jeremywilmot.tictactoe;

public enum SquareState {

	Player1("X"),
	Player2("Y"),
	Empty(".");
	
	private String name;
	
	SquareState(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
