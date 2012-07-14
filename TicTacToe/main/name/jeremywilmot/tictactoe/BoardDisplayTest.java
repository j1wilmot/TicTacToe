package name.jeremywilmot.tictactoe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardDisplayTest {

	private BoardDisplay display;
	
	@Before
	public void setUp() {
		Board board = new Board();
		display = new BoardDisplay(board);
	}
	
	@Test
	public void initializeGame() {
		String initialState = ". . .\n" +
				". . .\n" +
				". . .\n";
		assertEquals("Game not initialized correctly", initialState, display.display());
	}

}
