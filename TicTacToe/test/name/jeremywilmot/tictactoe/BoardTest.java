package name.jeremywilmot.tictactoe;
import static org.junit.Assert.*;

import org.junit.experimental.runners.Enclosed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class BoardTest {

	public static class Player1 {

		private Board board;

		@Before
		public void init() {
			board = new Board();
		}

		@Test
		public void takeSquare() {
			board.setSquare(0, true);
			String expectedState = "X . .\n" +
					". . .\n" +
					". . .\n";
			assertEquals(expectedState, board.display());
		}

		@Test(expected=IllegalStateException.class)
		public void player1CannotTakeSameSquare() {
			board.setSquare(0, true);
			board.setSquare(0, true);
		}

		@Test(expected=IllegalStateException.class)
		public void player2CannotTakeSameSquare() {
			board.setSquare(0, false);
			board.setSquare(0, false);
		}

		@Test(expected=IllegalStateException.class)
		public void player1CannotTakePlayer2sSquare() {
			board.setSquare(0, true);
			board.setSquare(0, false);
		}

		@Test(expected=IllegalStateException.class)
		public void player2CannotTakePlayer1sSquare() {
			board.setSquare(0, false);
			board.setSquare(0, true);
		}
	}

	public static class GameOverWhen {
		private Board board;
		
		@Before
		public void init() {
			board = new Board();
		}

		@Test
		public void gameOverTop() {
			board.setSquare(0, true);
			board.setSquare(1, true);
			board.setSquare(2, true);
			assertTrue(board.gameOver());
		}

		@Test
		public void gameOverMiddleAcross() {
			board.setSquare(3, true);
			board.setSquare(4, true);
			board.setSquare(5, true);
			assertTrue(board.gameOver());
		}

		@Test
		public void gameOverBottom() {
			board.setSquare(6, true);
			board.setSquare(7, true);
			board.setSquare(8, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void gameOverLeft() {
			board.setSquare(0, true);
			board.setSquare(3, true);
			board.setSquare(6, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void gameOverMiddleDown() {
			board.setSquare(1, true);
			board.setSquare(4, true);
			board.setSquare(7, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void gameOverRight() {
			board.setSquare(2, true);
			board.setSquare(5, true);
			board.setSquare(8, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void gameOverRightToLeftDiagonal() {
			board.setSquare(2, true);
			board.setSquare(4, true);
			board.setSquare(6, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void gameOverLeftToRightDiagonal() {
			board.setSquare(0, true);
			board.setSquare(4, true);
			board.setSquare(8, true);
			assertTrue(board.gameOver());
		}
		
		@Test
		public void allSquaresTakenWithNoVictor() {
			board.setSquare(0, true);
			board.setSquare(1, false);
			board.setSquare(2, true);
			board.setSquare(3, true);
			board.setSquare(4, false);
			board.setSquare(5, true);
			board.setSquare(6, false);
			board.setSquare(7, true);
			board.setSquare(8, false);
			assertTrue(board.gameOver());
		}
	}

	public static class GameNotOver {
		@Test
		public void gameNotOver() {
			Board board = new Board();
			board.setSquare(3, true);
			board.setSquare(4, false);
			board.setSquare(5, true);
			assertFalse(board.gameOver());
		}
	}
	

}
