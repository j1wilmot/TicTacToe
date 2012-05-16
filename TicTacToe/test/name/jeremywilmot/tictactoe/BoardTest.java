package name.jeremywilmot.tictactoe;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BoardTest {
	
	private Board board;
	
	@Before
	public void init() {
		board = new Board();
	}

	@Test
	public void takeSquare() {
		board.take(0, true);
		String expectedState = "X . .\n" +
				  			   ". . .\n" +
				  			   ". . .\n";
		assertEquals(expectedState, board.display());
	}
	
	@Test(expected=IllegalStateException.class)
	public void player1CannotTakeSameSquare() {
		board.take(0, true);
		board.take(0, true);
	}
	
	@Test(expected=IllegalStateException.class)
	public void player2CannotTakeSameSquare() {
		board.take(0, false);
		board.take(0, false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void player1CannotTakePlayer2sSquare() {
		board.take(0, true);
		board.take(0, false);
	}
	
	@Test(expected=IllegalStateException.class)
	public void player2CannotTakePlayer1sSquare() {
		board.take(0, false);
		board.take(0, true);
	}
	
	@Test
	public void gameOverAcross() {
		board.take(0, true);
		board.take(1, true);
		board.take(2, true);
		assertTrue(board.gameOver());
	}

}
