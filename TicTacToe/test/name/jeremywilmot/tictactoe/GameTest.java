package name.jeremywilmot.tictactoe;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class GameTest {

	private Game game;
	
	@Before
	public void init() {
		game = new Game();
	}
	
	@Test
	public void initializeGame() {
		String initialState = ". . .\n" +
							  ". . .\n" +
							  ". . .\n";
		assertEquals("Game not initialized correctly", initialState, game.display());
	}

	public static class AfterPlayersAreAssigned {
		
		private Game game;
		Player player1;
		Player player2;
		
		@Before
		public void assignPlayers() {
			game = new Game();
			player1 = new Player("Jim");
			player2 = new Player("Joe");
		}
		
		@Test
		public void getPlayers() {
			assertEquals(player1, game.getPlayer1());
			assertEquals(player2, game.getPlayer2());
		}
		
		@Test
		public void takeSquare() {
//			game.takeSquare();
		}
		
	}
	
}
