package name.jeremywilmot.tictactoe;

public class PlayerToken {

	Player player;
	String symbol;
	
	public PlayerToken(Player player, String symbol) {
		this.player = player;
		this.symbol = symbol;
	}

	public Player getPlayer() {
		return player;
	}

	public String getSymbol() {
		return symbol;
	}
	
	
}
