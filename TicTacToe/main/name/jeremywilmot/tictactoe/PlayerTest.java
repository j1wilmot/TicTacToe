package name.jeremywilmot.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testCreation() {
		String name = "Jim";
		SquareState symbol = SquareState.Player1;
		
		Player player = new Player(name, symbol);
		assertEquals(name, player.getName());
		assertEquals(symbol, player.getSymbol());
	}

}
