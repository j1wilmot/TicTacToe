package name.jeremywilmot.tictactoe;

public class Game {

	private Board board;
	private BoardDisplay display;
	private Player player1;
	private Player player2;
	
	Game() {
		this.board = new Board();
		this.display = new BoardDisplay(board);
	}
	
	public String display() {
		return display.display();
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void run() {
		System.out.println("Hello!");
		
	}

}
