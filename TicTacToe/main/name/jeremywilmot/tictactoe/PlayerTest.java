package name.jeremywilmot.tictactoe;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {
	
	@Test
	public void testCreation() {
		String name = "Jim";
		Player player = new Player(name);
		assertEquals(name, player.getName());
	}

}
