package name.jeremywilmot.tictactoe;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

public class GameTest {

	private Game game;
	Player player1;
	Player player2;
	
	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void getPlayers() {
		player1 = new Player("Jim");
		player2 = new Player("Joe");
		game.setPlayer1(player1);
		game.setPlayer2(player2);
		assertEquals(player1, game.getPlayer1());
		assertEquals(player2, game.getPlayer2());
	}

}
