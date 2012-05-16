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
			player1 = new Player("Jim", SquareState.Player1);
			player2 = new Player("Joe", SquareState.Player2);
			game.setPlayer1(player1);
			game.setPlayer2(player2);
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
